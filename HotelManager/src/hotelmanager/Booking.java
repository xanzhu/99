package hotelmanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author bobby
 */
public class Booking {

    private final DBManager dbManager;
    private RoomManagement rm;

    public Booking(DBManager dbManager) {
        this.dbManager = dbManager;
        rm = new RoomManagement(dbManager);
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Room Service Error", JOptionPane.ERROR_MESSAGE);
    }

    private void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Room Service", JOptionPane.PLAIN_MESSAGE);
    }

    public void addBooking(String userEmail, int roomNumber, java.sql.Date bookingDate) {

        int userId = userEmailID(userEmail);

        String query = "INSERT INTO BookingRecords (UserID, RoomNumber, BookingDate) VALUES (?, ?, ?)";

        Connection connection = dbManager.getConnection();

        if (connection != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setInt(1, userId);
                preparedStatement.setInt(2, roomNumber);
                preparedStatement.setDate(3, bookingDate);

                int lines = preparedStatement.executeUpdate();

                if (lines > 0) {
                    // Set Room Status to false
                    rm.roomStatus(roomNumber, false);
                    System.out.println("Booking added successfully!");
                } else {
                    System.out.println("Failed to Book room.");
                }
            } catch (SQLException ex) {
                System.err.println("Error Booking room: " + ex.getMessage());
            }
        }
    }

    public void cancelBooking(int selectedBookingId) {
        String updateRoomAvailabilitySQL = "UPDATE RoomRecords SET IsAvailable = TRUE WHERE RoomNumber = "
                + "(SELECT RoomNumber FROM BookingRecords WHERE BookingID = ?)";

        String deleteBookingSQL = "DELETE FROM BookingRecords WHERE BookingID = ?";

        Connection connection = dbManager.getConnection();

        try (PreparedStatement updateAvailabilityStatement = connection.prepareStatement(updateRoomAvailabilitySQL); PreparedStatement deleteBookingStatement = connection.prepareStatement(deleteBookingSQL)) {

            connection.setAutoCommit(false); // Start a transaction

            updateAvailabilityStatement.setInt(1, selectedBookingId);
            int availabilityUpdated = updateAvailabilityStatement.executeUpdate();

            deleteBookingStatement.setInt(1, selectedBookingId);
            int rowsDeleted = deleteBookingStatement.executeUpdate();

            if (availabilityUpdated > 0 && rowsDeleted > 0) {
                connection.commit();
                showSuccessMessage("Booking with ID " + selectedBookingId + " has been canceled.");
            } else {
                connection.rollback();
                showErrorMessage("Booking with ID " + selectedBookingId + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int userEmailID(String email) {
        int userId = -1;

        String query = "SELECT ID FROM UserData WHERE Email = ?";

        Connection connection = dbManager.getConnection();

        if (connection != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, email);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        userId = resultSet.getInt("ID");
                    }
                }
            } catch (SQLException ex) {
                System.err.println("Error adding room: " + ex.getMessage());
            }
        }
        return userId;
    }

// Validation Methods
    public boolean validateAddRoom(int roomNumber) {

        String query = "SELECT IsAvailable FROM RoomRecords WHERE RoomNumber = ?";

        Connection connection = dbManager.getConnection();

        if (connection != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setInt(1, roomNumber);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        boolean isAvailable = resultSet.getBoolean("IsAvailable");
                        return isAvailable;
                    }
                }
            } catch (SQLException ex) {
                System.err.println("SQL error occurred:");
                ex.printStackTrace();
            }
        }
        return false;
    }
}
