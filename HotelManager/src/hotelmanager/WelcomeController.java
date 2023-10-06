package hotelmanager;

/**
 *
 * @author bobby
 */
import java.awt.event.ActionEvent;

public class WelcomeController {

    private final WelcomeView welcomeView;

    // Default Constructor
    public WelcomeController(WelcomeView welcomeView) {
        this.welcomeView = welcomeView;
        addLoginButtonListener();
        addRegisterButtonListener();
    }

    // Handle Login Button - LoadsGUI
    private void addLoginButtonListener() {
        welcomeView.getLoginButton().addActionListener((ActionEvent e) -> {
            LoginView loginView = new LoginView();
            DBManager db = new DBManager();
            LoginController loginController = new LoginController(loginView, db);
            loginView.setVisible(true);

            welcomeView.dispose();
        });
    }

    // Handle Register Button - Loads RegisterGUI
    private void addRegisterButtonListener() {
        welcomeView.getRegisterButton().addActionListener((ActionEvent e) -> {
            RegisterView registerView = new RegisterView();
            registerView.setVisible(true);

            welcomeView.dispose();
        });
    }
}
