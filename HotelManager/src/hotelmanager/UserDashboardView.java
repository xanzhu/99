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
 * @author bobby
 */
public class UserDashboardView extends JFrame {

    private final JButton logoutBtn;
    private final JLabel nameField;

    private final String loginEmail;

    private final BookingView bookingView;
    private final RoomServiceView roomServiceView;

    private final AppUtils u;

    UserDashboardView(String userName, String userEmail) {
        this.loginEmail = userEmail;
        this.bookingView = new BookingView();
        this.roomServiceView = new RoomServiceView();

        // Load in reusable elements
        this.u = new AppUtils();

        setBounds(100, 80, 1280, 720);
        setResizable(false);
        setLayout(null);

        JLabel title = new JLabel("Dashboard");
        title.setFont(u.formatText(30, true));
        title.setBounds(1075, 20, 500, 30);
        add(title);

        JPanel Sidebar = new JPanel();
        Sidebar.setBounds(0, 0, 250, 720);
        Sidebar.setBackground(u.userColour());
        Sidebar.setLayout(null);
        add(Sidebar);

        nameField = new JLabel(userName);
        nameField.setBounds(0, 100, 250, 200);
        nameField.setVerticalAlignment(JLabel.CENTER);
        nameField.setHorizontalAlignment(JLabel.CENTER);
        nameField.setFont(u.formatText(20, true));
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
        RoomServiceGUI();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public JButton getLogoutButton() {
        return logoutBtn;
    }

    private JButton BookingBtn;
    private JButton ServiceBtn;
    private JButton NewBookingBtn;

    private void BookingGUI() {

        boolean hasBooking = bookingView.NewBookingCheck(loginEmail);

        // Define buttons for use
        ServiceBtn = new JButton("Room Service");
        NewBookingBtn = new JButton("Book a room");
        BookingBtn = new JButton("My Booking");
        
        BookingBtn.setBounds(350, 160, 150, 150);
        BookingBtn.setFont(u.formatText(16));
        BookingBtn.setLayout(null);
        add(BookingBtn);
        BookingBtn.setVisible(hasBooking);

        NewBookingBtn.setBounds(350, 160, 150, 150);
        NewBookingBtn.setFont(u.formatText(16));
        NewBookingBtn.setLayout(null);
        add(NewBookingBtn);
        
        // Add Booking
        JButton AddBookingBtn = new JButton("Add Booking");
        AddBookingBtn.setLayout(null);
        AddBookingBtn.setBounds(350, 160, 150, 150);
        AddBookingBtn.setFont(u.formatText(16));
        add(AddBookingBtn);
        AddBookingBtn.setVisible(false);

        // View Booking
        JButton ViewBookingBtn = new JButton("View Booking");
        ViewBookingBtn.setLayout(null);
        ViewBookingBtn.setBounds(550, 160, 150, 150);
        ViewBookingBtn.setFont(u.formatText(16));
        add(ViewBookingBtn);
        ViewBookingBtn.setVisible(false);

        // Cancel Booking
        JButton CancelBookingBtn = new JButton("Cancel Booking");
        CancelBookingBtn.setLayout(null);
        CancelBookingBtn.setBounds(750, 160, 150, 150);
        CancelBookingBtn.setFont(u.formatText(16));
        add(CancelBookingBtn);
        CancelBookingBtn.setVisible(false);

        // Return Button
        JButton ReturnBtn = new JButton("Return");
        ReturnBtn.setLayout(null);
        ReturnBtn.setBounds(350, 70, 150, 50);
        ReturnBtn.setFont(u.formatText(16));
        add(ReturnBtn);
        ReturnBtn.setVisible(false);

        ReturnBtn.addActionListener((ActionEvent e) -> {

            boolean returnCheck = bookingView.NewBookingCheck(loginEmail);

            if (returnCheck) {
                btnState(true, BookingBtn, ServiceBtn);
                btnState(false, AddBookingBtn, ViewBookingBtn, CancelBookingBtn, ReturnBtn);
            } else {
                btnState(true, NewBookingBtn);
                btnState(false, AddBookingBtn, ReturnBtn, CancelBookingBtn, ViewBookingBtn);
            }
        });

        AddBookingBtn.addActionListener((ActionEvent e) -> {
            bookingView.addBookingGUI();
        });

        ViewBookingBtn.addActionListener((ActionEvent e) -> {
            bookingView.viewBookingGUI(loginEmail);
        });

        CancelBookingBtn.addActionListener((ActionEvent e) -> {
            bookingView.cancelBookingGUI(loginEmail);
        });

        // Existing Bookings
        BookingBtn.addActionListener((ActionEvent e) -> {
            btnState(false, BookingBtn, ServiceBtn, NewBookingBtn);
            btnState(true, AddBookingBtn, ViewBookingBtn, CancelBookingBtn, ReturnBtn);
        });

        // New Bookings
        NewBookingBtn.addActionListener((ActionEvent e) -> {
            btnState(true, AddBookingBtn, ReturnBtn);
            btnState(false, NewBookingBtn);
        });
    }

    private void RoomServiceGUI() {
        ServiceBtn.setBounds(550, 160, 150, 150);
        ServiceBtn.setFont(u.formatText(16));
        ServiceBtn.setLayout(null);
        add(ServiceBtn);
        ServiceBtn.setVisible(false);

        // View Menu
        JButton ViewMenu = new JButton("View Room Services");
        ViewMenu.setLayout(null);
        ViewMenu.setBounds(350, 160, 200, 100);
        ViewMenu.setFont(u.formatText(16));
        add(ViewMenu);
        ViewMenu.setVisible(false);

        // Order Menu
        JButton OrderMenu = new JButton("Order Room Services");
        OrderMenu.setLayout(null);
        OrderMenu.setBounds(570, 160, 200, 100);
        OrderMenu.setFont(u.formatText(16));
        add(OrderMenu);
        OrderMenu.setVisible(false);

        // Return 
        JButton SReturnBtn = new JButton("Return");
        SReturnBtn.setLayout(null);
        SReturnBtn.setBounds(350, 70, 150, 50);
        SReturnBtn.setFont(u.formatText(15));
        add(SReturnBtn);
        SReturnBtn.setVisible(false);

        // Listeners:
        ViewMenu.addActionListener((ActionEvent e) -> {
            roomServiceView.viewRoomServicesGUI();
        });

        OrderMenu.addActionListener((ActionEvent e) -> {
            roomServiceView.OrderFoodGUI(loginEmail);
        });

        // Button Visibility
        SReturnBtn.addActionListener((ActionEvent e) -> {
            btnState(true, BookingBtn, ServiceBtn);
            btnState(false, ViewMenu, OrderMenu, SReturnBtn);
        });

        ServiceBtn.addActionListener((ActionEvent e) -> {
            btnState(true, ViewMenu, OrderMenu, SReturnBtn);
            btnState(false, BookingBtn, ServiceBtn);
        });
    }

    private void btnState(boolean isVisible, Component... buttons) {
        for (Component button : buttons) {
            button.setVisible(isVisible);
        }
    }

    public static void main(String[] args) {
        UserDashboardView userDash = new UserDashboardView("Yuhwan Kim", "example@example.com");
    }
}
