package bobbyhyunhotel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoomManagementGUI {
    private RoomManagement rm;
    
    public RoomManagementGUI(){
        rm = new RoomManagement();
    }
    public void addRoomGUI() {
        // Create a new JFrame for the Add Room GUI
        JFrame addRoomFrame = new JFrame("Add Room");
        addRoomFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addRoomFrame.setBounds(200, 200, 400, 250); // Adjust the size as needed

        // Create a JPanel to hold the input fields and labels
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        // Add labels and text fields for each room attribute
        JLabel roomNumberLabel = new JLabel("Room Number:");
        JTextField roomNumberField = new JTextField();
        JLabel roomTypeLabel = new JLabel("Room Type:");
        JTextField roomTypeField = new JTextField();
        JLabel priceLabel = new JLabel("Price:");
        JTextField priceField = new JTextField();
        JLabel availabilityLabel = new JLabel("Is Available:");
        JCheckBox availabilityCheckBox = new JCheckBox();

        // Add components to the input panel
        inputPanel.add(roomNumberLabel);
        inputPanel.add(roomNumberField);
        inputPanel.add(roomTypeLabel);
        inputPanel.add(roomTypeField);
        inputPanel.add(priceLabel);
        inputPanel.add(priceField);
        inputPanel.add(availabilityLabel);
        inputPanel.add(availabilityCheckBox);

        // Create a button to add the room
        JButton addButton = new JButton("Add Room");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get values from text fields and checkboxes
                int roomNumber = Integer.parseInt(roomNumberField.getText());
                String roomType = roomTypeField.getText();
                double price = Double.parseDouble(priceField.getText());
                boolean isAvailable = availabilityCheckBox.isSelected();

                // Call a method to add the room to the database
                rm.addRoom(roomNumber, roomType, price, isAvailable);

                // Clear input fields
                roomNumberField.setText("");
                roomTypeField.setText("");
                priceField.setText("");
                availabilityCheckBox.setSelected(false);
            }
        });

        // Create a button panel for the Add button
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);

        // Create a main panel to hold all components
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the main panel to the frame
        addRoomFrame.add(mainPanel);

        // Set frame visibility to true
        addRoomFrame.setVisible(true);
    }
    
        public void removeRoomGUI() {
        // Create a new JFrame for the Remove Room GUI
        JFrame removeRoomFrame = new JFrame("Remove Room");
        removeRoomFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        removeRoomFrame.setBounds(200, 200, 400, 150); // Adjust the size as needed

        // Create a JPanel to hold the input field and confirm button
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        // Add a label and text field for room number input
        JLabel roomNumberLabel = new JLabel("Enter Room Number:");
        JTextField roomNumberField = new JTextField();

        // Add components to the input panel
        inputPanel.add(roomNumberLabel);
        inputPanel.add(roomNumberField);

        // Create a button to confirm the removal
        JButton confirmButton = new JButton("Confirm Removal");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the room number entered by the user
                int roomNumber = Integer.parseInt(roomNumberField.getText());

                // Call a method to remove the room from the database
                rm.removeRoom(roomNumber);

                // Clear the input field
                roomNumberField.setText("");

                // Close the remove room frame
                removeRoomFrame.dispose();
            }
        });

        // Create a button panel for the Confirm button
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(confirmButton);

        // Create a main panel to hold all components
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the main panel to the frame
        removeRoomFrame.add(mainPanel);

        // Set frame visibility to true
        removeRoomFrame.setVisible(true);
    }
        
        public void priceGUI() {
        // Create a new JFrame for the Price Update GUI
        JFrame priceFrame = new JFrame("Update Room Price");
        priceFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        priceFrame.setBounds(200, 200, 400, 150); // Adjust the size as needed

        // Create a JPanel to hold the input fields and labels
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        // Add labels and text fields for room number and new price
        JLabel roomNumberLabel = new JLabel("Room Number:");
        JTextField roomNumberField = new JTextField();
        JLabel newPriceLabel = new JLabel("New Price:");
        JTextField newPriceField = new JTextField();

        // Add components to the input panel
        inputPanel.add(roomNumberLabel);
        inputPanel.add(roomNumberField);
        inputPanel.add(newPriceLabel);
        inputPanel.add(newPriceField);

        // Create a button to update the room price
        JButton updateButton = new JButton("Update Price");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get values from text fields
                int roomNumber = Integer.parseInt(roomNumberField.getText());
                double newPrice = Double.parseDouble(newPriceField.getText());

                // Call a method to update the room price in the database
                rm.updateRoomPrice(roomNumber, newPrice);

                // Clear input fields
                roomNumberField.setText("");
                newPriceField.setText("");
            }
        });

        // Create a button panel for the Update button
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(updateButton);

        // Create a main panel to hold all components
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the main panel to the frame
        priceFrame.add(mainPanel);

        // Set frame visibility to true
        priceFrame.setVisible(true);
    }
        
        public void availableGUI() {
        // Create a new JFrame for the Availability GUI
        JFrame availableFrame = new JFrame("Room Availability");
        availableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        availableFrame.setLayout(new BorderLayout()); // Set BorderLayout

        // Create a JPanel to hold the input fields and labels
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        // Add labels and radio buttons for room number and availability
        JLabel roomNumberLabel = new JLabel("Room Number:");
        JTextField roomNumberField = new JTextField();

        JLabel availabilityLabel = new JLabel("Availability:");
        JRadioButton availableRadio = new JRadioButton("Available");
        JRadioButton unavailableRadio = new JRadioButton("Unavailable");

        // Group the radio buttons together so that only one can be selected
        ButtonGroup availabilityGroup = new ButtonGroup();
        availabilityGroup.add(availableRadio);
        availabilityGroup.add(unavailableRadio);

        // Add components to the input panel
        inputPanel.add(roomNumberLabel);
        inputPanel.add(roomNumberField);

        // Create a panel for the radio buttons
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        radioPanel.add(availabilityLabel);
        radioPanel.add(availableRadio);
        radioPanel.add(unavailableRadio);

        // Create a button to update availability
        JButton updateButton = new JButton("Update Availability");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get values from text fields and radio buttons
                int roomNumber = Integer.parseInt(roomNumberField.getText());
                boolean isAvailable = availableRadio.isSelected();

                // Call a method to update the availability in the database
                rm.updateRoomAvailability(roomNumber, isAvailable);

                // Clear input fields
                roomNumberField.setText("");
                availabilityGroup.clearSelection(); // Clear radio button selection
            }
        });

        // Create a button panel for the Update button
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(updateButton);

        // Create a main panel to hold all components
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(radioPanel, BorderLayout.NORTH); // Place radioPanel in North
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the main panel to the frame
        availableFrame.add(mainPanel, BorderLayout.CENTER); // Place mainPanel in Center

        // Set frame size and visibility
        availableFrame.setSize(400, 200);
        availableFrame.setVisible(true);
    }




}
