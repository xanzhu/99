package hotelmanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author bobby
 */
public class RoomServices {

    private final DBManager dbManager;

    public RoomServices(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Room Service Error", JOptionPane.ERROR_MESSAGE);
    }

    private void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Room Service", JOptionPane.PLAIN_MESSAGE);
    }

    public void addFood(String foodName, String foodType, double price, boolean isAvailable) {

        String query = "INSERT INTO RoomServicesRecords (FoodName, FoodType, Price, IsAvailable) VALUES (?, ?, ?, ?)";

        Connection connection = dbManager.getConnection();

        if (connection != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, foodName);
                preparedStatement.setString(2, foodType);
                preparedStatement.setDouble(3, price);
                preparedStatement.setBoolean(4, isAvailable);

                int lines = preparedStatement.executeUpdate();

                if (lines > 0) {
                    showSuccessMessage(foodName + " has been added!");
                } else {
                    showErrorMessage("Failed to add Room Service Item");
                }
            } catch (SQLException ex) {
                System.err.println("Error adding food: " + ex.getMessage());
            }
        }
    }

    public void removeFood(String foodName) {

        String query = "DELETE FROM RoomServicesRecords WHERE FoodName = ?";

        Connection connection = dbManager.getConnection();

        if (connection != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, foodName);

                int lines = preparedStatement.executeUpdate();

                if (lines > 0) {
                    showSuccessMessage("Food item '" + foodName + "' removed successfully.");
                } else {
                    showErrorMessage("Food item '" + foodName + "' not found in the database.");
                }
            } catch (SQLException ex) {
                System.err.println("Error deleting food item: " + ex.getMessage());
            }
        }
    }

    public void updateFoodPrice(String foodName, double newPrice) {

        String query = "UPDATE RoomServicesRecords SET Price = ? WHERE FoodName = ?";

        Connection connection = dbManager.getConnection();

        if (connection != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setDouble(1, newPrice);
                preparedStatement.setString(2, foodName);

                int lines = preparedStatement.executeUpdate();

                if (lines > 0) {
                    showSuccessMessage("Food item '" + foodName + "' price updated to: $" + newPrice);
                } else {
                    showErrorMessage("Food item '" + foodName + "' not found in the database.");
                }
            } catch (SQLException ex) {
                System.err.println("Error updating food price: " + ex.getMessage());
            }
        }
    }

    public void updateFoodStatus(String foodName, boolean isAvailable) {

        String query = "UPDATE RoomServicesRecords SET IsAvailable = ? WHERE FoodName = ?";

        Connection connection = dbManager.getConnection();

        if (connection != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setBoolean(1, isAvailable);
                preparedStatement.setString(2, foodName);

                int lines = preparedStatement.executeUpdate();

                if (lines > 0) {
                    if (isAvailable) {
                        showSuccessMessage("Food item '" + foodName + "' is now available!");
                    } else {
                        showSuccessMessage("Food item '" + foodName + "' is now unavailable!");
                    }
                } else {
                    showErrorMessage("Food item '" + foodName + "' not found in the database.");
                }
            } catch (SQLException ex) {
                System.err.println("Error adding food status: " + ex.getMessage());
            }
        }
    }

    public int matchIDEmail(String email) {
        int userId = -1; // Default value if no user found

        String query = "SELECT ID FROM UserData WHERE Email = ?";

        Connection connection = dbManager.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    userId = resultSet.getInt("ID");
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error while retrieving UserID by email:" + ex.getMessage());
        }

        return userId;
    }

    public Map<String, Double> getItemsPrice() {
        Map<String, Double> foodItemsPrices = new HashMap<>();

        String query = "SELECT FoodName, Price FROM RoomServicesRecords WHERE IsAvailable = true";

        Connection connection = dbManager.getConnection();

        if (connection != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query); ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    String foodName = resultSet.getString("FoodName");
                    double price = resultSet.getDouble("Price");
                    foodItemsPrices.put(foodName, price);
                }
            } catch (SQLException ex) {
                System.err.println("Error getting Items and Prices: " + ex.getMessage());
            }
        }
        return foodItemsPrices;
    }

    public int matchRoomEmail(String userEmail) {

        String query = "SELECT RoomNumber FROM BookingRecords WHERE UserID = (SELECT ID FROM UserData WHERE Email = ?)";

        Connection connection = dbManager.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, userEmail);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("RoomNumber");
                } else {
                    return -1;
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error matching Room to Email " + ex.getMessage());
            return -1;
        }
    }

    public void orderRoomServices(String userEmail, int roomNumber, String foodName, double price) {

        String query = "INSERT INTO OrderRecords (UserID, RoomNumber, FoodName, Price) VALUES (?, ?, ?, ?)";

        Connection connection = dbManager.getConnection();

        if (connection != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                int userId = matchIDEmail(userEmail);

                System.out.println("UserID: " + userId);
                System.out.println("RoomNumber: " + roomNumber);

                if (userId != -1) {
                    preparedStatement.setInt(1, userId);
                    preparedStatement.setInt(2, roomNumber);
                    preparedStatement.setString(3, foodName);
                    preparedStatement.setDouble(4, price);

                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("Order saved successfully.");
                    } else {
                        System.out.println("Order not saved.");
                    }
                } else {
                    System.out.println("User not found. Cannot place the order.");
                }
            } catch (SQLException ex) {
                System.err.println("Error placing order: " + ex.getMessage());
            }
        }
    }

    public void viewFoodPriceItem(StringBuilder details) {
        details.setLength(0);

        String query = "SELECT FoodName, FoodType, Price FROM RoomServicesRecords WHERE IsAvailable = true";

        Connection connection = dbManager.getConnection();

        try (
                PreparedStatement preparedStatement = connection.prepareStatement(query); ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String foodName = resultSet.getString("FoodName");
                String foodType = resultSet.getString("FoodType");
                double price = resultSet.getDouble("Price");

                details.append("\n");
                details.append("Food Name: ").append(foodName).append("\n");
                details.append("Food Type: ").append(foodType).append("\n");
                details.append("Price: $").append(price).append("\n");
            }
        } catch (SQLException ex) {
            System.err.println("Error viewing order: " + ex.getMessage());
        }
    }

}
