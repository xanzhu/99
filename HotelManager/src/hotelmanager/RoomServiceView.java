package hotelmanager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.Map;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author bobby
 */
public class RoomServiceView {

    private final RoomServices rs;
    private final AppUtils u;

    public RoomServiceView() {
        DBManager dbManager = new DBManager();
        this.rs = new RoomServices(dbManager);
        this.u = new AppUtils();
    }
    
    public JFrame getAddFoodGUI() {
        return FoodFrame;
    }
    
    private JFrame FoodFrame;
    
    

    public void addFoodGUI() {
        FoodFrame = new JFrame("Add Food Item");
        FoodFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        FoodFrame.setBounds(450, 250, 600, 400);
        FoodFrame.setResizable(false);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(null);

        // Room options
        JLabel FoodLabel = new JLabel("Enter Food Name:");
        FoodLabel.setFont(u.formatText(15));
        FoodLabel.setBounds(100, 50, 150, 40);

        JTextField FoodField = new JTextField();
        FoodField.setBounds(280, 50, 200, 40);

        JLabel FoodTypeLabel = new JLabel("Enter Food Type:");
        FoodTypeLabel.setFont(u.formatText(15));
        FoodTypeLabel.setBounds(100, 110, 150, 40);

        JTextField FoodTypeField = new JTextField();
        FoodTypeField.setBounds(280, 110, 200, 40);

        JLabel FoodPriceLabel = new JLabel("Enter Food Price:");
        FoodPriceLabel.setFont(u.formatText(15));
        FoodPriceLabel.setBounds(100, 170, 150, 40);

        JTextField FoodPriceField = new JTextField();
        FoodPriceField.setBounds(280, 170, 200, 40);

        JLabel FoodStatusLabel = new JLabel("Available for Menu: ");
        FoodStatusLabel.setBounds(100, 230, 150, 40);

        JCheckBox FoodCheckbox = new JCheckBox();
        FoodCheckbox.setBounds(280, 230, 150, 40);

        JButton FoodBtn = new JButton("Add Food");
        FoodBtn.setBounds(180, 300, 250, 40);
        FoodBtn.setHorizontalAlignment(JButton.CENTER);
        FoodBtn.setBackground(u.staffColour());
        FoodBtn.setForeground(Color.WHITE);
        FoodBtn.setOpaque(true);
        FoodBtn.setBorderPainted(false);

        optionsPanel.add(FoodLabel);
        optionsPanel.add(FoodField);
        optionsPanel.add(FoodTypeLabel);
        optionsPanel.add(FoodTypeField);
        optionsPanel.add(FoodPriceLabel);
        optionsPanel.add(FoodPriceField);
        optionsPanel.add(FoodStatusLabel);
        optionsPanel.add(FoodCheckbox);
        optionsPanel.add(FoodBtn);

        // Add Options panel to frame
        FoodFrame.add(optionsPanel);
        FoodFrame.setVisible(true);

        // Action Listener
        FoodBtn.addActionListener((ActionEvent e) -> {
            String foodName = FoodField.getText();
            String foodType = FoodTypeField.getText();
            double price = Double.parseDouble(FoodPriceField.getText());
            boolean isAvailable = FoodCheckbox.isSelected();

            rs.addFood(foodName, foodType, price, isAvailable);

            FoodFrame.dispose();
        });
    }

