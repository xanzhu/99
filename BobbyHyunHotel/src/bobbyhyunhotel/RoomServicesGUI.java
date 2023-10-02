package bobbyhyunhotel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;
//import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.*;
//import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomServicesGUI {
    
    private RoomServices rs;

    public RoomServicesGUI() {
        rs = new RoomServices();
    }

    public void addFoodGUI() {
        // Create a new JFrame for the Add Food GUI
        JFrame addFoodFrame = new JFrame("Add Food");
        addFoodFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addFoodFrame.setBounds(200, 200, 400, 250); // Adjust the size as needed

        // Create a JPanel to hold the input fields and labels
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        // Add labels and text fields for each food attribute
        JLabel foodNameLabel = new JLabel("Food Name:");
        JTextField foodNameField = new JTextField();
        JLabel foodTypeLabel = new JLabel("Food Type:");
        JTextField foodTypeField = new JTextField();
        JLabel priceLabel = new JLabel("Price:");
        JTextField priceField = new JTextField();
        JLabel availabilityLabel = new JLabel("Is Available:");
        JCheckBox availabilityCheckBox = new JCheckBox();

        // Add components to the input panel
        inputPanel.add(foodNameLabel);
        inputPanel.add(foodNameField);
        inputPanel.add(foodTypeLabel);
        inputPanel.add(foodTypeField);
        inputPanel.add(priceLabel);
        inputPanel.add(priceField);
        inputPanel.add(availabilityLabel);
        inputPanel.add(availabilityCheckBox);

        // Create a button to add the food item
        JButton addButton = new JButton("Add Food");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get values from text fields and checkboxes
                String foodName = foodNameField.getText();
                String foodType = foodTypeField.getText();
                double price = Double.parseDouble(priceField.getText());
                boolean isAvailable = availabilityCheckBox.isSelected();

                // Call a method to add the food item to the database
                rs.addFood(foodName, foodType, price, isAvailable);

                // Clear input fields
                foodNameField.setText("");
                foodTypeField.setText("");
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
        addFoodFrame.add(mainPanel);

        // Set frame visibility to true
        addFoodFrame.setVisible(true);
    }

    public void removeFoodGUI() {
        // Create a new JFrame for the Remove Food GUI
        JFrame removeFoodFrame = new JFrame("Remove Food");
        removeFoodFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        removeFoodFrame.setBounds(200, 200, 400, 150); // Adjust the size as needed

        // Create a JPanel to hold the input fields and labels
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        // Add a label and a text field for entering food name
        JLabel foodNameLabel = new JLabel("Food Name:");
        JTextField foodNameField = new JTextField();

        // Add components to the input panel
        inputPanel.add(foodNameLabel);
        inputPanel.add(foodNameField);

        // Create a button to remove the food item
        JButton removeButton = new JButton("Remove Food");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the food name from the text field
                String foodName = foodNameField.getText();

                // Call a method to remove the food item from the database
                rs.removeFood(foodName);

                // Clear the input field
                foodNameField.setText("");
            }
        });

        // Create a button panel for the Remove button
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(removeButton);

        // Create a main panel to hold all components
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the main panel to the frame
        removeFoodFrame.add(mainPanel);

        // Set frame visibility to true
        removeFoodFrame.setVisible(true);
    }

    public void priceFoodGUI() {
        // Create a new JFrame for the Price Food GUI
        JFrame priceFoodFrame = new JFrame("Update Food Price");
        priceFoodFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        priceFoodFrame.setBounds(200, 200, 400, 250); // Adjust the size as needed

        // Create a JPanel to hold the input fields and labels
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        // Add labels and text fields for food name and new price
        JLabel foodNameLabel = new JLabel("Food Name:");
        JTextField foodNameField = new JTextField();
        JLabel newPriceLabel = new JLabel("New Price:");
        JTextField newPriceField = new JTextField();

        // Add components to the input panel
        inputPanel.add(foodNameLabel);
        inputPanel.add(foodNameField);
        inputPanel.add(newPriceLabel);
        inputPanel.add(newPriceField);

        // Create a button to update the food price
        JButton updateButton = new JButton("Update Price");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get values from text fields
                String foodName = foodNameField.getText();
                double newPrice = Double.parseDouble(newPriceField.getText());

                // Call a method to update the food price in the database
                rs.updateFoodPrice(foodName, newPrice);

                // Clear input fields
                foodNameField.setText("");
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
        priceFoodFrame.add(mainPanel);

        // Set frame visibility to true
        priceFoodFrame.setVisible(true);
    }

    public void foodAvailabilityGUI() {
        // Create a new JFrame for the Food Availability GUI
        JFrame foodAvailabilityFrame = new JFrame("Food Availability");
        foodAvailabilityFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        foodAvailabilityFrame.setBounds(200, 200, 400, 250); // Adjust the size as needed

        // Create a JPanel to hold the input fields and labels
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        // Add labels and radio buttons for food name and availability
        JLabel foodNameLabel = new JLabel("Food Name:");
        JTextField foodNameField = new JTextField();

        JLabel availabilityLabel = new JLabel("Availability:");
        JRadioButton availableRadio = new JRadioButton("Available");
        JRadioButton unavailableRadio = new JRadioButton("Unavailable");

        // Create a button group for radio buttons to ensure mutual exclusivity
        ButtonGroup availabilityGroup = new ButtonGroup();
        availabilityGroup.add(availableRadio);
        availabilityGroup.add(unavailableRadio);

        // Add components to the input panel
        inputPanel.add(foodNameLabel);
        inputPanel.add(foodNameField);
        inputPanel.add(availabilityLabel);
        inputPanel.add(availableRadio);
        inputPanel.add(unavailableRadio);

        // Create a button to update food availability
        JButton updateButton = new JButton("Update Availability");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get values from text field and radio buttons
                String foodName = foodNameField.getText();
                boolean isAvailable = availableRadio.isSelected();

                // Call a method to update the food availability in the database
                rs.updateFoodAvailability(foodName, isAvailable);

                // Clear input fields
                foodNameField.setText("");
                availabilityGroup.clearSelection();
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
        foodAvailabilityFrame.add(mainPanel);

        // Set frame visibility to true
        foodAvailabilityFrame.setVisible(true);
    }

    public void orderRoomServicesGUI(String userEmail) {
        String Email = userEmail;
        int selectedRoomNumber = getRoomNumberBasedOnUserEmail(Email);
        JFrame orderFrame = new JFrame("Order Room Services");
        orderFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        orderFrame.setBounds(200, 200, 400, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Retrieve available food items and prices from RoomServicesRecords table
        List<String> foodItems = new ArrayList<>();
        List<Double> prices = new ArrayList<>();

        String jdbcUrl = "jdbc:derby:HotelRecords;create=true"; // Update with your JDBC URL
        try (Connection connection = DriverManager.getConnection(jdbcUrl); PreparedStatement preparedStatement = connection.prepareStatement("SELECT FoodName, Price FROM RoomServicesRecords WHERE IsAvailable = true"); ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String foodName = resultSet.getString("FoodName");
                double price = resultSet.getDouble("Price");
                foodItems.add(foodName);
                prices.add(price);
            }
        } catch (SQLException ex) {
            System.err.println("SQL error occurred:");
            ex.printStackTrace();
        }

        // Create a JComboBox to display food items
        JComboBox<String> foodComboBox = new JComboBox<>(foodItems.toArray(new String[0]));

        // Create a label to display selected food and price
        JLabel selectionLabel = new JLabel("Selected Food: ");
        selectionLabel.setHorizontalAlignment(JLabel.CENTER);

        // Create an "Order" button
        JButton orderButton = new JButton("Order");
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedFood = (String) foodComboBox.getSelectedItem();
                double selectedPrice = prices.get(foodComboBox.getSelectedIndex());
                
                // Handle the order (e.g., insert the order into the OrderRecords table)
                // You can implement this part based on your database schema
                //int selectedRoomNumber = getRoomNumberBasedOnUserSelection();
                // Call the orderRoomServices function with the user's email and room number
                rs.orderRoomServices(Email, selectedRoomNumber, selectedFood, selectedPrice);
                // Update the label to display the selected food and price
                selectionLabel.setText("Selected Food: " + selectedFood + " - Price: $" + selectedPrice);
            }
        });

        // Add components to the panel
        panel.add(foodComboBox, BorderLayout.NORTH);
        panel.add(selectionLabel, BorderLayout.CENTER);
        panel.add(orderButton, BorderLayout.SOUTH);

        orderFrame.add(panel);
        orderFrame.setVisible(true);
    }

        public int getRoomNumberBasedOnUserEmail(String userEmail) {
        String jdbcUrl = "jdbc:derby:HotelRecords;create=true"; // Update with your JDBC URL

        try (Connection connection = DriverManager.getConnection(jdbcUrl); PreparedStatement preparedStatement = connection.prepareStatement("SELECT RoomNumber FROM BookingRecords WHERE UserID = (SELECT ID FROM UserData WHERE Email = ?)")) {

            preparedStatement.setString(1, userEmail);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("RoomNumber");
                } else {
                    // Handle the case where no room is found for the user's email.
                    // You can return a default room number or display an error message to the user.
                    // For this example, I'll return -1 to indicate an error.
                    return -1;
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL error occurred:");
            e.printStackTrace();
            return -1; // Return -1 to indicate an error
        }
    }
        public void viewRoomServicesGUI() {
        JFrame viewFrame = new JFrame("View Room Services");
        viewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        viewFrame.setBounds(200, 200, 400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        StringBuilder roomServiceDetails = new StringBuilder();

        String jdbcUrl = "jdbc:derby:HotelRecords;create=true"; 
        try (Connection connection = DriverManager.getConnection(jdbcUrl);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT FoodName, FoodType, Price FROM RoomServicesRecords WHERE IsAvailable = true");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String foodName = resultSet.getString("FoodName");
                String foodType = resultSet.getString("FoodType");
                double price = resultSet.getDouble("Price");

                roomServiceDetails.append("Food Name: ").append(foodName).append("\n");
                roomServiceDetails.append("Food Type: ").append(foodType).append("\n");
                roomServiceDetails.append("Price: $").append(price).append("\n");
                roomServiceDetails.append("-----------------------\n");
            }
        } catch (SQLException ex) {
            System.err.println("SQL error occurred:");
            ex.printStackTrace();
        }

        textArea.setText(roomServiceDetails.toString());

       
        panel.add(scrollPane, BorderLayout.CENTER);

        viewFrame.add(panel);
        viewFrame.setVisible(true);
    }

}
