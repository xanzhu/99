package hotelmanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
                System.err.println("Error adding room: " + ex.getMessage());
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
                System.err.println("Error adding room: " + ex.getMessage());
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
                System.err.println("Error adding room: " + ex.getMessage());
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
                    if(isAvailable){
                        showSuccessMessage("Food item '" + foodName + "' is now available!");
                    }
                    else {
                        showSuccessMessage("Food item '" + foodName + "' is now unavailable!");
                    }
                } else {
                    showErrorMessage("Food item '" + foodName + "' not found in the database.");
                }
            } catch (SQLException ex) {
                System.err.println("Error adding room: " + ex.getMessage());
            }
        }
    }

}
