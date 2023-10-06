package hotelmanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author Bobby Jenkins, Hyun il Jun
 */
public class Booking {

    private final DBManager dbManager;
    private final RoomManagement rm;

    // Default constructor
    public Booking(DBManager dbManager) {
        this.dbManager = dbManager;
        rm = new RoomManagement(dbManager);
    }

    // Reusable Error message component
    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Booking Menu Error", JOptionPane.ERROR_MESSAGE);
    }

    // Reusable Success message component
    private void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Booking Menu", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Add Booking Function Stores user input into BookingRecords table.
     *
     * @param userEmail
     * @param roomNumber
     * @param bookingDate
     */
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

    /**
     * Cancel Booking Function Uses Booking ID to cancel users booking
     *
     * @param selectedBookingId
     */
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
        } catch (SQLException ex) {
            System.err.println("Error deleting ID from email: " + ex.getMessage());
        }
    }

    /**
     * Obtain User Email Function Matches ID to user Email
     *
     * @param email
     * @return
     */
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
                System.err.println("Error selecting ID from email: " + ex.getMessage());
            }
        }
        return userId;
    }

    /**
     * Current Booking Function Returns Boolean if a booking has been found.
     *
     * @param userId
     * @return
     */
    public boolean hasBooking(int userId) {
        String selectBookingSQL = "SELECT COUNT(*) FROM BookingRecords WHERE UserID = ?";

        Connection connection = dbManager.getConnection();
        if (connection != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectBookingSQL)) {
                preparedStatement.setInt(1, userId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int bookingCount = resultSet.getInt(1);
                        return bookingCount > 0;
                    }
                }
            } catch (SQLException ex) {
                System.err.println("Error while checking booking status: " + ex.getMessage());
            }
        }

        return false;
    }

    /**
     * Validation Function for Adding Room Adds validation to Add room fields.
     *
     * @param roomNumber
     * @return
     */
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
                System.err.println("Error validating room: " + ex.getMessage());
            }
        }
        return false;
    }

    /**
     * Booking Function for Staff Allows staff to book a room for any user
     *
     * @param guestEmail
     * @param roomNumber
     * @param bookingDate
     */
    public void staffAddBooking(String guestEmail, int roomNumber, java.sql.Date bookingDate) {

        String query = "INSERT INTO BookingRecords (UserID, RoomNumber, BookingDate) VALUES (?, ?, ?)";

        Connection connection = dbManager.getConnection();

        if (connection != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                int userId = userEmailID(guestEmail);

                preparedStatement.setInt(1, userId);
                preparedStatement.setInt(2, roomNumber);
                preparedStatement.setDate(3, bookingDate);

                int lines = preparedStatement.executeUpdate();

                if (lines > 0) {
                    rm.roomStatus(roomNumber, false, false);
                    showSuccessMessage("Room:" + roomNumber + " has been successfully booked for " + guestEmail + "!");
                } else {
                    showErrorMessage("Failed to book room for " + guestEmail + "\nPlease try again.");
                }
            } catch (SQLException ex) {
                System.err.println("Error booking room: " + ex.getMessage());
            }
        }
    }

    /**
     * Cancel Booking Function for Staff Allows staff to delete bookings and
     * then updates room status.
     *
     * @param bookID
     */
    public void staffCancelBooking(int bookID) {
        String selectRoomNumberSQL = "SELECT RoomNumber FROM BookingRecords WHERE BookingID = ?";
        String deleteBookingSQL = "DELETE FROM BookingRecords WHERE BookingID = ?";
        String updateRoomStatusSQL = "UPDATE RoomRecords SET IsAvailable = true WHERE RoomNumber = ?";

        Connection connection = dbManager.getConnection();

        try {
            connection.setAutoCommit(false);

            int roomNumber = -1;

            try (PreparedStatement selectRoomNumberStatement = connection.prepareStatement(selectRoomNumberSQL)) {
                selectRoomNumberStatement.setInt(1, bookID);

                try (ResultSet resultSet = selectRoomNumberStatement.executeQuery()) {
                    if (resultSet.next()) {
                        roomNumber = resultSet.getInt("RoomNumber");
                    } else {
                        showErrorMessage("Booking not found.");
                        return;
                    }
                }
            }

            try (PreparedStatement updateRoomStatusStatement = connection.prepareStatement(updateRoomStatusSQL)) {
                updateRoomStatusStatement.setInt(1, roomNumber);
                int updateRowsAffected = updateRoomStatusStatement.executeUpdate();

                if (updateRowsAffected > 0) {
                    try (PreparedStatement deleteBookingStatement = connection.prepareStatement(deleteBookingSQL)) {
                        deleteBookingStatement.setInt(1, bookID);
                        int deleteRowsAffected = deleteBookingStatement.executeUpdate();

                        if (deleteRowsAffected > 0) {
                            showSuccessMessage("Booking: " + bookID + "has been successfully removed.");
                        } else {
                            showErrorMessage("Failed to delete booking, try again.");
                        }
                    }
                } else {
                    showErrorMessage("Failed to update Room status.");
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error during cancellation: " + ex.getMessage());
        }
    }

    /**
     * Get BookingRecords Function Selects all active booking records from table
     * then assigns to Records ArrayList
     *
     * @param email
     * @return
     */
    public List<Records> getBookingRecords(String email) {
        List<Records> bookingRecords = new ArrayList<>();

        try {
            int userId = userEmailID(email);

            if (userId == -1) {
                return bookingRecords; // User not found
            }

            String viewBookingSQL = "SELECT * FROM BookingRecords WHERE UserID = ?";

            Connection connection = dbManager.getConnection();

            try (PreparedStatement preparedStatement = connection.prepareStatement(viewBookingSQL,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

                preparedStatement.setInt(1, userId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int bookingID = resultSet.getInt("BookingID");
                        int roomNumber = resultSet.getInt("RoomNumber");
                        java.sql.Date bookingDate = resultSet.getDate("BookingDate");

                        Records record = new Records(bookingID, userId, roomNumber, bookingDate);
                        bookingRecords.add(record);
                    }
                }
            } catch (SQLException ex) {
                System.err.println("Error getting Booking records: " + ex.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookingRecords;
    }

    /**
     * Get Bookings for user Selects all records for current user.
     *
     * @param userId
     * @return
     */
    public List<Records> getBookingsForUser(int userId) {
        List<Records> userBooking = new ArrayList<>();

        String query = "SELECT * FROM BookingRecords WHERE UserID = ?";

        Connection connection = dbManager.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int bookingID = resultSet.getInt("BookingID");
                    int roomNumber = resultSet.getInt("RoomNumber");
                    java.sql.Date bookingDate = resultSet.getDate("BookingDate");

                    Records rc = new Records(bookingID, userId, roomNumber, bookingDate);
                    userBooking.add(rc);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error getting user booking: " + ex.getMessage());
        }
        return userBooking;
    }

    public void availableRooms(JComboBox<String> roomDropdown) {
        String query = "SELECT RoomNumber FROM RoomRecords WHERE IsAvailable = true";

        try (Connection connection = dbManager.getConnection(); PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {

            roomDropdown.setSelectedIndex(-1);

            while (resultSet.next()) {
                String roomNumber = resultSet.getString("RoomNumber");
                roomDropdown.addItem(roomNumber);
                System.out.println("Available Room Number: " + roomNumber); // Print room numbers to console
            }
        } catch (SQLException ex) {
            System.err.println("Error getting available rooms: " + ex.getMessage());
        }
    }

}
