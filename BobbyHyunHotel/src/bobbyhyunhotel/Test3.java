package bobbyhyunhotel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test3 {

    public static void main(String[] args) {
        // JDBC URL for connecting to the Derby embedded database
        String jdbcUrl = "jdbc:derby:HotelRecords;create=true";

        try (Connection connection = DriverManager.getConnection(jdbcUrl);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM RoomServicesRecords");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            // Iterate through the result set and print the room services records
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String foodName = resultSet.getString("FoodName");
                String foodType = resultSet.getString("FoodType");
                double price = resultSet.getDouble("Price");
                boolean isAvailable = resultSet.getBoolean("IsAvailable");

                System.out.println("ID: " + id);
                System.out.println("Food Name: " + foodName);
                System.out.println("Food Type: " + foodType);
                System.out.println("Price: " + price);
                System.out.println("Is Available: " + isAvailable);
                System.out.println("------------------------");
            }

        } catch (SQLException ex) {
            System.err.println("SQL error occurred:");
            ex.printStackTrace();
        }
    }
}

