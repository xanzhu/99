package bobbyhyunhotel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateRoomsRecords {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:derby:HotelRecords;create=true"; // Update this with your URL

        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

            Connection connection = DriverManager.getConnection(jdbcUrl);

            // Create a SQL statement
            Statement statement = connection.createStatement();
            
            // Define the SQL statement to create the table for room records
            String createTableSQL = "CREATE TABLE RoomRecords (" +
                    "RoomNumber INT NOT NULL, " +
                    "RoomType VARCHAR(255), " +
                    "Price DECIMAL(10, 2), " +
                    "IsAvailable BOOLEAN, " +
                    "PRIMARY KEY (RoomNumber))";

            // Execute the SQL statement to create the table
            statement.execute(createTableSQL);

            System.out.println("Table 'RoomRecords' created successfully.");

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
