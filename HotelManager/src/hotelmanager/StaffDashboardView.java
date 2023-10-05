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
public class StaffDashboardView extends JFrame {

    /**
     * @return the manageRoomBtn
     */
    public JButton getManageRoomBtn() {
        return manageRoomBtn;
    }

    /**
     * @return the roomServiceBtn
     */
    public JButton getRoomServiceBtn() {
        return roomServiceBtn;
    }

    /**
     * @return the manageUserBtn
     */
    public JButton getManageUserBtn() {
        return manageUserBtn;
    }

    /**
     * @return the bookingsBtn
     */
    public JButton getBookingsBtn() {
        return bookingsBtn;
    }
    
    public JButton getAddBookingButton() {
        return addBooking;
    }

    private final JButton logoutBtn;
    private final String emailField;
    private final JLabel nameField;

    private JButton manageRoomBtn;
    private JButton roomServiceBtn;
    private JButton manageUserBtn;
    private JButton bookingsBtn;
    
    // Test
   private JButton addBooking; 

    private final RoomManagementView roomManagement;
    private final RoomServiceView roomService;
    private final BookingView bookingView;
    private final AppUtils u;

    private final BillingView billingView;

    StaffDashboardView(String userName, String userEmail) {

        this.emailField = userEmail;

        this.roomManagement = new RoomManagementView();
        this.roomService = new RoomServiceView();
        this.bookingView = new BookingView();
        this.billingView = new BillingView();
        this.u = new AppUtils();

        setBounds(100, 80, 1280, 720);
        setResizable(false);
        setLayout(null);

        JLabel title = new JLabel("Staff Dashboard");
        title.setFont(u.formatText(30, true));
        title.setBounds(1000, 20, 500, 30);
        add(title);

        // Default Design stuffs: 
        JPanel Sidebar = new JPanel();
        Sidebar.setBounds(0, 0, 250, 720);
        Sidebar.setBackground(u.staffColour());
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
        logoutBtn.setForeground(Color.BLACK);
        logoutBtn.setBackground(Color.WHITE);
        Sidebar.add(logoutBtn);

        ImageIcon profileImg = new ImageIcon(ClassLoader.getSystemResource("Assets/profile.png"));
        JLabel profileLabel = new JLabel(profileImg);
        profileLabel.setBounds(75, 60, 100, 100);
        Sidebar.add(profileLabel);

        // Custom Private GUIs ! :D
        LoadButtons();
        ManageRoomGUI();
        RoomServiceGUI();
        BookingsGUI();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public JButton getLogoutButton() {
        return logoutBtn;
    }

    private void btnState(boolean isVisible, Component... button) {
        for (Component b : button) {
            b.setVisible(isVisible);
        }
    }
    
    private void LoadButtons(){
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

        // Action Listeners
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
        
        //Action Listeners
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
    
    // Testing
    public static void main(String[] args) {
        StaffDashboardView staffDash = new StaffDashboardView("Jon Kim", "example@gmail.com");
    }
}
