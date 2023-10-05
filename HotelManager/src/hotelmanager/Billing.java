package hotelmanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bobby Jenkins, Hyun il Jun
 */
public class Billing {

    private final DBManager dbManager;
    private RoomManagement rm;

    public Billing(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    /**
     * Room Price function
     * Match UserID across BookingRecords and UserData
     * then return price uses Email to match.
     * 
     * @param userEmail
     * @return roomPrice
    */
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

    /**
     * Total Room Services Price
     * Gets the total price from OrderRecords
     * using UserID.
     * 
     * @param userEmail
     * @return 
     */
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

    /**
     * Display Record Function
     * Converts db entries into Empty ArrayList
     * for use in GUI
     * 
     * @return records
     */
    public List<Records> displayRecords() {
        List<Records> records = new ArrayList<>();

        String query = "SELECT * FROM BookingRecords";

        Connection connection = dbManager.getConnection();

        if (connection != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query); ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    int bookingID = resultSet.getInt("BookingID");
                    int userID = resultSet.getInt("UserID");
                    int roomNumber = resultSet.getInt("RoomNumber");
                    java.sql.Date bookingDate = resultSet.getDate("BookingDate");

                    Records record = new Records(bookingID, userID, roomNumber, bookingDate);
                    records.add(record);
                }

            } catch (SQLException ex) {
                System.err.println("Error displaying records: " + ex.getMessage());
            }
        }

        return records;
    }

     /**
     * Display Food Records Function
     * Converts db entries into Empty ArrayList
     * for use in GUI
     * 
     * @return records
     */
    public List<Records> displayFoodRecords() {
        List<Records> records = new ArrayList<>();

        String query = "SELECT * FROM OrderRecords";

        Connection connection = dbManager.getConnection();

        if (connection != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query); ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {

                    int orderID = resultSet.getInt("OrderID");
                    int userID = resultSet.getInt("UserID");
                    int roomNumber = resultSet.getInt("RoomNumber");
                    String foodName = resultSet.getString("FoodName");
                    double price = resultSet.getDouble("Price");

                    Records record = new Records(orderID, userID, roomNumber, foodName, price);
                    records.add(record);
                }

            } catch (SQLException ex) {
                System.err.println("Error displaying records: " + ex.getMessage());
            }
        }
        return records;
    }
}