    public void removeFoodGUI() {

        JFrame removeFoodFrame = new JFrame("Remove Food Item");
        removeFoodFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        removeFoodFrame.setBounds(450, 250, 600, 350);
        removeFoodFrame.setResizable(false);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(null);

        JLabel FoodLabel = new JLabel("Enter Food Name:");
        FoodLabel.setFont(u.formatText(15));
        FoodLabel.setBounds(120, 70, 150, 40);

        JTextField FoodField = new JTextField();
        FoodField.setFont(u.formatText(15));
        FoodField.setBounds(300, 70, 150, 40);

        JLabel note = new JLabel("* Warning this deletes food items.");
        note.setFont(u.formatText(12));
        note.setForeground(Color.red);
        note.setBounds(180, 240, 250, 40);

        JButton deleteBtn = new JButton("Delete Item");
        deleteBtn.setBounds(120, 190, 330, 40);
        deleteBtn.setHorizontalAlignment(JButton.CENTER);
        deleteBtn.setBackground(u.staffColour());
        deleteBtn.setForeground(Color.WHITE);
        deleteBtn.setOpaque(true);
        deleteBtn.setBorderPainted(false);

        optionsPanel.add(FoodLabel);
        optionsPanel.add(FoodField);
        optionsPanel.add(note);
        optionsPanel.add(deleteBtn);

        deleteBtn.addActionListener((ActionEvent e) -> {
            String foodName = FoodLabel.getText();

            rs.removeFood(foodName);

            removeFoodFrame.dispose();
        });

        removeFoodFrame.add(optionsPanel);
        removeFoodFrame.setVisible(true);
    }

    public void updateFoodPriceGUI() {

        JFrame FoodPriceFrame = new JFrame("Food Item Price");
        FoodPriceFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        FoodPriceFrame.setBounds(450, 250, 600, 350);
        FoodPriceFrame.setResizable(false);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(null);

        // Options
        JLabel FoodLabel = new JLabel("Enter Food Name:");
        FoodLabel.setFont(u.formatText(15));
        FoodLabel.setBounds(120, 50, 150, 40);

        JTextField FoodField = new JTextField();
        FoodField.setFont(u.formatText(15));
        FoodField.setBounds(300, 50, 150, 40);

        JLabel priceLabel = new JLabel("Enter Price:");
        priceLabel.setFont(u.formatText(15));
        priceLabel.setBounds(120, 120, 150, 40);

        JTextField priceField = new JTextField();
        priceField.setBounds(300, 120, 150, 40);

        JButton priceBtn = new JButton("Update Price");
        priceBtn.setBounds(160, 240, 250, 40);
        priceBtn.setBackground(u.staffColour());
        priceBtn.setForeground(Color.WHITE);
        priceBtn.setOpaque(true);
        priceBtn.setBorderPainted(false);

        optionsPanel.add(FoodLabel);
        optionsPanel.add(FoodField);
        optionsPanel.add(priceLabel);
        optionsPanel.add(priceField);
        optionsPanel.add(priceBtn);

        // Action Listener
        priceBtn.addActionListener((ActionEvent e) -> {
            String foodName = FoodField.getText();
            double price = Double.parseDouble(priceField.getText());

            rs.updateFoodPrice(foodName, price);

            FoodPriceFrame.dispose();
        });

        FoodPriceFrame.add(optionsPanel);
        FoodPriceFrame.setVisible(true);
    }

