package bobbyhyunhotel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterGUI extends JFrame {

    private JTextField nameField;
    private JTextField ageField;
    private JTextField addressField;
    private JTextField phoneField;
    private JTextField emailField;
    private JPasswordField passwordField;

    public RegisterGUI() {
        setTitle("Register");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(7, 2));
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);

        JLabel ageLabel = new JLabel("Age:");
        ageField = new JTextField(5);

        JLabel addressLabel = new JLabel("Address:");
        addressField = new JTextField(20);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneField = new JTextField(15);

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String address = addressField.getText();
                String phone = phoneField.getText();
                String email = emailField.getText();
                String password = getPasswordAsString(); // Retrieve password as String

                // Call the Register class to save the user data to the database
                Register.saveUserData(name, age, address, phone, email, password);

                // Close the registration window
                dispose();
            }
        });

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(ageLabel);
        panel.add(ageField);
        panel.add(addressLabel);
        panel.add(addressField);
        panel.add(phoneLabel);
        panel.add(phoneField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(registerButton);

        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new RegisterGUI();
            }
        });
    }

    // Custom method to retrieve the password as a String
    private String getPasswordAsString() {
        return new String(passwordField.getPassword());
    }
}
