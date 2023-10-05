package hotelmanager;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author Bobby Jenkins, Hyun il Jun
 */
public class LoginController {

    private final LoginView loginView;
    private final DBManager dbManager;

    // Default Constructor
    public LoginController(LoginView loginView, DBManager dbManager) {
        this.loginView = loginView;
        this.dbManager = dbManager;
        
        // Loads in Listeners
        LoginButtonListener();
        ReturnButtonListener();
    }

    /**
     * Login Button Listener
     * Extracts email from db for validation.
     */
    private void LoginButtonListener() {
        loginView.getLoginButton().addActionListener((ActionEvent e) -> {
            String email = loginView.getEmail();
            String password = loginView.getPassword();

            // Check User Input
            if (checkInput(email, password)) {
                // Check input against database
                if (validateData(email, password)) {
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

    // Validates Email and Password Against Database.
    private boolean validateData(String email, String password) {
        if (checkInput(email, password)) {
            return dbManager.checkCredentials(email, password);
        }

        return false;
    }

    // Validates Login input
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

    // Reusable Error Message
    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Login Error", JOptionPane.PLAIN_MESSAGE);
    }

    // Returns to main menu
    private void ReturnButtonListener() {
        loginView.getReturnButton().addActionListener((ActionEvent e) -> {
            WelcomeView welcomeView = new WelcomeView();
            WelcomeController welcomeController = new WelcomeController(welcomeView);
            welcomeView.setVisible(true);

            loginView.dispose();
        });
    }

    // Load Staff Dashboard after logging in.
    private void openStaffDashboard(String userName, String userEmail) {
        StaffDashboardView staffDash = new StaffDashboardView(userName, userEmail);
        DashboardController dashController = new DashboardController(staffDash);
        
        staffDash.setVisible(true);

        loginView.dispose();
    }

    // Load User Dashboard after logging in.
    private void openUserDashboard(String userName, String userEmail) {
        UserDashboardView userDash = new UserDashboardView(userName, userEmail);
        DashboardController dashController = new DashboardController(userDash);
        
        userDash.setVisible(true);

        loginView.dispose();
    }
}
