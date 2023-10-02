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
public class StaffDashboardView extends JFrame {

    private final JButton logoutBtn;
    private final JLabel nameField;

    private RoomManagementView roomManagement;
    private RoomServiceView roomService;

    StaffDashboardView(String userName, String userEmail) {

        roomManagement = new RoomManagementView();
        roomService = new RoomServiceView();

        setBounds(100, 80, 1280, 720);
        setResizable(false);
        setLayout(null);

        JLabel title = new JLabel("Staff Dashboard");
        title.setFont(new Font("sans serif", Font.BOLD, 30));
        title.setBounds(1000, 20, 500, 30);
        add(title);

        // Default Design stuffs: 
        JPanel Sidebar = new JPanel();
        Sidebar.setBounds(0, 0, 250, 720);
        Sidebar.setBackground(Color.decode("#0047AB"));
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

    // Testing
    public static void main(String[] args) {
        StaffDashboardView staffDash = new StaffDashboardView("Jon Kim", "example@gmail.com");
    }
    
    private JButton ManageRoomBtn;
    private JButton RoomServiceBtn;
    private JButton ManageUserBtn;
    private JButton BookingsBtn;

    private void btnState(boolean isVisible, Component... button) {
        for (Component b : button) {
            b.setVisible(isVisible);
        }
    }

    private void ManageRoomGUI() {

        ManageRoomBtn = new JButton("Manage Rooms");
        ManageRoomBtn.setBounds(350, 160, 150, 150);
        ManageRoomBtn.setFont(new Font("sans serif", Font.PLAIN, 16));
        ManageRoomBtn.setLayout(null);
        add(ManageRoomBtn);

        // Add Button
        JButton AddRoomBtn = new JButton("Add Room");
        AddRoomBtn.setLayout(null);
        AddRoomBtn.setBounds(350, 160, 150, 150);
        AddRoomBtn.setFont(new Font("sans serif", Font.PLAIN, 16));
        add(AddRoomBtn);
        AddRoomBtn.setVisible(false);

        // Remove button
        JButton RemoveRoomBtn = new JButton("Remove Room");
        RemoveRoomBtn.setLayout(null);
        RemoveRoomBtn.setBounds(550, 160, 150, 150);
        RemoveRoomBtn.setFont(new Font("sans serif", Font.PLAIN, 16));
        add(RemoveRoomBtn);
        RemoveRoomBtn.setVisible(false);

        // Room Price
        JButton PriceRoomBtn = new JButton("Price Room");
        PriceRoomBtn.setLayout(null);
        PriceRoomBtn.setBounds(750, 160, 150, 150);
        PriceRoomBtn.setFont(new Font("sans serif", Font.PLAIN, 16));
        add(PriceRoomBtn);
        PriceRoomBtn.setVisible(false);

        // Room Status
        JButton RoomStatusBtn = new JButton("Room Status");
        RoomStatusBtn.setLayout(null);
        RoomStatusBtn.setBounds(350, 340, 150, 150);
        RoomStatusBtn.setFont(new Font("sans serif", Font.PLAIN, 16));
        add(RoomStatusBtn);
        RoomStatusBtn.setVisible(false);

        // Return button
        JButton ReturnBtn = new JButton("Return");
        ReturnBtn.setLayout(null);
        ReturnBtn.setBounds(350, 70, 150, 50);
        ReturnBtn.setFont(new Font("sans serif", Font.PLAIN, 15));
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
            btnState(true, RoomServiceBtn, ManageRoomBtn, ManageUserBtn, BookingsBtn);
            btnState(false, AddRoomBtn, RemoveRoomBtn, ReturnBtn, RoomStatusBtn, PriceRoomBtn);
        });

