package bobbyhyunhotel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author junhy test
 */
public class Booking {

    private RoomManagement rm;

    public Booking() {
        rm = new RoomManagement();
    }

    private String jdbcUrl = "jdbc:derby:HotelRecords;create=true"; // Update this with your URL

    public void addBooking(String userEmail, int roomNumber, java.sql.Date bookingDate) {
        try {
            // Step 1: Retrieve UserID based on the email
            int userId = getUserIdByEmail(userEmail);

            // Step 2: Insert the booking information into BookingRecords
            String insertBookingSQL = "INSERT INTO BookingRecords (UserID, RoomNumber, BookingDate) VALUES (?, ?, ?)";

            try (Connection connection = DriverManager.getConnection(jdbcUrl); PreparedStatement preparedStatement = connection.prepareStatement(insertBookingSQL)) {
                preparedStatement.setInt(1, userId);
                preparedStatement.setInt(2, roomNumber);
                preparedStatement.setDate(3, bookingDate);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    // Successfully added the booking, now update the room's availability to "unavailable"
                    rm.updateRoomAvailability(roomNumber, false); // Set the room as unavailable
                    System.out.println("Booking information saved successfully, and room set to unavailable.");
                } else {
                    System.out.println("Booking information not saved.");
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL error occurred:");
            e.printStackTrace();
        }
    }

    public int getUserIdByEmail(String email) {
        int userId = -1; // Default value if no user found

        String query = "SELECT ID FROM UserData WHERE Email = ?";

        try (Connection connection = DriverManager.getConnection(jdbcUrl); PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    userId = resultSet.getInt("ID");
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL error occurred while retrieving UserID by email:");
            e.printStackTrace();
        }

        return userId;
    }

    public void cancelBooking(int selectedBookingId) {
        // JDBC URL for connecting to the Derby embedded database
        String jdbcUrl = "jdbc:derby:HotelRecords;create=true";

        // SQL statement to update the room availability to "available"
        String updateRoomAvailabilitySQL = "UPDATE RoomRecords SET IsAvailable = TRUE WHERE RoomNumber = "
                + "(SELECT RoomNumber FROM BookingRecords WHERE BookingID = ?)";

        // SQL statement to delete the booking record
        String deleteBookingSQL = "DELETE FROM BookingRecords WHERE BookingID = ?";

        try (Connection connection = DriverManager.getConnection(jdbcUrl); PreparedStatement updateAvailabilityStatement = connection.prepareStatement(updateRoomAvailabilitySQL); PreparedStatement deleteBookingStatement = connection.prepareStatement(deleteBookingSQL)) {

            // Start a transaction
            connection.setAutoCommit(false);

            updateAvailabilityStatement.setInt(1, selectedBookingId);

            // Execute the SQL statement to update room availability
            int availabilityUpdated = updateAvailabilityStatement.executeUpdate();

            deleteBookingStatement.setInt(1, selectedBookingId);

            // Execute the SQL statement to delete the booking record
            int rowsDeleted = deleteBookingStatement.executeUpdate();

            if (availabilityUpdated > 0 && rowsDeleted > 0) {
                // Commit the transaction
                connection.commit();
                System.out.println("Booking with ID " + selectedBookingId + " has been canceled.");
            } else {
                // Rollback the transaction if any of the updates fail
                connection.rollback();
                System.out.println("Booking with ID " + selectedBookingId + " not found.");
            }
        } catch (SQLException ex) {
            System.err.println("SQL error occurred:");
            ex.printStackTrace();
        }
    }

}
