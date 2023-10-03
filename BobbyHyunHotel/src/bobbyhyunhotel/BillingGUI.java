package bobbyhyunhotel;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author junhy
 */
public class BillingGUI extends JFrame {

    private Billing billing;

    public BillingGUI() {
        billing = new Billing();
    }

    public void getUserBillingGUI(String userEmail) {
        String Email = userEmail;
        double roomPrice = billing.getRoomPrice(Email);
        double roomServicesPrice = billing.getTotalRoomServicesPrice(Email);

        // Create a new JFrame for the billing GUI
        JFrame billingFrame = new JFrame("Billing");
        billingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create a JPanel to hold the billing information
        JPanel billingPanel = new JPanel(new GridLayout(3, 1, 10, 10));

        // Add labels for room price, room services price, and total price
        JLabel roomPriceLabel = new JLabel("Room Price: $" + roomPrice);
        JLabel roomServicesPriceLabel = new JLabel("Room Services Price: $" + roomServicesPrice);
        JLabel totalLabel = new JLabel("Total Price: $" + (roomPrice + roomServicesPrice));

        // Add components to the billing panel
        billingPanel.add(roomPriceLabel);
        billingPanel.add(roomServicesPriceLabel);
        billingPanel.add(totalLabel);

        // Add the billing panel to the frame
        billingFrame.add(billingPanel);

        // Center the frame on the screen
        billingFrame.setLocationRelativeTo(null);

        // Set frame visibility to true
        billingFrame.pack();
        billingFrame.setVisible(true);
    }

    //staffDashboard allow them to view all the booking records
    public void displayBookingRecordsGUI() {
        // Create the main frame
        JFrame frame = new JFrame("Booking Records");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create a panel to hold the content
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Create a scroll pane and add the panel to it
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // JDBC URL for connecting to the Derby embedded database
        String jdbcUrl = "jdbc:derby:HotelRecords;create=true";

        try (Connection connection = DriverManager.getConnection(jdbcUrl); PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM BookingRecords"); ResultSet resultSet = preparedStatement.executeQuery()) {

            // Iterate through the result set and add records to the panel
            while (resultSet.next()) {
                int bookingID = resultSet.getInt("BookingID");
                int userID = resultSet.getInt("UserID");
                int roomNumber = resultSet.getInt("RoomNumber");
                java.sql.Date bookingDate = resultSet.getDate("BookingDate");

                // Create a label for each record
                JLabel recordLabel = new JLabel("Booking ID: " + bookingID + " | User ID: " + userID + " | Room Number: " + roomNumber + " | Booking Date: " + bookingDate);
                panel.add(recordLabel);
            }

        } catch (SQLException ex) {
            System.err.println("SQL error occurred:");
            ex.printStackTrace();
        }

        // Add the scroll pane to the frame
        frame.add(scrollPane);

        // Set frame size and make it visible
        frame.setSize(500, 400); // You can adjust the size as needed
        frame.setVisible(true);
    }

    public void displayRoomServicesRecordsGUI() {
        // Create the main frame
        JFrame frame = new JFrame("Room Services Records");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create a panel to hold the content
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Create a scroll pane and add the panel to it
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // JDBC URL for connecting to the Derby embedded database
        String jdbcUrl = "jdbc:derby:HotelRecords;create=true";

        try (Connection connection = DriverManager.getConnection(jdbcUrl); PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM OrderRecords"); ResultSet resultSet = preparedStatement.executeQuery()) {

            // Iterate through the result set and add records to the panel
            while (resultSet.next()) {
                int orderID = resultSet.getInt("OrderID");
                int userID = resultSet.getInt("UserID");
                int roomNumber = resultSet.getInt("RoomNumber");
                String foodName = resultSet.getString("FoodName");
                double price = resultSet.getDouble("Price");

                // Create a label for each record
                JLabel recordLabel = new JLabel("Order ID: " + orderID + " | User ID: " + userID + " | Room Number: " + roomNumber + " | Food Name: " + foodName + " | Price: $" + price);
                panel.add(recordLabel);
            }

        } catch (SQLException ex) {
            System.err.println("SQL error occurred:");
            ex.printStackTrace();
        }

        // Add the scroll pane to the frame
        frame.add(scrollPane);

        // Set frame size and make it visible
        frame.setSize(500, 400); // You can adjust the size as needed
        frame.setVisible(true);
    }

}
