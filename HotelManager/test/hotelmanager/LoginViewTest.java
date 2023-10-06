package hotelmanager;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author junhy
 */
public class LoginViewTest {

    private LoginView loginView;

    @Before
    public void setUp() {
        loginView = new LoginView();
    }

    /**
     * Verify Email and Passwords Fields are not null.
     */
    @Test
    public void verifyFields() {
        assertNotNull(loginView.getEmail());
        assertNotNull(loginView.getPassword());
    }

    /**
     * Verify Login & Return are loaded correctly.
     */
    @Test
    public void verifyButtons() {
        assertNotNull(loginView.getLoginButton());
        assertNotNull(loginView.getReturnButton());
    }

    /**
     * Verify Login Button.
     */
    @Test
    public void verifyLoginBtn() {
        JButton loginButton = loginView.getLoginButton();
        assertEquals("Login", loginButton.getText());
        assertTrue(loginButton.isVisible());
    }

    /**
     * Verify Return Button.
     */
    @Test
    public void verifyReturnBtn() {
        JButton returnBtn = loginView.getReturnButton();
        assertEquals("Return", returnBtn.getText());
        assertTrue(returnBtn.isVisible());
    }
    
    /**
     * Verify Profile Image is Visible.
     */
    @Test
    public void verifyProfileImage(){
        ImageIcon loginBG = loginView.getLoginBG();
        assertNotNull(loginBG);

        assertNotNull(loginBG.getImage());
    }
}