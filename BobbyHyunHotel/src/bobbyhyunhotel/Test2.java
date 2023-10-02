package bobbyhyunhotel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test2 {

    public static void main(String[] args) {
        // JDBC URL for connecting to the Derby embedded database
        String jdbcUrl = "jdbc:derby:HotelRecords;create=true";

        try (Connection connection = DriverManager.getConnection(jdbcUrl);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM RoomRecords");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            // Iterate through the result set and print the room records
            while (resultSet.next()) {
                int roomNumber = resultSet.getInt("RoomNumber");
                String roomType = resultSet.getString("RoomType");
                double price = resultSet.getDouble("Price");
                boolean isAvailable = resultSet.getBoolean("IsAvailable");

                System.out.println("Room Number: " + roomNumber);
                System.out.println("Room Type: " + roomType);
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
