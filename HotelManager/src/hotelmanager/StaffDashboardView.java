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

    private final JButton logoutBtn;
    private final JLabel nameField;
    
    private JButton manageRoomBtn;
    private JButton roomServiceBtn;
    private JButton manageUserBtn;
    private JButton bookingsBtn;

    private final RoomManagementView roomManagement;
    private final RoomServiceView roomService;
    private final AppUtils u;

    StaffDashboardView(String userName, String userEmail) {

        this.roomManagement = new RoomManagementView();
        this.roomService = new RoomServiceView();
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
        ManageRoomGUI();
        RoomServiceGUI();
        BookingsGUI();
        ManageUserGUI();

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

    private void ManageRoomGUI() {
        manageRoomBtn = new JButton("Manage Rooms");
        manageRoomBtn.setBounds(350, 160, 150, 150);
        manageRoomBtn.setFont(u.formatText(16));
        manageRoomBtn.setLayout(null);
        add(manageRoomBtn);

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
            btnState(true, roomServiceBtn, manageRoomBtn, manageUserBtn, bookingsBtn);
            btnState(false, AddRoomBtn, RemoveRoomBtn, ReturnBtn, RoomStatusBtn, PriceRoomBtn);
        });

        manageRoomBtn.addActionListener((ActionEvent e) -> {
            btnState(true, AddRoomBtn, RemoveRoomBtn, ReturnBtn, RoomStatusBtn, PriceRoomBtn);
            btnState(false, roomServiceBtn, manageRoomBtn, manageUserBtn, bookingsBtn);
        });
    }

    private void RoomServiceGUI() {

        roomServiceBtn = new JButton("Room Service");
        roomServiceBtn.setBounds(550, 160, 150, 150);
        roomServiceBtn.setFont(u.formatText(16));
        roomServiceBtn.setLayout(null);
        add(roomServiceBtn);

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
        JButton menuPrice = new JButton("Food Price");
        menuPrice.setLayout(null);
        menuPrice.setBounds(750, 160, 150, 150);
        menuPrice.setFont(u.formatText(16));
        add(menuPrice);
        menuPrice.setVisible(false);

        // Menu Status
        JButton menuStatus = new JButton("Menu Availability");
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

        ReturnBtn.addActionListener((ActionEvent e) -> {
            btnState(true, roomServiceBtn, manageRoomBtn, manageUserBtn, bookingsBtn);
            btnState(false, addFood, removeFood, ReturnBtn, menuStatus, menuPrice, menuDisplay);
        });

        roomServiceBtn.addActionListener((ActionEvent e) -> {
            btnState(true, addFood, removeFood, ReturnBtn, menuStatus, menuPrice, menuDisplay);
            btnState(false, roomServiceBtn, manageRoomBtn, manageUserBtn, bookingsBtn);
        });
    }

    // TODO: Implement this! :D
    private void BookingsGUI() {
        bookingsBtn = new JButton("Bookings");
        bookingsBtn.setBounds(750, 160, 150, 150);
        bookingsBtn.setFont(u.formatText(16));
        bookingsBtn.setLayout(null);
        add(bookingsBtn);
        
        JButton AddBooking = new JButton("Add Booking");
        
        
    }

    // Remove this?
    private void ManageUserGUI() {
        manageUserBtn = new JButton("Manage Users");
        manageUserBtn.setBounds(950, 160, 150, 150);
        manageUserBtn.setFont(u.formatText(16));
        manageUserBtn.setLayout(null);
        add(manageUserBtn);

        JButton AddUser = new JButton("Add User");
        AddUser.setLayout(null);
        AddUser.setBounds(350, 160, 150, 150);
        AddUser.setFont(u.formatText(16));
        add(AddUser);
        AddUser.setVisible(false);

        JButton RemoveUser = new JButton("Remove User");
        RemoveUser.setLayout(null);
        RemoveUser.setBounds(550, 160, 150, 150);
        RemoveUser.setFont(u.formatText(16, false));
        add(RemoveUser);
        RemoveUser.setVisible(false);

        JButton ReturnBtn = new JButton("Return");
        ReturnBtn.setLayout(null);
        ReturnBtn.setBounds(350, 70, 150, 50);
        ReturnBtn.setFont(u.formatText(15, false));
        add(ReturnBtn);
        ReturnBtn.setVisible(false);

        ReturnBtn.addActionListener((ActionEvent e) -> {
            btnState(true, roomServiceBtn, manageRoomBtn, manageUserBtn, bookingsBtn);
            btnState(false, AddUser, RemoveUser, ReturnBtn);
        });

        manageUserBtn.addActionListener((ActionEvent e) -> {
            btnState(true, AddUser, RemoveUser, ReturnBtn);
            btnState(false, roomServiceBtn, manageRoomBtn, manageUserBtn, bookingsBtn);
        });
    }
    
    // Testing
    public static void main(String[] args) {
        StaffDashboardView staffDash = new StaffDashboardView("Jon Kim", "example@gmail.com");
    }
}
