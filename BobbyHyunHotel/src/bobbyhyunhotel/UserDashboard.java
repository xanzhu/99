package bobbyhyunhotel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UserDashboard extends JFrame {

    private BookingGUI bookingGUI;
    private RoomServicesGUI roomServicesGUI;
    private BillingGUI billingGUI;
    private static String loggedInUserEmail;
    private JFrame bookingActionsFrame;

    public UserDashboard(String userEmail) {
        this.loggedInUserEmail = userEmail;
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "Do you want to exit the application?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    dispose(); // Close the JFrame
                    System.exit(0); // Exit the application
                }
            }
        });

        setLayout(new BorderLayout());
        setBounds(100, 100, 1000, 560);
        bookingGUI = new BookingGUI();
        roomServicesGUI = new RoomServicesGUI();
        billingGUI = new BillingGUI();

        
        JButton myBookingButton = new JButton("My Booking");
        myBookingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openBookingActionsDialog();
            }
        });

        // Create a button for "Room Services"
        JButton roomServicesButton = new JButton("Room Services");
        roomServicesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openRoomServicesDialog();
            }
        });

        // Create a tool bar for the buttons
        JToolBar toolBar = new JToolBar(JToolBar.HORIZONTAL);
        toolBar.setFloatable(false); // Prevent the toolbar from being moved

        // Add buttons to the toolbar
        toolBar.add(Box.createHorizontalGlue()); 
        toolBar.add(myBookingButton);
        toolBar.add(roomServicesButton);

        // Create a billing button
        JButton billingButton = new JButton("Billing");
        billingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                billingGUI.getUserBillingGUI(loggedInUserEmail);
            }
        });

        // Add the billing button to the toolbar
        toolBar.add(billingButton);

        // Add the toolbar to the frame's north position
        add(toolBar, BorderLayout.NORTH);

        setVisible(true);
    }

    private void openBookingActionsDialog() {
        if (bookingActionsFrame != null) {
            bookingActionsFrame.dispose(); // Dispose of the Booking Actions dialog if it's open
        }

        bookingActionsFrame = new JFrame("Booking Actions");
        bookingActionsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        bookingActionsFrame.setBounds(200, 200, 400, 200);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        JButton addBookingButton = new JButton("Add Booking");
        addBookingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookingGUI.addBookingGUI();
            }
        });
        JButton viewBookingButton = new JButton("View Booking");
        viewBookingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookingGUI.viewBookingGUI(loggedInUserEmail);
            }
        });
        JButton cancelBookingButton = new JButton("Cancel Booking");
        cancelBookingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookingGUI.cancelBookingGUI(loggedInUserEmail);
            }
        });

        buttonPanel.add(addBookingButton);
        buttonPanel.add(viewBookingButton);
        buttonPanel.add(cancelBookingButton);

        bookingActionsFrame.add(buttonPanel);
        bookingActionsFrame.setVisible(true);
    }

    private void openRoomServicesDialog() {
        if (bookingActionsFrame != null) {
            bookingActionsFrame.dispose(); // Dispose of the Booking Actions dialog if it's open
        }

        JFrame roomServicesFrame = new JFrame("Room Services");
        roomServicesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        roomServicesFrame.setBounds(200, 200, 400, 200);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        JButton viewRoomServicesButton = new JButton("View Room Services");
        viewRoomServicesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                roomServicesGUI.viewRoomServicesGUI();
            }
        });
        JButton orderRoomServicesButton = new JButton("Order Room Services");
        orderRoomServicesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                roomServicesGUI.orderRoomServicesGUI(loggedInUserEmail);
            }
        });

        buttonPanel.add(viewRoomServicesButton);
        buttonPanel.add(orderRoomServicesButton);
        //buttonPanel.add(cancelRoomServicesButton);

        roomServicesFrame.add(buttonPanel);
        roomServicesFrame.setVisible(true);
    }

    public static void main(String[] args) {

        // Pass the email address to the UserDashboard constructor
        new UserDashboard(loggedInUserEmail);
    }
}
