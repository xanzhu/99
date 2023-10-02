/*
 * Hello world
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelmanager;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author bobby
 */
public class UserDashboardView extends JFrame {

    private final JButton logoutBtn;
    private final JLabel nameField;

    UserDashboardView(String userName, String userEmail) {
        setBounds(100, 80, 1280, 720);
        setResizable(false);
        setLayout(null);

        JLabel title = new JLabel("Dashboard");
        title.setFont(new Font("sans serif", Font.BOLD, 30));
        title.setBounds(1075, 20, 500, 30);
        add(title);

        JPanel Sidebar = new JPanel();
        Sidebar.setBounds(0, 0, 250, 720);
        Sidebar.setBackground(Color.decode("#0096FF"));
        Sidebar.setLayout(null);
        add(Sidebar);

        nameField = new JLabel(userName);
        nameField.setBounds(0, 100, 250, 200);
        nameField.setVerticalAlignment(JLabel.CENTER);
        nameField.setHorizontalAlignment(JLabel.CENTER);
        nameField.setFont(new Font("sans serif", Font.BOLD, 20));
        nameField.setForeground(Color.WHITE);
        Sidebar.add(nameField);

        JLabel email = new JLabel(userEmail);
        email.setBounds(0, 130, 250, 200);
        email.setVerticalAlignment(JLabel.CENTER);
        email.setHorizontalAlignment(JLabel.CENTER);
        email.setForeground(Color.WHITE);
        Sidebar.add(email);

        logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(35, 620, 170, 40);
        Sidebar.add(logoutBtn);
        
        ImageIcon profileImg = new ImageIcon(ClassLoader.getSystemResource("Assets/profile.png"));
        JLabel profileLabel = new JLabel(profileImg);
        profileLabel.setBounds(75, 60, 100, 100);
        Sidebar.add(profileLabel);
  
        setVisible(true);
    }

    public JButton getLogoutButton() {
        return logoutBtn;
    }

    public static void main(String[] args) {
        UserDashboardView userDashboardView = new UserDashboardView("Yuhwan", "example@example.com");
        userDashboardView.setVisible(true);
    }
}
