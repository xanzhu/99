package bobbyhyunhotel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author junhy
 */
public class Billing {

    public double getRoomPrice(String userEmail) {
        double roomPrice = 0.0;

        String jdbcUrl = "jdbc:derby:HotelRecords;create=true"; // Update with your JDBC URL

        try (Connection connection = DriverManager.getConnection(jdbcUrl); PreparedStatement preparedStatement = connection.prepareStatement("SELECT RoomNumber FROM BookingRecords WHERE UserID = (SELECT ID FROM UserData WHERE Email = ?)")) {
            preparedStatement.setString(1, userEmail);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int roomNumber = resultSet.getInt("RoomNumber");

                    try (PreparedStatement roomPriceStatement = connection.prepareStatement("SELECT Price FROM RoomRecords WHERE RoomNumber = ?")) {
                        roomPriceStatement.setInt(1, roomNumber);

                        try (ResultSet roomPriceResultSet = roomPriceStatement.executeQuery()) {
                            if (roomPriceResultSet.next()) {
                                roomPrice = roomPriceResultSet.getDouble("Price");
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL error occurred while retrieving room price:");
            e.printStackTrace();
        }

        return roomPrice;
    }

    public double getTotalRoomServicesPrice(String userEmail) {
        double totalRoomServicesPrice = 0.0;

        String jdbcUrl = "jdbc:derby:HotelRecords;create=true"; // Update with your JDBC URL

        try (Connection connection = DriverManager.getConnection(jdbcUrl); PreparedStatement preparedStatement = connection.prepareStatement("SELECT SUM(Price) AS TotalPrice FROM OrderRecords WHERE UserID = (SELECT ID FROM UserData WHERE Email = ?)")) {
            preparedStatement.setString(1, userEmail);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    totalRoomServicesPrice = resultSet.getDouble("TotalPrice");
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL error occurred while retrieving total room services price:");
            e.printStackTrace();
        }

        return totalRoomServicesPrice;
    }

}
