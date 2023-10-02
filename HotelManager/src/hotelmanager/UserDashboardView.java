package hotelmanager;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
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
        
        // Display Menu Options GUI
        BookingGUI();
  
        setVisible(true);
    }

    public JButton getLogoutButton() {
        return logoutBtn;
    }
    
    private JButton BookingBtn;
    
    private void btnState(boolean isVisible, Component... button) {
        for (Component b : button) {
            b.setVisible(isVisible);
        }
    }
    
    private void BookingGUI(){
        
        // TODO: Add logic to display / My Bookings / Book room?
        
        BookingBtn = new JButton("My Booking");
        BookingBtn.setBounds(350, 160, 150, 150);
        BookingBtn.setFont(new Font("sans serif", Font.PLAIN, 16));
        BookingBtn.setLayout(null);
        add(BookingBtn);
        
        // Add Booking
        JButton AddBookingBtn = new JButton("Add Booking");
        AddBookingBtn.setLayout(null);
        AddBookingBtn.setBounds(350, 160, 150, 150);
        AddBookingBtn.setFont(new Font("sans serif", Font.PLAIN, 16));
        add(AddBookingBtn);
        AddBookingBtn.setVisible(false);
        
        // View Booking
        JButton ViewBookingBtn = new JButton("View Booking");
        ViewBookingBtn.setLayout(null);
        ViewBookingBtn.setBounds(550, 160, 150, 150);
        ViewBookingBtn.setFont(new Font("sans serif", Font.PLAIN, 16));
        add(ViewBookingBtn);
        ViewBookingBtn.setVisible(false);
        
        // Cancel Booking
        JButton CancelBookingBtn = new JButton("Cancel Booking");
        CancelBookingBtn.setLayout(null);
        CancelBookingBtn.setBounds(750, 160, 150, 150);
        CancelBookingBtn.setFont(new Font("sans serif", Font.PLAIN, 16));
        add(CancelBookingBtn);
        CancelBookingBtn.setVisible(false);
        
        // Return Button
        JButton ReturnBtn = new JButton("Return");
        ReturnBtn.setLayout(null);
        ReturnBtn.setBounds(350, 70, 150, 50);
        ReturnBtn.setFont(new Font("sans serif", Font.PLAIN, 15));
        add(ReturnBtn);
        ReturnBtn.setVisible(false);
        
        // TO:DO Action Listeners for GUI
//        AddBookingBtn.addActionListener((ActionEvent e) -> { });
//        ViewBookingBtn.addActionListener((ActionEvent e) -> { });
//        CancelBookingBtn.addActionListener((ActionEvent e) -> { });
        
        // Button Visibility
        ReturnBtn.addActionListener((ActionEvent e) -> {
            btnState(true, BookingBtn);
            btnState(false, AddBookingBtn, ViewBookingBtn, CancelBookingBtn, ReturnBtn);
        });

        BookingBtn.addActionListener((ActionEvent e) -> {
            btnState(true, AddBookingBtn, ViewBookingBtn, CancelBookingBtn, ReturnBtn);
            btnState(false, BookingBtn);
        });    
    }
   
    public static void main(String[] args) {
        UserDashboardView userDashboardView = new UserDashboardView("Yuhwan", "example@example.com");
        userDashboardView.setVisible(true);
    }
}
