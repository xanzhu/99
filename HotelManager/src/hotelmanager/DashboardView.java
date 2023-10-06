package hotelmanager;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Bobby Jenkins, Hyun il Jun
 */
public abstract class DashboardView extends JFrame {

    protected JButton manageRoomBtn;
    protected JButton roomServiceBtn;
    protected JButton bookingsBtn;

    protected JButton logoutBtn;

    protected JButton returnBtn;
    protected JButton viewBookingBtn;
    protected JButton addBookingBtn;

    protected JPanel sidebar;

    protected String emailField;
    protected JLabel nameField;

    protected final BookingView bookingView;
    protected final RoomServiceView roomServiceView;
    protected final BillingView billingView;
    protected final AppUtils u;

    DashboardView(String userName, String userEmail) {

        this.bookingView = new BookingView();
        this.roomServiceView = new RoomServiceView();
        this.billingView = new BillingView();
        this.u = new AppUtils();

        this.emailField = userEmail;

        setSidebarColor(u.staffColour());

        setBounds(100, 80, 1280, 720);
        setResizable(false);
        setLayout(null);

        // Load default profile
        ImageIcon profileImg = new ImageIcon(ClassLoader.getSystemResource("Assets/profile.png"));
        JLabel profileLabel = new JLabel(profileImg);
        profileLabel.setBounds(75, 60, 100, 100);
        sidebar.add(profileLabel);

        // Name
        nameField = new JLabel(userName);
        nameField.setBounds(0, 100, 250, 200);
        nameField.setVerticalAlignment(JLabel.CENTER);
        nameField.setHorizontalAlignment(JLabel.CENTER);
        nameField.setFont(u.formatText(20, true));
        nameField.setForeground(Color.WHITE);
        sidebar.add(nameField);

        // Email
        JLabel email = new JLabel(userEmail);
        email.setBounds(0, 130, 250, 200);
        email.setVerticalAlignment(JLabel.CENTER);
        email.setHorizontalAlignment(JLabel.CENTER);
        email.setForeground(Color.WHITE);
        sidebar.add(email);

        // Log out
        logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(35, 620, 170, 40);
        logoutBtn.setForeground(Color.BLACK);
        logoutBtn.setBackground(Color.WHITE);
        sidebar.add(logoutBtn);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    protected void BookingGUI() {

        bookingsBtn.setBounds(750, 160, 150, 150);
        bookingsBtn.setFont(u.formatText(16));
        bookingsBtn.setLayout(null);
        add(bookingsBtn);

        addBookingBtn = new JButton("Add Booking");
        addBookingBtn.setLayout(null);
        addBookingBtn.setBounds(350, 160, 150, 150);
        addBookingBtn.setFont(u.formatText(16));
        add(addBookingBtn);
        addBookingBtn.setVisible(false);

        returnBtn = new JButton("Return");
        returnBtn.setLayout(null);
        returnBtn.setBounds(350, 70, 150, 50);
        returnBtn.setFont(u.formatText(15));
        add(returnBtn);
        returnBtn.setVisible(false);

        viewBookingBtn = new JButton("View Booking");
        viewBookingBtn.setLayout(null);
        viewBookingBtn.setBounds(550, 160, 150, 150);
        viewBookingBtn.setFont(u.formatText(16));
        add(viewBookingBtn);
        viewBookingBtn.setVisible(false);

        addBookingBtn.addActionListener((ActionEvent e) -> {
            bookingView.addBookingGUI();
        });
    }

    // Define Dashboard titles
    protected void setDashboardTitle(String titleText) {
        JLabel title = new JLabel(titleText);
        title.setFont(u.formatText(30, true));
        title.setBounds(1000, 20, 500, 30);
        add(title);
    }

    // Reusable Button State
    protected void btnState(boolean isVisible, Component... btn) {
        for (Component b : btn) {
            b.setVisible(isVisible);
        }
    }

    // Getters - Check if this should be public or protected
    protected JButton getManageRoomBtn() {
        return manageRoomBtn;
    }

    protected JButton getRoomServiceBtn() {
        return roomServiceBtn;
    }

    protected JButton getBookingsBtn() {
        return bookingsBtn;
    }

    protected JButton getAddBookingButton() {
        return addBookingBtn;
    }

    protected String getEmailFieldText() {
        return emailField;
    }

    protected JButton getLogoutButton() {
        return logoutBtn;
    }

    protected void setSidebarColor(Color color) {
        sidebar = new JPanel(); // Use the instance variable sidebar
        sidebar.setBounds(0, 0, 250, 720);
        sidebar.setBackground(color);
        sidebar.setLayout(null);
        add(sidebar);
    }
}
