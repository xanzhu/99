package bobbyhyunhotel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test5 {

    public static void main(String[] args) {
        // JDBC URL for connecting to the Derby embedded database
        String jdbcUrl = "jdbc:derby:HotelRecords;create=true";

        try (Connection connection = DriverManager.getConnection(jdbcUrl);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM OrderRecords");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            // Iterate through the result set and print the order records
            while (resultSet.next()) {
                int orderID = resultSet.getInt("OrderID");
                int userID = resultSet.getInt("UserID");
                int roomNumber = resultSet.getInt("RoomNumber");
                String foodName = resultSet.getString("FoodName");
                double price = resultSet.getDouble("Price");

                System.out.println("Order ID: " + orderID);
                System.out.println("User ID: " + userID);
                System.out.println("Room Number: " + roomNumber);
                System.out.println("Food Name: " + foodName);
                System.out.println("Price: $" + price);
                System.out.println("------------------------");
            }

        } catch (SQLException ex) {
            System.err.println("SQL error occurred:");
            ex.printStackTrace();
        }
    }
}

