package hotelmanager;

/**
 *
 * @author bobby
 */
import java.awt.event.ActionEvent;

public class WelcomeController {

    private final WelcomeView welcomeView;

    public WelcomeController(WelcomeView welcomeView) {
        this.welcomeView = welcomeView;
        addLoginButtonListener();
        addRegisterButtonListener();
    }

    private void addLoginButtonListener() {
        welcomeView.getLoginButton().addActionListener((ActionEvent e) -> {
            LoginView loginView = new LoginView();
            DBManager db = new DBManager();
            LoginController loginController = new LoginController(loginView, db);
            loginView.setVisible(true);

            welcomeView.dispose();
        });
    }

    private void addRegisterButtonListener() {
        welcomeView.getRegisterButton().addActionListener((ActionEvent e) -> {
            RegisterView registerView = new RegisterView();
            registerView.setVisible(true);

            welcomeView.dispose();
        });
    }
}
