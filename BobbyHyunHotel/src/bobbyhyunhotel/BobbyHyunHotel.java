package bobbyhyunhotel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import java.awt.event.*;
/**
 *
 * @author junhy
 */
public class BobbyHyunHotel extends JFrame{

    BobbyHyunHotel(){
        //setLocation(100,100);
        //setSize(1000,500);
        setBounds(100,100,1000,560);
        
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "Do you want to exit the application?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    dispose(); // Close the JFrame
                    System.exit(0); // Exit the application
                }
            }
        });
        
       // Create a JPanel with a GridBagLayout to center the buttons
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Add some padding

        JButton registerButton = new JButton("Register");
        JButton loginButton = new JButton("Login");

        // Set the preferred size for the buttons
        Dimension buttonSize = new Dimension(150, 30);
        registerButton.setPreferredSize(buttonSize);
        loginButton.setPreferredSize(buttonSize);

        // Add the buttons to the panel using GridBagConstraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(registerButton, gbc);

        gbc.gridy = 1;
        panel.add(loginButton, gbc);
        
         registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the registration GUI when the "Register" button is clicked
                new RegisterGUI();
            }
        });
        
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Login(); // Open the login GUI when the "Login" button is clicked
            }
        });
        
        // Add the panel to the center of the JFrame
        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }
  
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BobbyHyunHotel();
            }
        });
    }
}




 
