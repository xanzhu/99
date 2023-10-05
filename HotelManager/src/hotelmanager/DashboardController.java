package hotelmanager;

import java.awt.event.ActionEvent;

/**
 *
 * @author Bobby Jenkins, Hyun il Jun
 */
public class DashboardController {

    private StaffDashboardView staffDash;
    private UserDashboardView userDash;

    // Constructor for Staff Dashboard
    public DashboardController(StaffDashboardView staffDash) {
        this.staffDash = staffDash;
        staffLogout();
    }

    // Constructor for User Dashboard
    public DashboardController(UserDashboardView userDash) {
        this.userDash = userDash;
        userLogout();
    }

    // Handle Logging out for Staff
    private void staffLogout() {
        staffDash.getLogoutButton().addActionListener((ActionEvent e) -> {
            LoginView loginView = new LoginView();
            DBManager db = new DBManager();
            LoginController loginController = new LoginController(loginView, db);

            loginView.setVisible(true);

            staffDash.dispose();
        });
    }

    // Handle Logging out for Users
    private void userLogout() {
        userDash.getLogoutButton().addActionListener((ActionEvent e) -> {
            LoginView loginView = new LoginView();
            DBManager db = new DBManager();
            LoginController loginController = new LoginController(loginView, db);

            loginView.setVisible(true);

            userDash.dispose();
        });
    }

}
