package hotelmanager;

/**
 *
 * @author bobby
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;

public class RoomManagement {

    private final DBManager dbManager;

    public RoomManagement(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Room Management Error", JOptionPane.ERROR_MESSAGE);
    }

    private void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Room Management", JOptionPane.PLAIN_MESSAGE);
    }

    public boolean validateAddRoom(String roomNumberField, String priceField, String roomTypeField) {

        // Check 1: Room Number
        try {
            int roomNumber = Integer.parseInt(roomNumberField);
            if (checkRoom(roomNumber)) {
                showErrorMessage("Room with Room Number " + roomNumber + " already exists.");
                return false;
            } else if (roomNumber <= 0) {
                showErrorMessage("Room numbers must be positive.");
                return false;
            }
        } catch (NumberFormatException e) {
            showErrorMessage("Please enter a valid Room Number [1-999]");
            return false;
        }

        // Check 2: Room Type Empty
        if (roomTypeField.isEmpty()) {
            showErrorMessage("Please enter the type of room.\n(Suite, Double)");
            return false;
        }

        // Check 3: Room Price
        try {
            double price = Double.parseDouble(priceField);
            if (price <= 0.0) {
                showErrorMessage("Prices must be positive for rooms.");
                return false;
            }
        } catch (NumberFormatException e) {
            showErrorMessage("Please enter a valid price for the room.");
            return false;
        }

        // Pass
        showSuccessMessage("Room:" + roomNumberField + ""
                + "\nPrice: " + priceField
                + "\nType: " + roomTypeField
                + "\n\nCreated successfully!");
        
        return true;
    }

    // Check room
    public boolean checkRoom(int roomNumber) {
        String query = "SELECT RoomNumber FROM RoomRecords WHERE RoomNumber = ?";

        Connection connection = dbManager.getConnection();

        if (connection != null) {

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setInt(1, roomNumber);
                ResultSet resultSet = preparedStatement.executeQuery();

                return resultSet.next();

            } catch (SQLException ex) {
                System.err.println("Error checking if room exists: " + ex.getMessage());
                return false;
            }
        }
        return false;
    }

    public void addRoom(int roomNumber, String roomType, double price, boolean isAvailable) {

        String query = "INSERT INTO RoomRecords (roomNumber, roomType, price, IsAvailable) VALUES (?, ?, ?, ?)";

        Connection connection = dbManager.getConnection();

        if (connection != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, roomNumber);
                preparedStatement.setString(2, roomType);
                preparedStatement.setDouble(3, price);
                preparedStatement.setBoolean(4, isAvailable);

                int lines = preparedStatement.executeUpdate();

                // Testing Commit Change 
                connection.commit();

                if (lines > 0) {
                    System.out.println("Room added successfully!");
                } else {
                    System.out.println("Failed to add room.");
                }
            } catch (SQLException ex) {
                System.err.println("Error adding room: " + ex.getMessage());
            }
        }
    }

    public void removeRoom(int roomNumber) {

        String query = "DELETE FROM RoomRecords WHERE RoomNumber = ?";

        Connection connection = dbManager.getConnection();

        if (connection != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, roomNumber);
                int lines = preparedStatement.executeUpdate();

                if (lines > 0) {
                    showSuccessMessage("Room: " + roomNumber + " has been deleted successfully.");
                } else {
                    showErrorMessage("Room: " + roomNumber + " does not exist.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            showErrorMessage("Failed to establish database connection.");
        }
    }

    public void updateRoomPrice(int roomNumber, double newPrice) {

        String query = "UPDATE RoomRecords SET Price = ? WHERE RoomNumber = ?";

        Connection connection = dbManager.getConnection();

        if (connection != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setDouble(1, newPrice);
                preparedStatement.setInt(2, roomNumber);

                int updatedRows = preparedStatement.executeUpdate();

                if (updatedRows > 0) {
                    showSuccessMessage("Room: " + roomNumber + " has been adjusted to: $" + newPrice);
                } else {
                    showErrorMessage("Room: " + roomNumber + " does not exist.");
                }

            } catch (SQLException ex) {
                System.err.println("Failed to update price: " + ex.getMessage());
            }
        }
    }

    public void roomStatus(int roomNumber, boolean isAvailable) {

        String query = "UPDATE RoomRecords SET IsAvailable = ? WHERE RoomNumber = ?";

        Connection connection = dbManager.getConnection();

        if (connection != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setBoolean(1, isAvailable);
                preparedStatement.setInt(2, roomNumber);

                int updatedRows = preparedStatement.executeUpdate();

                if (updatedRows > 0) {
                    if (isAvailable) {
                        showSuccessMessage("Room: " + roomNumber + " is now available.");
                    } else {
                        showSuccessMessage("Room: " + roomNumber + " is now unavailable.");
                    }
                } else {
                    showErrorMessage("Room: " + roomNumber + " does not exist.");
                }

            } catch (SQLException ex) {
                System.err.println("Failed to update status: " + ex.getMessage());
            }
        }
    }
}
