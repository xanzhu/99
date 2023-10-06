package hotelmanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Test_Rooms {

     /**
     * DEBUG: Database Test Function
     * Returns all Rooms from Database
     * @param args 
     */
    public static void main(String[] args) {
        DBManager dbManager = new DBManager();

        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM RoomRecords");
             ResultSet resultSet = preparedStatement.executeQuery()) {

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
