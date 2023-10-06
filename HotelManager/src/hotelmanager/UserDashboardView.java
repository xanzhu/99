package hotelmanager;

import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

/**
 *
 * @author Bobby Jenkins, Hyun il Jun
 */
public class UserDashboardView extends DashboardView {

//    private final JButton logoutBtn;
//    private final JLabel nameField;

//    private final String loginEmail;

    private final BookingView bookingView;
//    private final RoomServiceView roomServiceView;
    private final BillingView billingView;
    
//    private JButton BookingBtn;
//    private JButton ServiceBtn;
    private JButton NewBookingBtn;
    private JButton BillingBtn;
    
//    private JButton AddBookingBtn;
    private JButton ViewMenu;

//    private final AppUtils u;

//    public JButton getAddBookingBtn() {
//        return addBookingBtn;
//    }

//    public JButton getBookingBtn() {
//        return bookingsBtn;
//    }

//    public JButton getServiceBtn() {
//        return roomServiceBtn;
//    }

    public JButton getNewBookingBtn() {
        return NewBookingBtn;
    }

    public JButton getBillingBtn() {
        return BillingBtn;
    }
//    
//    public JButton getLogoutButton() {
//        return logoutBtn;
//    }
    
    public JButton getViewBilling() {
        return ViewMenu;
    }

    // Default Constructor
    UserDashboardView(String userName, String userEmail) {
        super(userName, userEmail);
        setDashboardTitle("User Dashboard");
        
//        this.loginEmail = userEmail;
        this.bookingView = new BookingView();
//        this.roomServiceView = new RoomServiceView();
        this.billingView = new BillingView();

        // Display Menu Options GUI
        LoadButtons();
        BookingGUI();
        RoomServiceGUI();
        BillingGUI();

//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setVisible(true);
    }

    /**
     * Booking Function
     * Creates GUI to handle bookings based 
     * on existing or new user
     * 
     * New user has limited option
     * Existing user with booking full menu
     */
    private void LoadButtons() {
        roomServiceBtn = new JButton("Room Service");
        NewBookingBtn = new JButton("Book a room");
        bookingsBtn = new JButton("My Bookings");
        BillingBtn = new JButton("Charges");
    }
    
    protected void BookingGUI() {
        super.BookingGUI();
        
        boolean hasBooking = bookingView.NewBookingCheck(emailField);

        bookingsBtn.setBounds(350, 160, 150, 150);
        bookingsBtn.setFont(u.formatText(16));
        bookingsBtn.setLayout(null);
        add(bookingsBtn);
        bookingsBtn.setVisible(hasBooking);

        // Hide elements for non booked users
        if (!hasBooking) {
            roomServiceBtn.setVisible(false);
            BillingBtn.setVisible(false);
        }

        NewBookingBtn.setBounds(350, 160, 150, 150);
        NewBookingBtn.setFont(u.formatText(16));
        NewBookingBtn.setLayout(null);
        add(NewBookingBtn);

        // Add Booking
//        AddBookingBtn = new JButton("Add Booking");
//        AddBookingBtn.setLayout(null);
//        AddBookingBtn.setBounds(350, 160, 150, 150);
//        AddBookingBtn.setFont(u.formatText(16));
//        add(AddBookingBtn);
//        AddBookingBtn.setVisible(false);

        // View Booking
//        JButton ViewBookingBtn = new JButton("View Booking");
//        ViewBookingBtn.setLayout(null);
//        ViewBookingBtn.setBounds(550, 160, 150, 150);
//        ViewBookingBtn.setFont(u.formatText(16));
//        add(ViewBookingBtn);
//        ViewBookingBtn.setVisible(false);

        // Cancel Booking
        JButton CancelBookingBtn = new JButton("Cancel Booking");
        CancelBookingBtn.setLayout(null);
        CancelBookingBtn.setBounds(750, 160, 150, 150);
        CancelBookingBtn.setFont(u.formatText(16));
        add(CancelBookingBtn);
        CancelBookingBtn.setVisible(false);

        // Return Button
//        JButton ReturnBtn = new JButton("Return");
//        ReturnBtn.setLayout(null);
//        ReturnBtn.setBounds(350, 70, 150, 50);
//        ReturnBtn.setFont(u.formatText(16));
//        add(ReturnBtn);
//        ReturnBtn.setVisible(false);

        returnBtn.addActionListener((ActionEvent e) -> {
            boolean returnCheck = bookingView.NewBookingCheck(emailField);

            if (returnCheck) {
                btnState(true, bookingsBtn, roomServiceBtn, BillingBtn);
                btnState(false, addBookingBtn, viewBookingBtn, CancelBookingBtn, returnBtn);
            } else {
                btnState(true, getNewBookingBtn());
                btnState(false, addBookingBtn, returnBtn, CancelBookingBtn, viewBookingBtn, roomServiceBtn);
            }
        });

        viewBookingBtn.addActionListener((ActionEvent e) -> {
            bookingView.viewBookingGUI(emailField);
        });

        CancelBookingBtn.addActionListener((ActionEvent e) -> {
            bookingView.cancelBookingGUI(emailField);
        });

        // Existing Bookings
        bookingsBtn.addActionListener((ActionEvent e) -> {
            btnState(false, bookingsBtn, roomServiceBtn, NewBookingBtn, BillingBtn);
            btnState(true, addBookingBtn, viewBookingBtn, CancelBookingBtn, returnBtn);
        });

        // New Bookings
        NewBookingBtn.addActionListener((ActionEvent e) -> {
            btnState(true, addBookingBtn, returnBtn);
            btnState(false, NewBookingBtn, roomServiceBtn, BillingBtn);
        });
    }

    /**
     * Room Service Function
     * Creates Room Service GUI to order/view from menu
     */
    private void RoomServiceGUI() {
        roomServiceBtn.setBounds(550, 160, 150, 150);
        roomServiceBtn.setFont(u.formatText(16));
        roomServiceBtn.setLayout(null);
        add(roomServiceBtn);

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
            roomServiceView.OrderFoodGUI(emailField);
        });

        // Button Visibility
        SReturnBtn.addActionListener((ActionEvent e) -> {
            btnState(true, bookingsBtn, roomServiceBtn, BillingBtn);
            btnState(false, ViewMenu, OrderMenu, SReturnBtn);
        });

        roomServiceBtn.addActionListener((ActionEvent e) -> {
            btnState(true, ViewMenu, OrderMenu, SReturnBtn);
            btnState(false, bookingsBtn, roomServiceBtn, NewBookingBtn, BillingBtn);
        });
    }

    // Displays current Charges to user
    private void BillingGUI() {
        BillingBtn.setBounds(750, 160, 150, 150);
        BillingBtn.setFont(u.formatText(16));
        BillingBtn.setLayout(null);
        add(BillingBtn);

        BillingBtn.addActionListener((ActionEvent e) -> {
            billingView.getUserBillingGUI(emailField);
        });
    }
    
    // DEBUG - View User Dashboard
    public static void main(String[] args) {
        UserDashboardView userDash = new UserDashboardView("test", "test");
    }
    
    
    /**
     * Sidebar Color Function
     * Sets the sidebar background color for user. 
     * 
     * @param color 
     */
    @Override
    protected void setSidebarColor(Color color) {
        super.setSidebarColor(u.userColour());
    }
}
