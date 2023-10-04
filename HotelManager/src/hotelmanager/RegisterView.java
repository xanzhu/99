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
 * @author bobby
 */
public class RegisterView extends JFrame {

    private JTextField nameField;
    private JTextField ageField;
    private JTextField addressField;
    private JTextField phoneField;
    private JTextField emailField;
    private JPasswordField passwordField;
    
    private final AppUtils u;

    RegisterView() {
        this.u = new AppUtils();
        
        setBounds(100, 80, 1280, 720);
        setLayout(null);
        setResizable(false);
        getContentPane().setBackground(Color.WHITE);

        JLabel text = new JLabel("Hotel Register");
        text.setBounds(118, 50, 1000, 80);
        text.setFont(u.formatText(50, true));
        text.setForeground(Color.BLACK);
        add(text);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(150, 190, 100, 40);
        nameLabel.setFont(u.formatText(15));
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(270, 190, 170, 40);
        nameField.setToolTipText("Enter your name.");
        nameField.setBackground(Color.decode("#f3f6f4"));
        add(nameField);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(150, 250, 100, 40);
        ageLabel.setFont(u.formatText(15));
        add(ageLabel);

        ageField = new JTextField();
        ageField.setBounds(270, 250, 170, 40);
        ageField.setToolTipText("Enter your age.");
        ageField.setBackground(Color.decode("#f3f6f4"));
        add(ageField);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(150, 310, 100, 40);
        addressLabel.setFont(u.formatText(15));
        add(addressLabel);

        addressField = new JTextField();
        addressField.setToolTipText("Enter your Address.");
        addressField.setBounds(270, 310, 170, 40);
        addressField.setBackground(Color.decode("#f3f6f4"));
        add(addressField);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(150, 370, 100, 40);
        phoneLabel.setFont(u.formatText(15));
        add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setToolTipText("Enter phone number.");
        phoneField.setBounds(270, 370, 170, 40);
        phoneField.setBackground(Color.decode("#f3f6f4"));
        add(phoneField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(150, 430, 100, 40);
        emailLabel.setFont(u.formatText(15));
        add(emailLabel);

        emailField = new JTextField();
        emailField.setToolTipText("Enter email address.");
        emailField.setBounds(270, 430, 170, 40);
        emailField.setBackground(Color.decode("#f3f6f4"));
        add(emailField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(150, 490, 100, 40);
        passwordLabel.setFont(u.formatText(15));
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setToolTipText("Enter a password.");
        passwordField.setBounds(270, 490, 170, 40);
        passwordField.setBackground(Color.decode("#f3f6f4"));
        add(passwordField);

        JButton registerBtn = new JButton("Register");
        registerBtn.setBounds(150, 590, 290, 40);
        registerBtn.setBackground(Color.BLACK);
        registerBtn.setForeground(Color.WHITE);
        registerBtn.setOpaque(true);
        registerBtn.setBorderPainted(false);
        add(registerBtn);

        // Background - Image
        ImageIcon loginBG = new ImageIcon(ClassLoader.getSystemResource("Assets/loginBG.png"));
        JLabel imageLabel = new JLabel(loginBG);
        JPanel ImagePanel = new JPanel();
        ImagePanel.setBounds(640, -5, 640, 720);
        ImagePanel.add(imageLabel);
        add(ImagePanel);

        // TODO: Add return button
        
        registerBtn.addActionListener(e -> {
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String address = addressField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            RegisterController registerController = new RegisterController();

            registerController.registerUser(name, age, address, phone, email, password);
            
            setVisible(false);
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

// Testing
    public static void main(String[] args) {
        new RegisterView();
    }
}
