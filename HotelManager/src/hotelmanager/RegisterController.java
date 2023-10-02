/*
 * Hello world
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelmanager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author bobby
 */
public class RegisterController {

    DBManager dbManager;
    Connection conn;
    Statement statement;

    RegisterController() {
        dbManager = new DBManager();
        conn = dbManager.getConnection();
    }

    public void connectHotelDB() {
        try {
            statement = conn.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void registerUser(String name, int age, String address, String phone, String email, String password) {

        if (verifyRegister(name, age, address, phone, email, password)) {
            dbManager.saveUserData(name, age, address, phone, email, password);

            showSuccessMessage("Successfully Registered!\nPlease login.");

            openLoginView();
        }
    }

    public boolean verifyRegister(String name, int age, String address, String phone, String email, String password) {
        return checkAge(age) && checkFields(name, address, phone, email, password) && checkEmail(email);
    }

    private void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Registration Success", JOptionPane.PLAIN_MESSAGE);
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Registration Error", JOptionPane.PLAIN_MESSAGE);
    }

    // Set age limits
    public boolean checkAge(int age) {
        if (age < 18) {
            showErrorMessage("Failed to Register\nMust be 18 or older.");
            return false;
        } else if (age > 120) {
            showErrorMessage("Exceeds Age requirements\nEnter a valid age.");
            return false;
        }
        return true;
    }

    // Check for empty fields
    public boolean checkFields(String name, String address, String phone, String email, String password) {
        if (name.isEmpty() || address.isEmpty() || phone.isEmpty() || email.isEmpty() || password.isEmpty()) {
            showErrorMessage("All fields are required to register!");
            return false;
        }

        // TODO: Support for empty age - currently causes error!
        return true;
    }

    // Check if the email already exists in the database
    public boolean checkEmail(String email) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM UserData WHERE Email = ?");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                showErrorMessage("An account has been registered with this email already!\nUse a different email address.");
                return false;
            } else {
                return true;
            }
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            return false;
        }
    }

    // Opens Login Menu
    public static void openLoginView() {
        RegisterView rv = new RegisterView();
        rv.dispose();

        // Navigate to Login Menu!
        LoginView loginView = new LoginView();
        DBManager db = new DBManager();
        
        LoginController loginController = new LoginController(loginView, db);
        loginView.setVisible(true);
    }
}
