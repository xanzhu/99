package bobbyhyunhotel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StaffDashboard extends JFrame {
    private RoomManagementGUI roomManagement;
    private RoomServicesGUI roomServices;
    private JFrame roomActionsFrame;

    public StaffDashboard() {
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
        roomManagement = new RoomManagementGUI();
        roomServices = new RoomServicesGUI();


        // Create a button in the top left corner for Room Actions
        JButton roomButton = new JButton("Manage Rooms");
        roomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openRoomActionsDialog();
            }
        });

        // Create a button right next to it for Room Services
        JButton roomServicesButton = new JButton("Room Services");
        roomServicesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openRoomServicesDialog();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(roomButton);
        buttonPanel.add(roomServicesButton); // Add the Room Services button

        add(buttonPanel, BorderLayout.NORTH);

        setVisible(true);
    }

    private void openRoomActionsDialog() {

        if (roomActionsFrame != null) {
            roomActionsFrame.dispose(); // Dispose of the Room Actions dialog if it's open
        }

        roomActionsFrame = new JFrame("Room Actions");
        roomActionsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        roomActionsFrame.setBounds(200, 200, 400, 200);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        JButton addRoomButton = new JButton("Add Rooms");
        addRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                roomManagement.addRoomGUI();
            }
        });
        JButton removeRoomButton = new JButton("Remove Rooms");
        removeRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                roomManagement.removeRoomGUI();
            }
        });
        JButton roomPriceButton = new JButton("Room Price");
        roomPriceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                roomManagement.priceGUI();
            }
        });
        JButton roomAvailabilityButton = new JButton("Room Availability");
         roomAvailabilityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                roomManagement.availableGUI();
            }
        });

        buttonPanel.add(addRoomButton);
        buttonPanel.add(removeRoomButton);
        buttonPanel.add(roomPriceButton);
        buttonPanel.add(roomAvailabilityButton);

        roomActionsFrame.add(buttonPanel);
        roomActionsFrame.setVisible(true);

        // Add action listeners to these buttons if needed
    }

    private void openRoomServicesDialog() {
        if (roomActionsFrame != null) {
            roomActionsFrame.dispose(); // Dispose of the Room Actions dialog if it's open
        }

        JFrame roomServicesFrame = new JFrame("Room Services");
        roomServicesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        roomServicesFrame.setBounds(200, 200, 400, 200);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        JButton addFoodButton = new JButton("Add Foods");
        addFoodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                roomServices.addFoodGUI();
            }
        });
        JButton removeFoodButton = new JButton("Remove Foods");
        removeFoodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                roomServices.removeFoodGUI();
            }
        });
        JButton foodPriceButton = new JButton("Food Price");
        foodPriceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                roomServices.priceFoodGUI();
            }
        });
        JButton foodAvailabilityButton = new JButton("Food Availability");
        foodAvailabilityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                roomServices.foodAvailabilityGUI();
            }
        });

        buttonPanel.add(addFoodButton);
        buttonPanel.add(removeFoodButton);
        buttonPanel.add(foodPriceButton);
        buttonPanel.add(foodAvailabilityButton);

        roomServicesFrame.add(buttonPanel);
        roomServicesFrame.setVisible(true);

        // Add action listeners to these buttons if needed
    }

    public static void main(String[] args) {
        new StaffDashboard();
    }
}
