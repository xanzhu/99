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
public class StaffDashboardView extends JFrame {

    private final JButton logoutBtn;
    private final String emailField;
    private final JLabel nameField;

    private JButton manageRoomBtn;
    private JButton roomServiceBtn;
    private JButton manageUserBtn;
    private JButton bookingsBtn;
    private JButton addBooking;

    private final RoomManagementView roomManagement;
    private final RoomServiceView roomService;
    private final BookingView bookingView;
    private final AppUtils u;

    private final BillingView billingView;

    // Getters
    public JButton getManageRoomBtn() {
        return manageRoomBtn;
    }

    public JButton getRoomServiceBtn() {
        return roomServiceBtn;
    }

    public JButton getManageUserBtn() {
        return manageUserBtn;
    }

    public JButton getBookingsBtn() {
        return bookingsBtn;
    }

    public JButton getAddBookingButton() {
        return addBooking;
    }

    public String getEmailField() {
        return emailField;
    }

    public JButton getLogoutButton() {
        return logoutBtn;
    }

    StaffDashboardView(String userName, String userEmail) {

        this.emailField = userEmail;

        this.roomManagement = new RoomManagementView();
        this.roomService = new RoomServiceView();
        this.bookingView = new BookingView();
        this.billingView = new BillingView();
        
        // Util for Design Components
        this.u = new AppUtils();

        setBounds(100, 80, 1280, 720);
        setResizable(false);
        setLayout(null);

        // Dashboard Title
        JLabel title = new JLabel("Staff Dashboard");
        title.setFont(u.formatText(30, true));
        title.setBounds(1000, 20, 500, 30);
        add(title);

        // Default sidebar design
        JPanel Sidebar = new JPanel();
        Sidebar.setBounds(0, 0, 250, 720);
        Sidebar.setBackground(u.staffColour());
        Sidebar.setLayout(null);
        add(Sidebar);

        // Name
        nameField = new JLabel(userName);
        nameField.setBounds(0, 100, 250, 200);
        nameField.setVerticalAlignment(JLabel.CENTER);
        nameField.setHorizontalAlignment(JLabel.CENTER);
        nameField.setFont(u.formatText(20, true));
        nameField.setForeground(Color.WHITE);
        Sidebar.add(nameField);

        // Email
        JLabel email = new JLabel(userEmail);
        email.setBounds(0, 130, 250, 200);
        email.setVerticalAlignment(JLabel.CENTER);
        email.setHorizontalAlignment(JLabel.CENTER);
        email.setForeground(Color.WHITE);
        Sidebar.add(email);

        // Log out
        logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(35, 620, 170, 40);
        logoutBtn.setForeground(Color.BLACK);
        logoutBtn.setBackground(Color.WHITE);
        Sidebar.add(logoutBtn);

        // Default profile icon
        ImageIcon profileImg = new ImageIcon(ClassLoader.getSystemResource("Assets/profile.png"));
        JLabel profileLabel = new JLabel(profileImg);
        profileLabel.setBounds(75, 60, 100, 100);
        Sidebar.add(profileLabel);

        // Load in private GUIs! 
        LoadButtons();
        ManageRoomGUI();
        RoomServiceGUI();
        BookingsGUI();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Reusable component to handle button visibility
    private void btnState(boolean isVisible, Component... btn) {
        for (Component b : btn) {
            b.setVisible(isVisible);
        }
    }

    /**
     * Private GUI Functions for display menu options. 
     * Load buttons - Main menu buttons. 
     * Manage Rooms - sub-menu options for managing rooms. 
     * Room Service - sub-menu options for room service. 
     * Booking      - sub-menu options for bookings.
     */
    private void LoadButtons() {
        manageRoomBtn = new JButton("Manage Rooms");
        roomServiceBtn = new JButton("Room Service");
        bookingsBtn = new JButton("Bookings");
    }

    private void ManageRoomGUI() {
        manageRoomBtn = new JButton("Manage Rooms");
        getManageRoomBtn().setBounds(350, 160, 150, 150);
        getManageRoomBtn().setFont(u.formatText(16));
        getManageRoomBtn().setLayout(null);
        add(getManageRoomBtn());

        // Add Button
        JButton AddRoomBtn = new JButton("Add Room");
        AddRoomBtn.setLayout(null);
        AddRoomBtn.setBounds(350, 160, 150, 150);
        AddRoomBtn.setFont(u.formatText(16));
        add(AddRoomBtn);
        AddRoomBtn.setVisible(false);

        // Remove button
        JButton RemoveRoomBtn = new JButton("Remove Room");
        RemoveRoomBtn.setLayout(null);
        RemoveRoomBtn.setBounds(550, 160, 150, 150);
        RemoveRoomBtn.setFont(u.formatText(16));
        add(RemoveRoomBtn);
        RemoveRoomBtn.setVisible(false);

        // Room Price
        JButton PriceRoomBtn = new JButton("Price Room");
        PriceRoomBtn.setLayout(null);
        PriceRoomBtn.setBounds(750, 160, 150, 150);
        PriceRoomBtn.setFont(u.formatText(16));
        add(PriceRoomBtn);
        PriceRoomBtn.setVisible(false);

        // Room Status
        JButton RoomStatusBtn = new JButton("Room Status");
        RoomStatusBtn.setLayout(null);
        RoomStatusBtn.setBounds(350, 340, 150, 150);
        RoomStatusBtn.setFont(u.formatText(16));
        add(RoomStatusBtn);
        RoomStatusBtn.setVisible(false);

        // Return button
        JButton ReturnBtn = new JButton("Return");
        ReturnBtn.setLayout(null);
        ReturnBtn.setBounds(350, 70, 150, 50);
        ReturnBtn.setFont(u.formatText(15));
        add(ReturnBtn);
        ReturnBtn.setVisible(false);

        // Action Listeners to handle button clicks
        AddRoomBtn.addActionListener((ActionEvent e) -> {
            roomManagement.addRoomGUI();
        });

        RemoveRoomBtn.addActionListener((ActionEvent e) -> {
            roomManagement.removeRoomGUI();
        });

        RoomStatusBtn.addActionListener((ActionEvent e) -> {
            roomManagement.roomStatusGUI();
        });

        PriceRoomBtn.addActionListener((ActionEvent e) -> {
            roomManagement.roomPriceGUI();
        });

        // Handle button visibility
        ReturnBtn.addActionListener((ActionEvent e) -> {
            btnState(true, getRoomServiceBtn(), getManageRoomBtn(), getBookingsBtn());
            btnState(false, AddRoomBtn, RemoveRoomBtn, ReturnBtn, RoomStatusBtn, PriceRoomBtn);
        });

        getManageRoomBtn().addActionListener((ActionEvent e) -> {
            btnState(true, AddRoomBtn, RemoveRoomBtn, ReturnBtn, RoomStatusBtn, PriceRoomBtn);
            btnState(false, getRoomServiceBtn(), getManageRoomBtn(), getBookingsBtn());
        });
    }

    private void RoomServiceGUI() {
        roomServiceBtn = new JButton("Room Service");
        getRoomServiceBtn().setBounds(550, 160, 150, 150);
        getRoomServiceBtn().setFont(u.formatText(16));
        getRoomServiceBtn().setLayout(null);
        add(getRoomServiceBtn());

        // Add Menu Item
        JButton addFood = new JButton("Add Food");
        addFood.setLayout(null);
        addFood.setBounds(350, 160, 150, 150);
        addFood.setFont(u.formatText(16));
        add(addFood);
        addFood.setVisible(false);

        // Remove Menu Item
        JButton removeFood = new JButton("Remove Food");
        removeFood.setLayout(null);
        removeFood.setBounds(550, 160, 150, 150);
        removeFood.setFont(u.formatText(16));
        add(removeFood);
        removeFood.setVisible(false);

        // Menu Price
        JButton menuPrice = new JButton("Item Price");
        menuPrice.setLayout(null);
        menuPrice.setBounds(750, 160, 150, 150);
        menuPrice.setFont(u.formatText(16));
        add(menuPrice);
        menuPrice.setVisible(false);

        // Menu Status
        JButton menuStatus = new JButton("Item Status");
        menuStatus.setLayout(null);
        menuStatus.setBounds(350, 340, 150, 150);
        menuStatus.setFont(u.formatText(16));
        add(menuStatus);
        menuStatus.setVisible(false);

        // View Menu
        JButton menuDisplay = new JButton("View Menu");
        menuDisplay.setLayout(null);
        menuDisplay.setBounds(550, 340, 150, 150);
        menuDisplay.setFont(u.formatText(16));
        add(menuDisplay);
        menuDisplay.setVisible(false);

        // View Records
        JButton menuRecords = new JButton("View Records");
        menuRecords.setLayout(null);
        menuRecords.setBounds(750, 340, 150, 150);
        menuRecords.setFont(u.formatText(16));
        add(menuRecords);
        menuRecords.setVisible(false);

        // Return button
        JButton ReturnBtn = new JButton("Return");
        ReturnBtn.setLayout(null);
        ReturnBtn.setBounds(350, 70, 150, 50);
        ReturnBtn.setFont(u.formatText(15));
        add(ReturnBtn);
        ReturnBtn.setVisible(false);

        //Action Listeners for menu options
        addFood.addActionListener((ActionEvent e) -> {
            roomService.addFoodGUI();
        });

        removeFood.addActionListener((ActionEvent e) -> {
            roomService.removeFoodGUI();
        });

        menuPrice.addActionListener((ActionEvent e) -> {
            roomService.updateFoodPriceGUI();
        });

        menuStatus.addActionListener((ActionEvent e) -> {
            roomService.updateFoodStatusGUI();
        });

        menuDisplay.addActionListener((ActionEvent e) -> {
            roomService.viewRoomServicesGUI();
        });

        menuRecords.addActionListener((ActionEvent e) -> {
            billingView.displayFoodRecordsGUI();
        });

        // Set menu option visibility state
        ReturnBtn.addActionListener((ActionEvent e) -> {
            btnState(true, getRoomServiceBtn(), getManageRoomBtn(), getBookingsBtn());
            btnState(false, addFood, removeFood, ReturnBtn, menuStatus, menuPrice, menuDisplay, menuRecords);
        });

        getRoomServiceBtn().addActionListener((ActionEvent e) -> {
            btnState(true, addFood, removeFood, ReturnBtn, menuStatus, menuPrice, menuDisplay, menuRecords);
            btnState(false, getRoomServiceBtn(), getManageRoomBtn(), getBookingsBtn());
        });
    }

    private void BookingsGUI() {
        getBookingsBtn().setBounds(750, 160, 150, 150);
        getBookingsBtn().setFont(u.formatText(16));
        getBookingsBtn().setLayout(null);
        add(getBookingsBtn());

        // Add Booking
        addBooking = new JButton("Add Booking");
        addBooking.setLayout(null);
        addBooking.setBounds(350, 160, 150, 150);
        addBooking.setFont(u.formatText(16));
        add(addBooking);
        addBooking.setVisible(false);

        // Remove Button
        JButton removeBooking = new JButton("Remove Booking");
        removeBooking.setLayout(null);
        removeBooking.setBounds(550, 160, 150, 150);
        removeBooking.setFont(u.formatText(16));
        add(removeBooking);
        removeBooking.setVisible(false);

        // TODO: View Button
        JButton viewBooking = new JButton("View Bookings");
        viewBooking.setLayout(null);
        viewBooking.setBounds(750, 160, 150, 150);
        viewBooking.setFont(u.formatText(16));
        add(viewBooking);
        viewBooking.setVisible(false);

        // Return Button
        JButton ReturnBtn = new JButton("Return");
        ReturnBtn.setLayout(null);
        ReturnBtn.setBounds(350, 70, 150, 50);
        ReturnBtn.setFont(u.formatText(15));
        add(ReturnBtn);
        ReturnBtn.setVisible(false);

        addBooking.addActionListener((ActionEvent e) -> {
            bookingView.addBookingGUI();
        });

        removeBooking.addActionListener((ActionEvent e) -> {
            bookingView.staffCancelBookingGUI();
        });

        viewBooking.addActionListener((ActionEvent e) -> {
            billingView.displayBookingRecordsGUI();
        });

        ReturnBtn.addActionListener((ActionEvent e) -> {
            btnState(true, getRoomServiceBtn(), getManageRoomBtn(), getBookingsBtn());
            btnState(false, addBooking, removeBooking, ReturnBtn, viewBooking);
        });

        getBookingsBtn().addActionListener((ActionEvent e) -> {
            btnState(true, addBooking, removeBooking, ReturnBtn, viewBooking);
            btnState(false, getRoomServiceBtn(), getManageRoomBtn(), getBookingsBtn());
        });

    }
    
    // DEBUG: Display Dashboard for designing JFrame
    //    public static void main(String[] args) {
    //        StaffDashboardView staffDash = new StaffDashboardView("Jon Kim", "kimjon@gmail.com");
    //    }
}
