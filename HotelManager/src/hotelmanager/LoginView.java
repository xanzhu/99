package hotelmanager;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author bobby Test
 */
public class LoginView extends JFrame {

    private final JButton returnBtn;
    private final JButton loginBtn;
    private JTextField email;
    private JPasswordField password;
    private JTextField name;
    private ImageIcon loginBG;
    
    private final AppUtils u;

    LoginView() {
        
        // Load Application Utils
        this.u = new AppUtils();
        
        setBounds(100, 80, 1280, 720);
        setResizable(false);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel text = new JLabel("Hotel Login");
        text.setBounds(148, 70, 1000, 80);
        text.setFont(u.formatText(50, true));
        
        text.setForeground(Color.BLACK);
        add(text);

        JLabel userEmail = new JLabel("EMAIL");
        userEmail.setFont(u.formatText(12));
        userEmail.setBounds(150, 200, 100, 35);
        add(userEmail);

        email = new JTextField();
        email.setToolTipText("Enter an email address");
        email.setBackground(Color.decode("#f3f6f4"));
        email.setBounds(148, 230, 300, 40);
        add(email);

        JLabel pass = new JLabel("PASSWORD");
        pass.setFont(u.formatText(12));
        pass.setBounds(150, 300, 100, 40);
        add(pass);

        password = new JPasswordField();
        password.setToolTipText("Enter a password");
        password.setBackground(Color.decode("#f3f6f4"));
        password.setBounds(148, 330, 300, 40);
        add(password);

        loginBtn = new JButton("Login");
        loginBtn.setBounds(150, 510, 300, 40);
        loginBtn.setBackground(Color.BLACK);
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setOpaque(true);
        loginBtn.setBorderPainted(false);
        add(loginBtn);

        returnBtn = new JButton("Return");
        returnBtn.setBounds(150, 590, 300, 40);
        returnBtn.setOpaque(true);
        returnBtn.setBorderPainted(false);
        add(returnBtn);
        
        // Background Image!
        loginBG = new ImageIcon(ClassLoader.getSystemResource("Assets/loginBG.png"));
        JLabel imageLabel = new JLabel(loginBG);
        JPanel ImagePanel = new JPanel();
        ImagePanel.setBounds(640, -5, 640, 720);
        ImagePanel.add(imageLabel);
        add(ImagePanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Getters
    public JButton getLoginButton() {
        return loginBtn;
    }

    public JButton getReturnButton() {
        return returnBtn;
    }
    
    public String getEmail() {
        return email.getText();
    }

    public String getPassword() {
        return new String(password.getPassword());
    }

    public ImageIcon getLoginBG() {
        return loginBG;
    }

    // Debug: Displays Login GUI
    public static void main(String[] args) {
        new LoginView();
    }
}