    public void updateFoodStatusGUI() {

        JFrame FoodStatusFrame = new JFrame("Food Item Status");
        FoodStatusFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        FoodStatusFrame.setBounds(450, 250, 600, 350);
        FoodStatusFrame.setResizable(false);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(null);

        JLabel FoodName = new JLabel("Enter Food Name:");
        FoodName.setFont(u.formatText(15));
        FoodName.setBounds(120, 50, 150, 40);

        JTextField FoodField = new JTextField();
        FoodField.setFont(u.formatText(15));
        FoodField.setBounds(300, 50, 150, 40);

        JPanel availabilityPanel = new JPanel();
        availabilityPanel.setBounds(120, 130, 330, 40);

        JLabel availabilityLabel = new JLabel("Availability:");
        availabilityLabel.setFont(u.formatText(15));
        JRadioButton availableRadio = new JRadioButton("Available");
        JRadioButton unavailableRadio = new JRadioButton("Unavailable");

        ButtonGroup availabilityGroup = new ButtonGroup();
        availabilityGroup.add(availableRadio);
        availabilityGroup.add(unavailableRadio);

        availabilityPanel.add(availabilityLabel);
        availabilityPanel.add(availableRadio);
        availabilityPanel.add(unavailableRadio);

        JButton updateBtn = new JButton("Update Item");
        updateBtn.setBounds(160, 240, 250, 40);
        updateBtn.setBackground(u.staffColour());
        updateBtn.setForeground(Color.WHITE);
        updateBtn.setOpaque(true);
        updateBtn.setBorderPainted(false);

        optionsPanel.add(FoodName);
        optionsPanel.add(FoodField);
        optionsPanel.add(availabilityPanel);
        optionsPanel.add(updateBtn);

        // Action Listener
        updateBtn.addActionListener((ActionEvent e) -> {
            String foodName = FoodField.getText();
            boolean isAvailable = availableRadio.isSelected();

            rs.updateFoodStatus(foodName, isAvailable);

            FoodStatusFrame.dispose();
        });

        FoodStatusFrame.add(optionsPanel);
        FoodStatusFrame.setVisible(true);
    }

    public void OrderFoodGUI(String userEmail) {

        String Email = userEmail;
        int selectedRoomNumber = rs.matchRoomEmail(Email);

        JFrame orderFrame = new JFrame("Order Room Service");
        orderFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        orderFrame.setBounds(450, 250, 600, 350);
        orderFrame.setResizable(false);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(null);

        // Retrieve food items and prices
        Map<String, Double> foodItemsWithPrices = rs.getItemsPrice();

        // ComboBox
        JComboBox<String> foodComboBox = new JComboBox<>(foodItemsWithPrices.keySet().toArray(new String[0]));
        foodComboBox.setBounds(160, 10, 250, 200);

        // Label
        JLabel selectedItem = new JLabel("Selected Food: ");
        selectedItem.setBounds(160, 50, 400, 30);
        selectedItem.setFont(u.formatText(15));

        // Order Button 
        JButton orderBtn = new JButton("Place Order");
        orderBtn.setBounds(160, 220, 250, 40);
        orderBtn.setBackground(u.userColour());
        orderBtn.setForeground(Color.WHITE);
        orderBtn.setOpaque(true);
        orderBtn.setBorderPainted(false);

        optionsPanel.add(orderBtn);
        optionsPanel.add(selectedItem);
        optionsPanel.add(foodComboBox);

        foodComboBox.addActionListener((ActionEvent e) -> {
            String selectedFood = (String) foodComboBox.getSelectedItem();
            double selectedPrice = foodItemsWithPrices.get(selectedFood);

            selectedItem.setText("Selected Food: " + selectedFood + ", Price: $" + selectedPrice);
        });

        orderBtn.addActionListener((ActionEvent e) -> {
            String selectedFood = (String) foodComboBox.getSelectedItem();
            double selectedPrice = foodItemsWithPrices.get(selectedFood);

            rs.orderRoomServices(Email, selectedRoomNumber, selectedFood, selectedPrice);

            orderFrame.dispose();
        });

        orderFrame.add(optionsPanel);
        orderFrame.setVisible(true);
    }

    public void viewRoomServicesGUI() {
        JFrame viewFrame = new JFrame("View Room Services");
        viewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        viewFrame.setBounds(450, 250, 600, 350);
        viewFrame.setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel titlePanel = new JPanel();
        
        titlePanel.setBackground(u.userColour());
        JLabel titleLabel = new JLabel("MENU");
        titleLabel.setFont(u.formatText(20, true));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);

        mainPanel.add(titlePanel, BorderLayout.NORTH);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(u.formatText(14));
        JScrollPane scrollPane = new JScrollPane(textArea);

        StringBuilder details = new StringBuilder();

        rs.viewFoodPriceItem(details);

        textArea.setText(details.toString());

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        viewFrame.add(mainPanel);
        viewFrame.setVisible(true);
    }
}
