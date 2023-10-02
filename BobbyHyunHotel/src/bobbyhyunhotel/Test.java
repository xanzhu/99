
package bobbyhyunhotel;

/**
 *
 * @author junhy
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {

    public static void main(String[] args) {
        // JDBC URL for connecting to the Derby embedded database
        String jdbcUrl = "jdbc:derby:HotelRecords;create=true";

        try (Connection connection = DriverManager.getConnection(jdbcUrl);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM UserData");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            // Iterate through the result set and print the data
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("Name");
                int age = resultSet.getInt("Age");
                String address = resultSet.getString("Address");
                String phone = resultSet.getString("Phone");
                String email = resultSet.getString("Email");
                String password = resultSet.getString("Password");

                System.out.println("ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Age: " + age);
                System.out.println("Address: " + address);
                System.out.println("Phone: " + phone);
                System.out.println("Email: " + email);
                System.out.println("Password: " + password);
                System.out.println("------------------------");
            }

        } catch (SQLException ex) {
            System.err.println("SQL error occurred:");
            ex.printStackTrace();
        }
    }
}
