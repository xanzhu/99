package bobbyhyunhotel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author junhy
 */
public class RoomServices {

    private String jdbcUrl = "jdbc:derby:HotelRecords;create=true"; // Update this with your URL

    public static void addFood(String foodName, String foodType, double price, boolean isAvailable) {
        // JDBC URL for connecting to the Derby embedded database
        String jdbcUrl = "jdbc:derby:HotelRecords;create=true";

        try (Connection connection = DriverManager.getConnection(jdbcUrl); PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO RoomServicesRecords (FoodName, FoodType, Price, IsAvailable) VALUES (?, ?, ?, ?)")) {

            preparedStatement.setString(1, foodName);
            preparedStatement.setString(2, foodType);
            preparedStatement.setDouble(3, price);
            preparedStatement.setBoolean(4, isAvailable);

            preparedStatement.executeUpdate();

            System.out.println("Food information saved to the database successfully.");

        } catch (SQLException ex) {
            System.err.println("SQL error occurred:");
            ex.printStackTrace();
        }
    }

    public void removeFood(String foodName) {
        // JDBC URL for connecting to the Derby embedded database
        String jdbcUrl = "jdbc:derby:HotelRecords;create=true";

        try (Connection connection = DriverManager.getConnection(jdbcUrl); PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM RoomServicesRecords WHERE FoodName = ?")) {

            preparedStatement.setString(1, foodName);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Food item '" + foodName + "' removed successfully.");
            } else {
                System.out.println("Food item '" + foodName + "' not found in the database.");
            }

        } catch (SQLException ex) {
            System.err.println("SQL error occurred:");
            ex.printStackTrace();
        }
    }

    public void updateFoodPrice(String foodName, double newPrice) {
        // JDBC URL for connecting to the Derby embedded database
        String jdbcUrl = "jdbc:derby:HotelRecords;create=true";

        try (Connection connection = DriverManager.getConnection(jdbcUrl); PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE RoomServicesRecords SET Price = ? WHERE FoodName = ?")) {

            preparedStatement.setDouble(1, newPrice);
            preparedStatement.setString(2, foodName);

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Food price updated successfully.");
            } else {
                System.out.println("No such food item found.");
            }

        } catch (SQLException ex) {
            System.err.println("SQL error occurred:");
            ex.printStackTrace();
        }
    }

    public void updateFoodAvailability(String foodName, boolean isAvailable) {
        // JDBC URL for connecting to the Derby embedded database
        String jdbcUrl = "jdbc:derby:HotelRecords;create=true";

        try (Connection connection = DriverManager.getConnection(jdbcUrl); PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE RoomServicesRecords SET IsAvailable = ? WHERE FoodName = ?")) {

            preparedStatement.setBoolean(1, isAvailable);
            preparedStatement.setString(2, foodName);

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Food availability updated successfully.");
            } else {
                System.out.println("Food not found in the database.");
            }

        } catch (SQLException ex) {
            System.err.println("SQL error occurred:");
            ex.printStackTrace();
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

    public void orderRoomServices(String userEmail, int roomNumber, String foodName, double price) {
        String jdbcUrl = "jdbc:derby:HotelRecords;create=true"; // Update with your JDBC URL

        try (Connection connection = DriverManager.getConnection(jdbcUrl); PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO OrderRecords (UserID, RoomNumber, FoodName, Price) VALUES (?, ?, ?, ?)")) {

            // Step 1: Retrieve UserID based on the email
            int userId = getUserIdByEmail(userEmail);

            System.out.println("UserID: " + userId); // Add this line for debugging
            System.out.println("RoomNumber: " + roomNumber); // Add this line for debugging

            if (userId != -1) {
                // Step 2: Insert the order information into OrderRecords
                preparedStatement.setInt(1, userId);
                preparedStatement.setInt(2, roomNumber);
                preparedStatement.setString(3, foodName);
                preparedStatement.setDouble(4, price);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    // Successfully added the order, you can add any additional logic here
                    System.out.println("Order saved successfully.");
                } else {
                    System.out.println("Order not saved.");
                }
            } else {
                // Handle the case where the user was not found
                System.out.println("User not found. Cannot place the order.");
            }
        } catch (SQLException e) {
            System.err.println("SQL error occurred:");
            e.printStackTrace();
        }
    }

}
