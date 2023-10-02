package bobbyhyunhotel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test4 {

    public static void main(String[] args) {
        // JDBC URL for connecting to the Derby embedded database
        String jdbcUrl = "jdbc:derby:HotelRecords;create=true";

        try (Connection connection = DriverManager.getConnection(jdbcUrl);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM BookingRecords");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            // Iterate through the result set and print the booking records
            while (resultSet.next()) {
                int bookingID = resultSet.getInt("BookingID");
                int userID = resultSet.getInt("UserID");
                int roomNumber = resultSet.getInt("RoomNumber");
                java.sql.Date bookingDate = resultSet.getDate("BookingDate");

                System.out.println("Booking ID: " + bookingID);
                System.out.println("User ID: " + userID);
                System.out.println("Room Number: " + roomNumber);
                System.out.println("Booking Date: " + bookingDate);
                System.out.println("------------------------");
            }

        } catch (SQLException ex) {
            System.err.println("SQL error occurred:");
            ex.printStackTrace();
        }
    }
}
