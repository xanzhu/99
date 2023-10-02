package hotelmanager;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author bobby
 */
public class WelcomeView extends JFrame {
    
    private final JButton loginButton;
    private final JButton registerButton;

    WelcomeView() {
        setBounds(100, 80, 1280, 720);
        setResizable(false);
        
        ImageIcon bgImage = new ImageIcon(ClassLoader.getSystemResource("Assets/plane.jpg"));
        JLabel image = new JLabel(bgImage);
        add(image);
        
        JLabel text = new JLabel("Hotel System");
        text.setBounds(140, 220, 1000, 80);
        text.setFont(new Font("sans serif", Font.BOLD, 50));
        text.setHorizontalAlignment(JLabel.CENTER);
        text.setForeground(Color.WHITE);
        image.add(text);
        
        registerButton = new JButton("Register");
        registerButton.setBounds(500, 400, 270, 50);
        registerButton.setFont(new Font("sans serif", Font.PLAIN, 15));
        image.add(registerButton);

        loginButton = new JButton("Login");
        loginButton.setBounds(500, 490, 270, 50);
        loginButton.setFont(new Font("sans serif", Font.PLAIN, 15));
        image.add(loginButton);
                
        setVisible(true);
        
        // Close
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public JButton getLoginButton() {
        return loginButton;
    }
    
    public JButton getRegisterButton(){
        return registerButton;
    }
}
