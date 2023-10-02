package bobbyhyunhotel;

/**
 *
 * @author junhy
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

public class Register {

  public static void saveUserData(String name, int age, String address, String phone, String email, String password) {
        // JDBC URL for connecting to the Derby embedded database
        String jdbcUrl = "jdbc:derby:HotelRecords;create=true";

        try (Connection connection = DriverManager.getConnection(jdbcUrl);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO UserData (Name, Age, Address, Phone, Email, Password) VALUES (?, ?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, address);
            preparedStatement.setString(4, phone);
            preparedStatement.setString(5, email);
            preparedStatement.setString(6,password); // Convert char[] to String for storage

            preparedStatement.executeUpdate();

            System.out.println("Data saved to the database successfully.");

            // Clear the password array immediately after use
            //Arrays.fill(password, ' ');

        } catch (SQLException ex) {
            System.err.println("SQL error occurred:");
            ex.printStackTrace();
        }
    }

}
