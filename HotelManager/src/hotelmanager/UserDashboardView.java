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
public class UserDashboardView extends JFrame {

    private final JButton logoutBtn;
    private final JLabel nameField;

    private final String loginEmail;

    private final BookingView bookingView;
    private final RoomServiceView roomServiceView;
    private final BillingView billingView;
    
    private JButton BookingBtn;
    private JButton ServiceBtn;
    private JButton NewBookingBtn;
    private JButton BillingBtn;
    
    private JButton AddBookingBtn;
    private JButton ViewMenu;

    private final AppUtils u;
    
    private boolean isAddBookingGUIInvoked = false;

    public JButton getAddBookingBtn() {
        return AddBookingBtn;
    }

    public JButton getBookingBtn() {
        return BookingBtn;
    }

    public JButton getServiceBtn() {
        return ServiceBtn;
    }

    public JButton getNewBookingBtn() {
        return NewBookingBtn;
    }

    public JButton getBillingBtn() {
        return BillingBtn;
    }
    
    public JButton getLogoutButton() {
        return logoutBtn;
    }
    
    public JButton getViewBilling() {
        return ViewMenu;
    }

    // Default Constructor
    UserDashboardView(String userName, String userEmail) {
        this.loginEmail = userEmail;
        this.bookingView = new BookingView();
        this.roomServiceView = new RoomServiceView();
        this.billingView = new BillingView();

        // Load in reusable font elements
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
        BillingGUI();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Booking Function
     * Creates GUI to handle bookings based 
     * on existing or new user
     * 
     * New user has limited option
     * Existing user with booking full menu
     */
    private void BookingGUI() {

        boolean hasBooking = bookingView.NewBookingCheck(loginEmail);

        // Define main menu buttons for use
        ServiceBtn = new JButton("Room Service");
        NewBookingBtn = new JButton("Book a room");
        BookingBtn = new JButton("My Bookings");
        BillingBtn = new JButton("Charges");

        BookingBtn.setBounds(350, 160, 150, 150);
        BookingBtn.setFont(u.formatText(16));
        BookingBtn.setLayout(null);
        add(getBookingBtn());
        BookingBtn.setVisible(hasBooking);

        // Hide elements for non booked users
        if (!hasBooking) {
            ServiceBtn.setVisible(false);
            BillingBtn.setVisible(false);
        }

        NewBookingBtn.setBounds(350, 160, 150, 150);
        NewBookingBtn.setFont(u.formatText(16));
        NewBookingBtn.setLayout(null);
        add(NewBookingBtn);

        // Add Booking
        AddBookingBtn = new JButton("Add Booking");
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
                btnState(true, getNewBookingBtn());
                btnState(false, AddBookingBtn, ReturnBtn, CancelBookingBtn, ViewBookingBtn, getServiceBtn());
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
        getBookingBtn().addActionListener((ActionEvent e) -> {
            btnState(false, BookingBtn, ServiceBtn, NewBookingBtn, BillingBtn);
            btnState(true, AddBookingBtn, ViewBookingBtn, CancelBookingBtn, ReturnBtn);
        });

        // New Bookings
        getNewBookingBtn().addActionListener((ActionEvent e) -> {
            btnState(true, AddBookingBtn, ReturnBtn);
            btnState(false, NewBookingBtn, ServiceBtn, BillingBtn);
        });

        AddBookingBtn.addActionListener((ActionEvent e) -> {
            isAddBookingGUIInvoked = true;
            bookingView.addBookingGUI();
        });

    }

    /**
     * Room Service Function
     * Creates Room Service GUI to order/view from menu
     */
    private void RoomServiceGUI() {
        ServiceBtn.setBounds(550, 160, 150, 150);
        ServiceBtn.setFont(u.formatText(16));
        ServiceBtn.setLayout(null);
        add(ServiceBtn);

        // View Menu
        ViewMenu = new JButton("View Room Services");
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
            btnState(true, BookingBtn, ServiceBtn, BillingBtn);
            btnState(false, ViewMenu, OrderMenu, SReturnBtn);
        });

        getServiceBtn().addActionListener((ActionEvent e) -> {
            btnState(true, ViewMenu, OrderMenu, SReturnBtn);
            btnState(false, BookingBtn, ServiceBtn, NewBookingBtn, BillingBtn);
        });
    }

    // Displays current Charges to user
    private void BillingGUI() {
        BillingBtn.setBounds(750, 160, 150, 150);
        BillingBtn.setFont(u.formatText(16));
        BillingBtn.setLayout(null);
        add(BillingBtn);

        BillingBtn.addActionListener((ActionEvent e) -> {
            billingView.getUserBillingGUI(loginEmail);
        });
    }

    // Reusable Function for setting Button Visibility
    private void btnState(boolean isVisible, Component... btn) {
        for (Component b : btn) {
            b.setVisible(isVisible);
        }
    }
    
    // DEBUG - View User Dashboard
    public static void main(String[] args) {
        UserDashboardView userDash = new UserDashboardView("test", "test");
    }
}
