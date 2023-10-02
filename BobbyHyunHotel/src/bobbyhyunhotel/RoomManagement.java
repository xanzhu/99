package bobbyhyunhotel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoomManagement {

    public static void addRoom(int roomNumber, String roomType, double price, boolean isAvailable) {
        // JDBC URL for connecting to the Derby embedded database
        String jdbcUrl = "jdbc:derby:HotelRecords;create=true";

        try (Connection connection = DriverManager.getConnection(jdbcUrl);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO RoomRecords (RoomNumber, RoomType, Price, IsAvailable) VALUES (?, ?, ?, ?)")) {

            preparedStatement.setInt(1, roomNumber);
            preparedStatement.setString(2, roomType);
            preparedStatement.setDouble(3, price);
            preparedStatement.setBoolean(4, isAvailable);

            preparedStatement.executeUpdate();

            System.out.println("Room information saved to the database successfully.");

        } catch (SQLException ex) {
            System.err.println("SQL error occurred:");
            ex.printStackTrace();
        }
    }
    
        public static void removeRoom(int roomNumber) {
        // JDBC URL for connecting to the Derby embedded database
        String jdbcUrl = "jdbc:derby:HotelRecords;create=true";

        try (Connection connection = DriverManager.getConnection(jdbcUrl);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM RoomRecords WHERE RoomNumber = ?")) {

            preparedStatement.setInt(1, roomNumber);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Room information for Room Number " + roomNumber + " deleted successfully.");
            } else {
                System.out.println("Room with Room Number " + roomNumber + " not found.");
            }

        } catch (SQLException ex) {
            System.err.println("SQL error occurred:");
            ex.printStackTrace();
        }
    }
        
        public void updateRoomPrice(int roomNumber, double newPrice) {
        // JDBC URL for connecting to the Derby embedded database
        String jdbcUrl = "jdbc:derby:HotelRecords;create=true";

        try (Connection connection = DriverManager.getConnection(jdbcUrl);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE RoomRecords SET Price = ? WHERE RoomNumber = ?")) {

            preparedStatement.setDouble(1, newPrice);
            preparedStatement.setInt(2, roomNumber);

            int updatedRows = preparedStatement.executeUpdate();

            if (updatedRows > 0) {
                System.out.println("Room price updated successfully.");
            } else {
                System.out.println("Room not found. Price update failed.");
            }

        } catch (SQLException ex) {
            System.err.println("SQL error occurred:");
            ex.printStackTrace();
        }
    }
     
        public void updateRoomAvailability(int roomNumber, boolean isAvailable) {
        // JDBC URL for connecting to the Derby embedded database
        String jdbcUrl = "jdbc:derby:HotelRecords;create=true";

        try (Connection connection = DriverManager.getConnection(jdbcUrl);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE RoomRecords SET IsAvailable = ? WHERE RoomNumber = ?")) {

            preparedStatement.setBoolean(1, isAvailable);
            preparedStatement.setInt(2, roomNumber);

            int updatedRows = preparedStatement.executeUpdate();

            if (updatedRows > 0) {
                System.out.println("Room availability updated successfully.");
            } else {
                System.out.println("Room not found or availability not updated.");
            }

        } catch (SQLException ex) {
            System.err.println("SQL error occurred:");
            ex.printStackTrace();
        }
    }


}
