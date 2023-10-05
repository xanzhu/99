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

    private boolean isAddBookingGUIInvoked = false;

    public boolean isAddBookingGUIInvoked() {
        return isAddBookingGUIInvoked;
    }

    /**
     * @return the BookingBtn
     */
    public JButton getAddBookingBtn() {
        return AddBookingBtn;
    }

    public JButton getBookingBtn() {
        return BookingBtn;
    }

    /**
     * @return the ServiceBtn
     */
    public JButton getServiceBtn() {
        return ServiceBtn;
    }

    /**
     * @return the NewBookingBtn
     */
    public JButton getNewBookingBtn() {
        return NewBookingBtn;
    }

    /**
     * @return the BillingBtn
     */
    public JButton getBillingBtn() {
        return BillingBtn;
    }

    private final JButton logoutBtn;
    private final JLabel nameField;

    private final String loginEmail;

    private final BookingView bookingView;
    private final RoomServiceView roomServiceView;
    private final BillingView billingView;

    private final AppUtils u;

    UserDashboardView(String userName, String userEmail) {
        this.loginEmail = userEmail;
        this.bookingView = new BookingView();
        this.roomServiceView = new RoomServiceView();
        this.billingView = new BillingView();

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
        BillingGUI();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public JButton getLogoutButton() {
        return logoutBtn;
    }

    private JButton BookingBtn;
    private JButton ServiceBtn;
    private JButton NewBookingBtn;
    private JButton BillingBtn;
    private JButton AddBookingBtn;

    private void BookingGUI() {

        boolean hasBooking = bookingView.NewBookingCheck(loginEmail);

        // Define buttons for use
        ServiceBtn = new JButton("Room Service");
        NewBookingBtn = new JButton("Book a room");
        BookingBtn = new JButton("My Bookings");
        BillingBtn = new JButton("Charges");

        getBookingBtn().setBounds(350, 160, 150, 150);
        getBookingBtn().setFont(u.formatText(16));
        getBookingBtn().setLayout(null);
        add(getBookingBtn());
        getBookingBtn().setVisible(hasBooking);

        // Hide elements for non booked
        if (!hasBooking) {
            getServiceBtn().setVisible(false);
            getBillingBtn().setVisible(false);
        }

        getNewBookingBtn().setBounds(350, 160, 150, 150);
        getNewBookingBtn().setFont(u.formatText(16));
        getNewBookingBtn().setLayout(null);
        add(getNewBookingBtn());

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
                btnState(true, getBookingBtn(), getServiceBtn());
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
            btnState(false, getBookingBtn(), getServiceBtn(), getNewBookingBtn(), getBillingBtn());
            btnState(true, AddBookingBtn, ViewBookingBtn, CancelBookingBtn, ReturnBtn);
        });

        // New Bookings
        getNewBookingBtn().addActionListener((ActionEvent e) -> {
            btnState(true, AddBookingBtn, ReturnBtn);
            btnState(false, getNewBookingBtn(), getServiceBtn(), getBillingBtn());
        });

        AddBookingBtn.addActionListener((ActionEvent e) -> {
            isAddBookingGUIInvoked = true;
            bookingView.addBookingGUI();
        });

    }

    private void RoomServiceGUI() {
        getServiceBtn().setBounds(550, 160, 150, 150);
        getServiceBtn().setFont(u.formatText(16));
        getServiceBtn().setLayout(null);
        add(getServiceBtn());

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
            btnState(true, getBookingBtn(), getServiceBtn(), getBillingBtn());
            btnState(false, ViewMenu, OrderMenu, SReturnBtn);
        });

        getServiceBtn().addActionListener((ActionEvent e) -> {
            btnState(true, ViewMenu, OrderMenu, SReturnBtn);
            btnState(false, getBookingBtn(), getServiceBtn(), getNewBookingBtn(), getBillingBtn());
        });
    }

    private void BillingGUI() {
        getBillingBtn().setBounds(750, 160, 150, 150);
        getBillingBtn().setFont(u.formatText(16));
        getBillingBtn().setLayout(null);
        add(getBillingBtn());

        getBillingBtn().addActionListener((ActionEvent e) -> {
            billingView.getUserBillingGUI(loginEmail);
        });
    }

    private void btnState(boolean isVisible, Component... buttons) {
        for (Component button : buttons) {
            button.setVisible(isVisible);
        }
    }

    public static void main(String[] args) {
        UserDashboardView userDash = new UserDashboardView("test", "test");
    }

    void simulateBookingBtnClick() {
        ActionEvent mockEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "simulateBookingBtnClick");
        getBookingBtn().getActionListeners()[0].actionPerformed(mockEvent);
    }

    boolean isAddBookingBtnEnabled() {
        return getAddBookingBtn().isVisible();
    }
}
