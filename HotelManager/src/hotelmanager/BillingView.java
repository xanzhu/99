package hotelmanager;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Bobby Jenkins, Hyun il Jun
 */
public class BillingView extends JFrame {

    private final Billing billing;
    private final DBManager db;
    private final AppUtils u;
    
    // Default Constructor
    public BillingView() {
        this.db = new DBManager();
        this.billing = new Billing(db);
        this.u = new AppUtils();
    }

    /**
     * Create User Billing GUI
     * Based on user's email associated 
     * with Room and Food purchases.
     * 
     * @param userEmail 
     */
    public void getUserBillingGUI(String userEmail) {
        String Email = userEmail;
        double roomPrice = billing.getRoomPrice(Email);
        double roomServicesPrice = billing.getTotalRoomServicesPrice(Email);

        JFrame billingFrame = new JFrame();
        billingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        billingFrame.setBounds(1045, 220, 300, 400);
        billingFrame.setBackground(Color.WHITE);
        billingFrame.setResizable(false);

        JPanel billingPanel = new JPanel();
        billingPanel.setLayout(null);
        
        JLabel chargesTitle = new JLabel("Current Charges");
        chargesTitle.setBounds(20, 10, 400, 60);
        chargesTitle.setFont(u.formatText(20, true));

        JLabel priceLabel = new JLabel("Room Price: $" + roomPrice);
        priceLabel.setBounds(20, 100, 250, 40);
        priceLabel.setFont(u.formatText(16));
        
        JLabel roomServicesPriceLabel = new JLabel("Room Services Price: $" + roomServicesPrice);
        roomServicesPriceLabel.setBounds(20, 150, 250, 40);
        roomServicesPriceLabel.setFont(u.formatText(16));
        
        JLabel totalLabel = new JLabel("Total Price: $" + (roomPrice + roomServicesPrice));
        totalLabel.setBounds(20, 240, 250, 40);
        totalLabel.setFont(u.formatText(18));
        
        JButton removeBtn = new JButton("Close");
        removeBtn.setBounds(15, 310, 250, 40);
        removeBtn.setHorizontalAlignment(JButton.CENTER);
        removeBtn.setBackground(u.userColour());
        removeBtn.setForeground(Color.WHITE);
        removeBtn.setOpaque(true);
        removeBtn.setBorderPainted(false);
        
        billingPanel.add(chargesTitle);
        billingPanel.add(priceLabel);
        billingPanel.add(roomServicesPriceLabel);
        billingPanel.add(totalLabel);
        billingPanel.add(removeBtn);
        
        removeBtn.addActionListener((ActionEvent e) -> {
            billingFrame.dispose();
        });

        billingFrame.add(billingPanel);
        billingFrame.setVisible(true);
    }

    /**
     * Create GUI for All Booking Records
     * Returns all active bookings for staff to view.
     */
    public void displayBookingRecordsGUI() {
        JFrame bookingRecordsFrame = new JFrame();
        bookingRecordsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel recordsPanel = new JPanel();
        recordsPanel.setLayout(new BoxLayout(recordsPanel, BoxLayout.Y_AXIS));
        recordsPanel.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(recordsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
       
        JLabel recordsTitle = new JLabel("Booking Records");
        recordsTitle.setFont(u.formatText(20, true));
        recordsTitle.setForeground(u.staffColour());
        recordsTitle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        recordsPanel.add(recordsTitle);
        
        bookingRecordsFrame.add(scrollPane);

        List<Records> records = billing.displayRecords();

        for (Records record : records) {
            JPanel entriesPanel = new JPanel();
            entriesPanel.setLayout(new BoxLayout(entriesPanel, BoxLayout.Y_AXIS));
            entriesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            entriesPanel.setBackground(Color.WHITE);
            
            JLabel bookingIDLabel = new JLabel("Booking ID: " + record.getBookingID());
            bookingIDLabel.setFont(u.formatText(14, true));
            
            JLabel userIDLabel = new JLabel("User ID: " + record.getUserID());
            userIDLabel.setFont(u.formatText(14));
            
            JLabel roomNumberLabel = new JLabel("Room Number: " + record.getRoomNumber());
            roomNumberLabel.setFont(u.formatText(14));
            
            JLabel bookingDateLabel = new JLabel("Booking Date: " + record.getBookingDate());
            bookingDateLabel.setFont(u.formatText(14));

            entriesPanel.add(bookingIDLabel);
            entriesPanel.add(userIDLabel);
            entriesPanel.add(roomNumberLabel);
            entriesPanel.add(bookingDateLabel);

            recordsPanel.add(entriesPanel);
            recordsPanel.add(Box.createVerticalStrut(10));
        }

        bookingRecordsFrame.setBounds(1045, 220, 300, 400);
        bookingRecordsFrame.setResizable(false);
        bookingRecordsFrame.setVisible(true);
    }
    
    /**
     * Create Food Purchases GUI
     * Lists all active food purchases from users. 
     */
    public void displayFoodRecordsGUI() {
        JFrame foodRecordsFrame = new JFrame();
        foodRecordsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel recordsPanel = new JPanel();
        recordsPanel.setLayout(new BoxLayout(recordsPanel, BoxLayout.Y_AXIS));
        recordsPanel.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(recordsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
       
        JLabel recordsTitle = new JLabel("Room Service Records");
        recordsTitle.setFont(u.formatText(20, true));
        recordsTitle.setForeground(u.staffColour());
        recordsTitle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        recordsPanel.add(recordsTitle);
        
        foodRecordsFrame.add(scrollPane);

        List<Records> records = billing.displayFoodRecords();

        for (Records record : records) {
            JPanel entriesPanel = new JPanel();
            entriesPanel.setLayout(new BoxLayout(entriesPanel, BoxLayout.Y_AXIS));
            entriesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            entriesPanel.setBackground(Color.WHITE);
                        
            JLabel orderIDLabel = new JLabel("Order ID: " + record.getOrderID());
            orderIDLabel.setFont(u.formatText(14, true));
            
            JLabel userIDLabel = new JLabel("User ID: " + record.getUserID());
            userIDLabel.setFont(u.formatText(14));
            
            JLabel roomNumberLabel = new JLabel("Room Number: " + record.getRoomNumber());
            roomNumberLabel.setFont(u.formatText(14));
            
            JLabel foodNameLabel = new JLabel("Food Name: " + record.getFoodName());
            foodNameLabel.setFont(u.formatText(14));
            
            JLabel price = new JLabel("Price: " + record.getPrice());
            price.setFont(u.formatText(14));

            entriesPanel.add(orderIDLabel);
            entriesPanel.add(userIDLabel);
            entriesPanel.add(roomNumberLabel);
            entriesPanel.add(foodNameLabel);
            entriesPanel.add(price);

            recordsPanel.add(entriesPanel);
            recordsPanel.add(Box.createVerticalStrut(10));
        }

        foodRecordsFrame.setBounds(1045, 220, 300, 400);
        foodRecordsFrame.setResizable(false);
        foodRecordsFrame.setVisible(true);
    }
}
