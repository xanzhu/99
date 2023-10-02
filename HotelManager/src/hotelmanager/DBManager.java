package hotelmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author bobby
 */
public final class DBManager {

    private static final String USER_NAME = "hotel";
    private static final String PASSWORD = "hotel";
    private static final String URL = "jdbc:derby:HotelDB; create=true";

    private static DBManager instance;

    Connection conn;

    public DBManager() {
        establishConnection();
    }

    public static synchronized DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    public static void main(String[] args) {
        DBManager dbManager = new DBManager();
        System.out.println(dbManager.getConnection());
    }

    public Connection getConnection() {
        return this.conn;
    }

    //Establish connection
    public void establishConnection() {
        if (this.conn == null) {
            try {
                conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
                System.out.println(URL + " Get Connected Successfully ....");
            } catch (SQLException ex) {
                // Print the error message and stack trace for debugging
                ex.printStackTrace();
                System.err.println("Error: " + ex.getMessage());
            }
        }
    }

    public void closeConnections() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    // Testing
    public void commitPendingTransactions() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.commit();
            }
        } catch (SQLException ex) {
            // Handle the exception, e.g., log it or throw it
            ex.printStackTrace();
        }
    }

    // Test shutdown server: 
    public void shutdown() {
        try {
            if (conn != null && !conn.isClosed()) {
                // The DriverManager.getConnection method with "shutdown=true" is used to shut down Derby.
                // This is the proper way to shut down Derby gracefully.
                DriverManager.getConnection("jdbc:derby:;shutdown=true");
                System.out.println("Derby shutdown successful.");
            }
        } catch (SQLException ex) {
            // If you get a SQLException here, it's expected and can be ignored.
            // Derby throws an exception when it's shut down properly.
            if (ex.getSQLState().equals("XJ015")) {
                System.out.println("Derby shut down successfully.");
            } else {
                System.err.println("Derby shutdown failed: " + ex.getMessage());
            }
        }
    }

    public void createHotelData() {
        try {
            Statement statement = conn.createStatement();

            // Check if the table already exists using MetaData (Fixes already exists db issue)
            ResultSet tables = conn.getMetaData().getTables(null, null, "USERDATA", null);
            if (!tables.next()) {
                // Table doesn't exist, so create it
                String createTableSQL = "CREATE TABLE UserData ("
                        + "ID INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
                        + "Name VARCHAR(255), "
                        + "Age INT, "
                        + "Address VARCHAR(255), "
                        + "Phone VARCHAR(15), "
                        + "Email VARCHAR(255), "
                        + "Password VARCHAR(255), "
                        + "PRIMARY KEY (ID))";

                // Execute the SQL statement to create the table
                statement.execute(createTableSQL);

                System.out.println("Table 'UserData' created successfully.");
            } else {
                System.out.println("Table 'UserData' already exists.");
            }

            // Close the statement
            statement.close();

        } catch (SQLException e) {
            System.err.println("SQL error occurred: " + e.getMessage());
        }
    }

    public void createRoomData() {
        try {
            try (Statement statement = conn.createStatement()) {
                ResultSet tables = conn.getMetaData().getTables(null, null, "ROOMRECORDS", null);
                if (!tables.next()) {
                    String createRoomRecords = "CREATE TABLE RoomRecords ("
                            + "RoomNumber INT NOT NULL, "
                            + "RoomType VARCHAR(255), "
                            + "Price DECIMAL(10, 2), "
                            + "IsAvailable BOOLEAN, "
                            + "PRIMARY KEY (RoomNumber))";

                    statement.execute(createRoomRecords);

                    System.out.println("Table 'RoomRecords' created successfully.");
                } else {
                    System.out.println("Table 'RoomRecords' already exists.");
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL error occurred: " + e.getMessage());
        }
    }

    public void createRoomServiceData() {
        try {
            try (Statement statement = conn.createStatement()) {
                ResultSet tables = conn.getMetaData().getTables(null, null, "ROOMSERVICESRECORDS", null);
                if (!tables.next()) {
                    String RoomServicesRecords = "CREATE TABLE RoomServicesRecords ("
                            + "ID INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 100), "
                            + "FoodName VARCHAR(255), "
                            + "FoodType VARCHAR(255), "
                            + "Price DECIMAL(10, 2), "
                            + "IsAvailable BOOLEAN, "
                            + "PRIMARY KEY (ID))";

                    statement.execute(RoomServicesRecords);

                    System.out.println("Table 'RoomServicesRecords' created successfully.");
                } else {
                    System.out.println("Table 'RoomServicesRecords' already exists.");
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL error occurred: " + e.getMessage());
        }
    }

    public void createBookingData() {
        try {
            try (Statement statement = conn.createStatement()) {
                ResultSet tables = conn.getMetaData().getTables(null, null, "BOOKINGRECORDS", null);
                if (!tables.next()) {
                    String BookingRecords = "CREATE TABLE BookingRecords ("
                            + "BookingID INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
                            + "UserID INT, "
                            + "RoomNumber INT, "
                            + "BookingDate DATE, "
                            + "FOREIGN KEY (UserID) REFERENCES UserData(ID), "
                            + "FOREIGN KEY (RoomNumber) REFERENCES RoomRecords(RoomNumber), "
                            + "PRIMARY KEY (BookingID))";

                    statement.execute(BookingRecords);

                    System.out.println("Table 'Booking Records' created successfully.");
                } else {
                    System.out.println("Table 'Booking Records' already exists.");
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL error occurred: " + e.getMessage());
        }
    }

    //
    public void saveUserData(String name, int age, String address, String phone, String email, String password) {
        try {
            if (conn != null) {
                PreparedStatement preparedStatement = conn.prepareStatement(
                        "INSERT INTO UserData (Name, Age, Address, Phone, Email, Password) VALUES (?, ?, ?, ?, ?, ?)");

                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, age);
                preparedStatement.setString(3, address);
                preparedStatement.setString(4, phone);
                preparedStatement.setString(5, email);
                preparedStatement.setString(6, password);

                preparedStatement.executeUpdate();

                System.out.println("Data saved to the database successfully.");
            } else {
                System.err.println("Connection to the database is not established.");
            }
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }

    public boolean checkCredentials(String email, String password) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "SELECT * FROM UserData WHERE Email = ? AND Password = ?");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            return false;
        }
    }

    public String RetrieveName(String email) {
        String userName = null;

        try {
            Statement statement = conn.createStatement();

            String sql = "SELECT Name FROM UserData WHERE Email = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                userName = resultSet.getString("Name");
            }

            resultSet.close();
            preparedStatement.close();
            conn.close();
        } catch (SQLException ex) {
            // Handle any SQL exceptions
            ex.printStackTrace();
        }
        return userName;
    }
}
