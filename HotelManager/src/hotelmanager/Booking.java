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
        JOptionPane.showMessageDialog(null, message, "Booking Menu Error", JOptionPane.ERROR_MESSAGE);
    }

    private void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Booking Menu", JOptionPane.PLAIN_MESSAGE);
    }

    // TODO: User Booking Re-write // Handle only room number and date when booking // Email for Staff Management
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
                    rm.roomStatus(roomNumber, false, false);
                    showSuccessMessage("Room:" + roomNumber + " has added successfully booked!");
                } else {
                    showErrorMessage("Failed to Book room\nPlease Try again.");
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

            connection.setAutoCommit(false);

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

    public boolean hasBooking(int userId) {
        String selectBookingSQL = "SELECT COUNT(*) FROM BookingRecords WHERE UserID = ?";

        Connection connection = dbManager.getConnection();
        if (connection != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectBookingSQL)) {
                preparedStatement.setInt(1, userId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int bookingCount = resultSet.getInt(1);
                        System.out.println("DB: NOT FOUND");
                        return bookingCount > 0;
                    }
                }
            } catch (SQLException ex) {
                System.err.println("SQL error occurred while checking booking: " + ex.getMessage());
            }
        }

        return false;
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
