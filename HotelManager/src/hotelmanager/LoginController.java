package hotelmanager;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author bobby
 */
public class LoginController {

    private final LoginView loginView;
    private final DBManager dbManager;

    public LoginController(LoginView loginView, DBManager dbManager) {
        this.loginView = loginView;
        this.dbManager = dbManager;
        LoginButtonListener();
        ReturnButtonListener();
    }

    private void LoginButtonListener() {
        loginView.getLoginButton().addActionListener((ActionEvent e) -> {
            String email = loginView.getEmail();
            String password = loginView.getPassword();

            // Check User Input
            if (checkInput(email, password)) {
                // Check input against database
                if (validateData(email, password)) {
                    // Get name from Database! (TODO: Add support for other variables)
                    String userName = dbManager.RetrieveName(email);

                    if (email.endsWith("@hotel.com")) {
                        openStaffDashboard(userName, email);
                    } else {
                        openUserDashboard(userName, email);
                    }
                } else {
                    showErrorMessage("Incorrect email or password.\nPlease try again.");
                }
            }
        });
    }

    private boolean validateData(String email, String password) {
        if (checkInput(email, password)) {
            return dbManager.checkCredentials(email, password);
        }

        return false;
    }

    private boolean checkInput(String email, String password) {
        if (email.isEmpty()) {
            showErrorMessage("Please enter your email address.\nExample: john.smith@gmail.com");
            return false;
        } else if (password.isEmpty()) {
            showErrorMessage("Please enter a password!");
            return false;
        }
        return true;
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Login Error", JOptionPane.PLAIN_MESSAGE);
    }

    private void ReturnButtonListener() {
        loginView.getReturnButton().addActionListener((ActionEvent e) -> {
            WelcomeView welcomeView = new WelcomeView();
            WelcomeController welcomeController = new WelcomeController(welcomeView);
            welcomeView.setVisible(true);

            loginView.dispose();
        });
    }

    private void openStaffDashboard(String userName, String userEmail) {
        StaffDashboardView staffDash = new StaffDashboardView(userName, userEmail);
        DashboardController dashController = new DashboardController(staffDash);
        
        staffDash.setVisible(true);

        loginView.dispose();
    }

    private void openUserDashboard(String userName, String userEmail) {
        UserDashboardView userDash = new UserDashboardView(userName, userEmail);
        DashboardController dashController = new DashboardController(userDash);
        
        userDash.setVisible(true);

        loginView.dispose();
    }
}
