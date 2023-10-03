package bobbyhyunhotel;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JFrame;

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

}
