package hotelmanager;

import java.awt.event.ActionEvent;

/**
 *
 * @author bobby
 */
public class DashboardController {

    private StaffDashboardView staffDash;
    private UserDashboardView userDash;

    public DashboardController(StaffDashboardView staffDash) {
        this.staffDash = staffDash;
        staffLogout();
    }

    public DashboardController(UserDashboardView userDash) {
        this.userDash = userDash;
        userLogout();
    }

    private void staffLogout() {
        staffDash.getLogoutButton().addActionListener((ActionEvent e) -> {
            LoginView loginView = new LoginView();
            DBManager db = new DBManager();
            LoginController loginController = new LoginController(loginView, db);

            loginView.setVisible(true);

            staffDash.dispose();
        });
    }

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