        ManageRoomBtn.addActionListener((ActionEvent e) -> {
            btnState(true, AddRoomBtn, RemoveRoomBtn, ReturnBtn, RoomStatusBtn, PriceRoomBtn);
            btnState(false, RoomServiceBtn, ManageRoomBtn, ManageUserBtn, BookingsBtn);
        });
    }

    private void RoomServiceGUI() {

        RoomServiceBtn = new JButton("Room Service");
        RoomServiceBtn.setBounds(550, 160, 150, 150);
        RoomServiceBtn.setFont(new Font("sans serif", Font.PLAIN, 16));
        RoomServiceBtn.setLayout(null);
        add(RoomServiceBtn);

        // Add Menu Item
        JButton addFood = new JButton("Add Food");
        addFood.setLayout(null);
        addFood.setBounds(350, 160, 150, 150);
        addFood.setFont(new Font("sans serif", Font.PLAIN, 16));
        add(addFood);
        addFood.setVisible(false);

        // Remove Menu Item
        JButton removeFood = new JButton("Remove Foods");
        removeFood.setLayout(null);
        removeFood.setBounds(550, 160, 150, 150);
        removeFood.setFont(new Font("sans serif", Font.PLAIN, 16));
        add(removeFood);
        removeFood.setVisible(false);

        // Menu Price
        JButton menuPrice = new JButton("Food Price");
        menuPrice.setLayout(null);
        menuPrice.setBounds(750, 160, 150, 150);
        menuPrice.setFont(new Font("sans serif", Font.PLAIN, 16));
        add(menuPrice);
        menuPrice.setVisible(false);

        // Menu Status
        JButton menuStatus = new JButton("Food Availability");
        menuStatus.setLayout(null);
        menuStatus.setBounds(350, 340, 150, 150);
        menuStatus.setFont(new Font("sans serif", Font.PLAIN, 16));
        add(menuStatus);
        menuStatus.setVisible(false);

        // Return button
        JButton ReturnBtn = new JButton("Return");
        ReturnBtn.setLayout(null);
        ReturnBtn.setBounds(350, 70, 150, 50);
        ReturnBtn.setFont(new Font("sans serif", Font.PLAIN, 15));
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
        
        ReturnBtn.addActionListener((ActionEvent e) -> {
            btnState(true, RoomServiceBtn, ManageRoomBtn, ManageUserBtn, BookingsBtn);
            btnState(false, addFood, removeFood, ReturnBtn, menuStatus, menuPrice);
        });

        RoomServiceBtn.addActionListener((ActionEvent e) -> {
            btnState(true, addFood, removeFood, ReturnBtn, menuStatus, menuPrice);
            btnState(false, RoomServiceBtn, ManageRoomBtn, ManageUserBtn, BookingsBtn);
        });
    }
    
    private void BookingsGUI(){
        BookingsBtn = new JButton("Bookings");
        BookingsBtn.setBounds(750, 160, 150, 150);
        BookingsBtn.setFont(new Font("sans serif", Font.PLAIN, 16));
        BookingsBtn.setLayout(null);
        add(BookingsBtn);
    }
    
    private void ManageUserGUI(){
        ManageUserBtn = new JButton("Manage Users");
        ManageUserBtn.setBounds(950, 160, 150, 150);
        ManageUserBtn.setFont(new Font("sans serif", Font.PLAIN, 16));
        ManageUserBtn.setLayout(null);
        add(ManageUserBtn);
        
        JButton AddUser = new JButton("Add User");
        AddUser.setLayout(null);
        AddUser.setBounds(350, 160, 150, 150);
        AddUser.setFont(new Font("sans serif", Font.PLAIN, 16));
        add(AddUser);
        AddUser.setVisible(false);
        
        JButton RemoveUser = new JButton("Remove User");
        RemoveUser.setLayout(null);
        RemoveUser.setBounds(550, 160, 150, 150);
        RemoveUser.setFont(new Font("sans serif", Font.PLAIN, 16));
        add(RemoveUser);
        RemoveUser.setVisible(false);
       
        JButton ReturnBtn = new JButton("Return");
        ReturnBtn.setLayout(null);
        ReturnBtn.setBounds(350, 70, 150, 50);
        ReturnBtn.setFont(new Font("sans serif", Font.PLAIN, 15));
        add(ReturnBtn);
        ReturnBtn.setVisible(false);

        ReturnBtn.addActionListener((ActionEvent e) -> {
            btnState(true, RoomServiceBtn, ManageRoomBtn, ManageUserBtn, BookingsBtn);
            btnState(false, AddUser, RemoveUser, ReturnBtn);
        });

        ManageUserBtn.addActionListener((ActionEvent e) -> {
            btnState(true, AddUser, RemoveUser, ReturnBtn);
            btnState(false, RoomServiceBtn, ManageRoomBtn, ManageUserBtn, BookingsBtn);
        });
    }
}
