package hotelmanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bobby
 */
public class Billing {

    private final DBManager dbManager;
    private RoomManagement rm;

    public Billing(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public double getRoomPrice(String userEmail) {
        double roomPrice = 0.0;

        String query = "SELECT RoomNumber FROM BookingRecords WHERE UserID = (SELECT ID FROM UserData WHERE Email = ?)";

        Connection connection = dbManager.getConnection();

        if (connection != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
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
            } catch (SQLException ex) {
                System.err.println("Error getting price of room: " + ex.getMessage());
            }
        }

        return roomPrice;
    }

    public double getTotalRoomServicesPrice(String userEmail) {
        double totalRoomServicesPrice = 0.0;

        String query = "SELECT SUM(Price) AS TotalPrice FROM OrderRecords WHERE UserID = (SELECT ID FROM UserData WHERE Email = ?)";

        Connection connection = dbManager.getConnection();

        if (connection != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, userEmail);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        totalRoomServicesPrice = resultSet.getDouble("TotalPrice");
                    }
                }
            } catch (SQLException ex) {
                System.err.println("Error getting total of room: " + ex.getMessage());
            }
        }

        return totalRoomServicesPrice;
    }

    public List<Book> displayRecords() {
        List<Book> records = new ArrayList<>();

        String query = "SELECT * FROM BookingRecords";
        
        Connection connection = dbManager.getConnection();

        if (connection != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query); ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    int bookingID = resultSet.getInt("BookingID");
                    int userID = resultSet.getInt("UserID");
                    int roomNumber = resultSet.getInt("RoomNumber");
                    java.sql.Date bookingDate = resultSet.getDate("BookingDate");

                    Book record = new Book(bookingID, userID, roomNumber, bookingDate);
                    records.add(record);
                }

            } catch (SQLException ex) {
                System.err.println("Error displaying records: " + ex.getMessage());
            }
        }

        return records;
    }

}
