package bobbyhyunhotel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class CreateHotelRecords {
    public static void main(String[] args) throws SQLException {
        String jdbcUrl = "jdbc:derby:HotelRecords;create=true"; // Update this with your URL

        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

            Connection connection = DriverManager.getConnection(jdbcUrl);

            // Create a SQL statement
            Statement statement = connection.createStatement();

            // Define the SQL statement to create the table with a hashed password column
            String createTableSQL = "CREATE TABLE UserData (" +
                    "ID INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 100), " +
                    "Name VARCHAR(255), " +
                    "Age INT, " +
                    "Address VARCHAR(255), " +
                    "Phone VARCHAR(15), " +
                    "Email VARCHAR(255), " +
                    "Password VARCHAR(64), " + 
                    "PRIMARY KEY (ID))";

            // Execute the SQL statement to create the table
            statement.execute(createTableSQL);

            // Close the statement and connection
            statement.close();
            connection.close();

            System.out.println("Table 'UserData' created successfully.");

        } catch (ClassNotFoundException e) {
            System.err.println("Derby JDBC driver not found. Make sure you have Derby in your classpath.");
        } catch (SQLException e) {
            System.err.println("SQL error occurred:");
        }
    }
}
