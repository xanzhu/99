package bobbyhyunhotel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateRoomServicesRecords {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:derby:HotelRecords;create=true"; // Update this with your URL

        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

            Connection connection = DriverManager.getConnection(jdbcUrl);

            // Create a SQL statement
            Statement statement = connection.createStatement();
            
            // Define the SQL statement to create the table for room records
            String createTableSQL = "CREATE TABLE RoomServicesRecords (" +
                    "ID INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 100), " +
                    "FoodName VARCHAR(255), " +
                    "FoodType VARCHAR(255), " +
                    "Price DECIMAL(10, 2), " +
                    "IsAvailable BOOLEAN, " +
                    "PRIMARY KEY (ID))";

            // Execute the SQL statement to create the table
            statement.execute(createTableSQL);

            System.out.println("Table 'RoomServicesRecords' created successfully.");

            // Close the statement and connection
            statement.close();
            connection.close();

        } catch (ClassNotFoundException e) {
            System.err.println("Derby JDBC driver not found. Make sure you have Derby in your classpath.");
        } catch (SQLException e) {
            System.err.println("SQL error occurred:");
            e.printStackTrace();
        }
    }
}
