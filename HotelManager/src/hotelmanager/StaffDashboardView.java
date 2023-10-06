package hotelmanager;

import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

/**
 *
 * @author Bobby Jenkins, Hyun il Jun
 */
public class StaffDashboardView extends DashboardView {

    private final RoomManagementView roomManagement;

    StaffDashboardView(String userName, String userEmail) {
        super(userName, userEmail);
        setDashboardTitle("Staff Dashboard");

        this.roomManagement = new RoomManagementView();

        // Load in private GUIs! 
        LoadButtons();
        ManageRoomGUI();
        RoomServiceGUI();
        BookingsGUI();
    }

    /**
     * Private GUI Functions for display menu options. Load buttons - Main menu
     * buttons. Manage Rooms - sub-menu options for managing rooms. Room Service
     * - sub-menu options for room service. Booking - sub-menu options for
     * bookings.
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
            roomServiceView.addFoodGUI();
        });

        removeFood.addActionListener((ActionEvent e) -> {
            roomServiceView.removeFoodGUI();
        });

        menuPrice.addActionListener((ActionEvent e) -> {
            roomServiceView.updateFoodPriceGUI();
        });

        menuStatus.addActionListener((ActionEvent e) -> {
            roomServiceView.updateFoodStatusGUI();
        });

        menuDisplay.addActionListener((ActionEvent e) -> {
            roomServiceView.viewRoomServicesGUI();
        });

        menuRecords.addActionListener((ActionEvent e) -> {
            billingView.displayFoodRecordsGUI();
        });

        // Set menu option visibility state
        ReturnBtn.addActionListener((ActionEvent e) -> {
            btnState(true, roomServiceBtn, manageRoomBtn, bookingsBtn);
            btnState(false, addFood, removeFood, ReturnBtn, menuStatus, menuPrice, menuDisplay, menuRecords);
        });

        roomServiceBtn.addActionListener((ActionEvent e) -> {
            btnState(true, addFood, removeFood, ReturnBtn, menuStatus, menuPrice, menuDisplay, menuRecords);
            btnState(false, roomServiceBtn, manageRoomBtn, bookingsBtn);
        });
    }

    private void BookingsGUI() {
        super.BookingGUI();

        // Remove Button
        JButton removeBooking = new JButton("Remove Booking");
        removeBooking.setLayout(null);
        removeBooking.setBounds(750, 160, 150, 150);
        removeBooking.setFont(u.formatText(16));
        add(removeBooking);
        removeBooking.setVisible(false);

//        addBookingBtn.addActionListener((ActionEvent e) -> {
//            bookingView.addBookingGUI();
//        });
        removeBooking.addActionListener((ActionEvent e) -> {
            bookingView.staffCancelBookingGUI();
        });

        viewBookingBtn.addActionListener((ActionEvent e) -> {
            billingView.displayBookingRecordsGUI();
        });

        returnBtn.addActionListener((ActionEvent e) -> {
            btnState(true, roomServiceBtn, manageRoomBtn, bookingsBtn);
            btnState(false, addBookingBtn, removeBooking, returnBtn, viewBookingBtn);
        });

        bookingsBtn.addActionListener((ActionEvent e) -> {
            btnState(true, addBookingBtn, removeBooking, returnBtn, viewBookingBtn);
            btnState(false, roomServiceBtn, manageRoomBtn, bookingsBtn);
        });
    }

    // DEBUG: Display Dashboard for designing JFrame
    public static void main(String[] args) {
        StaffDashboardView staffDash = new StaffDashboardView("Jon Kim", "kimjon@gmail.com");
    }

     /**
     * Sidebar Color Function
     * Sets the sidebar background color for staff. 
     * 
     * @param color 
     */
    @Override
    protected void setSidebarColor(Color color) {
        super.setSidebarColor(u.staffColour());
    }
}
