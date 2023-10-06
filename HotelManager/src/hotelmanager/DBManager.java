package hotelmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Bobby Jenkins, Hyun il Jun
 */
public final class DBManager {

    // Established the embedded db
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

    // Debug method: Run DBManager
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
            } catch (SQLException ex) {
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

    /**
     * Creates User Data Table
     */
    public void createHotelData() {
        try {
            Statement statement = conn.createStatement();

            // Check if the table already exists using MetaData (Fixes already exists db issue)
            ResultSet tables = conn.getMetaData().getTables(null, null, "USERDATA", null);
            if (!tables.next()) {
                String createTableSQL = "CREATE TABLE UserData ("
                        + "ID INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
                        + "Name VARCHAR(255), "
                        + "Age INT, "
                        + "Address VARCHAR(255), "
                        + "Phone VARCHAR(15), "
                        + "Email VARCHAR(255), "
                        + "Password VARCHAR(255), "
                        + "PRIMARY KEY (ID))";

                statement.execute(createTableSQL);

                System.out.println("Table 'UserData' created successfully.");
            } else {
                System.out.println("Table 'UserData' already exists.");
            }

            statement.close();

        } catch (SQLException e) {
            System.err.println("SQL error occurred: " + e.getMessage());
        }
    }

    /**
    * Creates Room Records Table
    */
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

    /**
     * Creates Room Service Records Table
     */
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

    /**
     * Creates Booking Records Table
     */
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

    /**
     * Creates Order Records Table
     */
    public void createOrderData() {
        try {
            try (Statement statement = conn.createStatement()) {
                ResultSet tables = conn.getMetaData().getTables(null, null, "ORDERRECORDS", null);
                if (!tables.next()) {
                    String createOrderData = "CREATE TABLE OrderRecords ("
                            + "OrderID INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
                            + "UserID INT, "
                            + "RoomNumber INT, "
                            + "FoodName VARCHAR(255), "
                            + "Price DECIMAL(10, 2), "
                            + "FOREIGN KEY (UserID) REFERENCES UserData(ID), "
                            + "FOREIGN KEY (RoomNumber) REFERENCES RoomRecords(RoomNumber), "
                            + "PRIMARY KEY (OrderID))";

                    statement.execute(createOrderData);

                    System.out.println("Table 'Order Records' created successfully.");
                } else {
                    System.out.println("Table 'Order Records' already exists.");
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL error occurred: " + e.getMessage());
        }
    }

    /**
     * Save User Data 
     * Takes input from Register Field saves into db
     * 
     * @param name
     * @param age
     * @param address
     * @param phone
     * @param email
     * @param password 
     */
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

    /**
     * Credentials Function
     * Gets Email and Password from db
     * 
     * @param email
     * @param password
     * @return 
     */
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

    /**
     * Retrieve Name Function
     * Gets Name from db
     * 
     * @param email
     * @return 
     */
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
            ex.printStackTrace();
        }
        return userName;
    }
}
