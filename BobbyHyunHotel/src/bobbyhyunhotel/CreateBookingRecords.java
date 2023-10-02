
package bobbyhyunhotel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateBookingRecords {
    public static void main(String[] args) throws SQLException {
        String jdbcUrl = "jdbc:derby:HotelRecords;create=true"; // Update this with your URL

        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

            Connection connection = DriverManager.getConnection(jdbcUrl);

            // Create a SQL statement
            Statement statement = connection.createStatement();

            // Define the SQL statement to create the table with Booking information
            String createTableSQL = "CREATE TABLE BookingRecords (" +
                    "BookingID INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
                    "UserID INT, " +
                    "RoomNumber INT, " +
                    "BookingDate DATE, " +
                    "FOREIGN KEY (UserID) REFERENCES UserData(ID), " +
                    "FOREIGN KEY (RoomNumber) REFERENCES RoomRecords(RoomNumber), " +
                    "PRIMARY KEY (BookingID))";

            // Execute the SQL statement to create the table
            statement.execute(createTableSQL);

            // Close the statement and connection
            statement.close();
            connection.close();

            System.out.println("Table 'BookingRecords' created successfully.");

        } catch (ClassNotFoundException e) {
            System.err.println("Derby JDBC driver not found. Make sure you have Derby in your classpath.");
        } catch (SQLException e) {
            System.err.println("SQL error occurred:");
            e.printStackTrace();
        }
    }
}
