package bobbyhyunhotel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

// Hello

public class Login extends JFrame {
    private static String loggedInUserEmail;
    
    Login() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "Do you want to exit the application?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    dispose(); // Close the JFrame
                    System.exit(0); // Exit the application
                }
            }
        });
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JButton login = new JButton("Login");
        login.setBounds(30, 120, 90, 30);
        add(login);

        JLabel user = new JLabel("Email");
        user.setBounds(30, 20, 90, 30);
        add(user);
        JTextField email = new JTextField();
        email.setBounds(130, 20, 120, 30);
        add(email);

        JLabel password = new JLabel("Password");
        password.setBounds(30, 50, 90, 30);
        add(password);
        JPasswordField pw = new JPasswordField();
        pw.setBounds(130, 50, 120, 30);
        add(pw);

        // Add an ActionListener to the login button
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userEmail = email.getText();
                String userPassword = new String(pw.getPassword()); // Retrieve password as a string

                // Call a method to verify email and password
                if (verifyCredentials(userEmail, userPassword)) {
                    loggedInUserEmail = userEmail;
                    if (userEmail.endsWith("@hotel.com")) {
                        openStaffDashboard();
                    } else {
                        openUserDashboard();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid email or password.");
                }
            }
        });

        // Setting the background size
        setBounds(100, 100, 500, 300);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Login();
    }

    // Method to verify email and password
    private boolean verifyCredentials(String email, String password) {
        // JDBC URL for connecting to the Derby embedded database
        String jdbcUrl = "jdbc:derby:HotelRecords;create=true";

        try (Connection connection = DriverManager.getConnection(jdbcUrl);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM UserData WHERE Email = ? AND Password = ?")) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next(); // If there's a match in the database, return true

        } catch (SQLException ex) {
            System.err.println("SQL error occurred:");
            ex.printStackTrace();
        }

        return false;
    }

    private void openStaffDashboard() {
        // Replace this with the code to open the StaffDashboard or call its constructor
        StaffDashboard staffDashboard = new StaffDashboard();
        //staffDashboard.setVisible(true);
        dispose();
    }

    private void openUserDashboard() {
        // Replace this with the code to open the UserDashboard or call its constructor
        UserDashboard userDashboard = new UserDashboard(loggedInUserEmail);
        //userDashboard.setVisible(true);
        dispose();
    }
}
