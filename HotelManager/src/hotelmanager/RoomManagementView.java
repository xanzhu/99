package hotelmanager;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author bobby
 */
public class RoomManagementView {

    private RoomManagement rm;

    public RoomManagementView() {
        DBManager dbManager = new DBManager();
        rm = new RoomManagement(dbManager);
    }

    public void addRoomGUI() {

        // TODO: Add ToolTips
        JFrame addRoomFrame = new JFrame("Add a Room");
        addRoomFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addRoomFrame.setBounds(450, 250, 600, 400);
        addRoomFrame.setResizable(false);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(null);

        // Room options
        JLabel roomNumberLabel = new JLabel("Enter Room Number:");
        roomNumberLabel.setFont(new Font("sans serif", Font.PLAIN, 15));
        roomNumberLabel.setBounds(100, 50, 150, 40);

        JTextField roomNumberField = new JTextField();
        roomNumberField.setBounds(280, 50, 200, 40);

        JLabel roomTypeLabel = new JLabel("Enter Room Type:");
        roomTypeLabel.setFont(new Font("sans serif", Font.PLAIN, 15));
        roomTypeLabel.setBounds(100, 110, 150, 40);

        JTextField roomTypeField = new JTextField();
        roomTypeField.setBounds(280, 110, 200, 40);

        JLabel priceLabel = new JLabel("Enter Room Price:");
        priceLabel.setFont(new Font("sans serif", Font.PLAIN, 15));
        priceLabel.setBounds(100, 170, 150, 40);

        JTextField priceField = new JTextField();
        priceField.setBounds(280, 170, 200, 40);

        JLabel statusLabel = new JLabel("Available for Booking: ");
        statusLabel.setBounds(100, 230, 150, 40);

        JCheckBox statusCheckBox = new JCheckBox();
        statusCheckBox.setBounds(280, 230, 150, 40);

        JButton createRoomBtn = new JButton("Create Room");
        createRoomBtn.setBounds(180, 300, 250, 40);
        createRoomBtn.setHorizontalAlignment(JButton.CENTER);
        createRoomBtn.setBackground(Color.decode("#0047AB"));
        createRoomBtn.setForeground(Color.WHITE);
        createRoomBtn.setOpaque(true);
        createRoomBtn.setBorderPainted(false);

        optionsPanel.add(roomNumberLabel);
        optionsPanel.add(roomNumberField);
        optionsPanel.add(roomTypeLabel);
        optionsPanel.add(roomTypeField);
        optionsPanel.add(priceLabel);
        optionsPanel.add(priceField);
        optionsPanel.add(statusLabel);
        optionsPanel.add(statusCheckBox);
        optionsPanel.add(createRoomBtn);

        // Add Options panel to frame
        addRoomFrame.add(optionsPanel);
        addRoomFrame.setVisible(true);

        createRoomBtn.addActionListener((ActionEvent e) -> {
            String roomNumberText = roomNumberField.getText();
            String roomType = roomTypeField.getText();
            String priceText = priceField.getText();
            boolean isAvailable = statusCheckBox.isSelected();

            if (rm.validateAddRoom(roomNumberText, priceText, roomType)) {
                int roomNumber = Integer.parseInt(roomNumberText);
                double price = Double.parseDouble(priceText);
                rm.addRoom(roomNumber, roomType, price, isAvailable);

                addRoomFrame.dispose();
            }
        });
    }

    public void removeRoomGUI() {

        // TODO: Add Tooltips
        JFrame removeRoomFrame = new JFrame("Remove Room");
        removeRoomFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        removeRoomFrame.setBounds(450, 250, 600, 350);
        removeRoomFrame.setResizable(false);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(null);

        // Removal Options
        JLabel roomNumberLabel = new JLabel("Enter Room Number:");
        roomNumberLabel.setFont(new Font("sans serif", Font.PLAIN, 15));
        roomNumberLabel.setBounds(120, 70, 150, 40);

        JTextField roomNumberField = new JTextField();
        roomNumberField.setFont(new Font("sans serif", Font.PLAIN, 15));
        roomNumberField.setBounds(300, 70, 150, 40);

        JLabel note = new JLabel("* Warning this will delete a room!");
        note.setFont(new Font("sans serif", Font.PLAIN, 12));
        note.setForeground(Color.red);
        note.setBounds(180, 240, 200, 40);

        JButton deleteBtn = new JButton("Delete Room");
        deleteBtn.setBounds(120, 190, 330, 40);
        deleteBtn.setHorizontalAlignment(JButton.CENTER);
        deleteBtn.setBackground(Color.decode("#0047AB"));
        deleteBtn.setForeground(Color.WHITE);
        deleteBtn.setOpaque(true);
        deleteBtn.setBorderPainted(false);

        optionsPanel.add(roomNumberLabel);
        optionsPanel.add(roomNumberField);
        optionsPanel.add(note);
        optionsPanel.add(deleteBtn);

        deleteBtn.addActionListener((ActionEvent e) -> {

            int roomNumber = Integer.parseInt(roomNumberField.getText());

            rm.removeRoom(roomNumber);

            removeRoomFrame.dispose();
        });

        removeRoomFrame.add(optionsPanel);
        removeRoomFrame.setVisible(true);
    }

    public void roomPriceGUI() {

        JFrame roomPriceFrame = new JFrame("Update Room Price");
        roomPriceFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        roomPriceFrame.setBounds(450, 250, 600, 350);
        roomPriceFrame.setResizable(false);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(null);

        // Options
        JLabel roomNumberLabel = new JLabel("Enter Room Number:");
        roomNumberLabel.setFont(new Font("sans serif", Font.PLAIN, 15));
        roomNumberLabel.setBounds(120, 50, 150, 40);

        JTextField roomNumberField = new JTextField();
        roomNumberField.setFont(new Font("sans serif", Font.PLAIN, 15));
        roomNumberField.setBounds(300, 50, 150, 40);

        JLabel priceLabel = new JLabel("Enter Price:");
        priceLabel.setFont(new Font("sans serif", Font.PLAIN, 15));
        priceLabel.setBounds(120, 120, 150, 40);

        JTextField priceField = new JTextField();
        priceField.setBounds(300, 120, 150, 40);

        JButton priceBtn = new JButton("Update Price");
        priceBtn.setBounds(160, 240, 250, 40);
        priceBtn.setBackground(Color.decode("#0047AB"));
        priceBtn.setForeground(Color.WHITE);
        priceBtn.setOpaque(true);
        priceBtn.setBorderPainted(false);

        optionsPanel.add(roomNumberLabel);
        optionsPanel.add(roomNumberField);
        optionsPanel.add(priceLabel);
        optionsPanel.add(priceField);
        optionsPanel.add(priceBtn);

        // Action Listener
        priceBtn.addActionListener((ActionEvent e) -> {
            int roomNumber = Integer.parseInt(roomNumberField.getText());
            double newPrice = Double.parseDouble(priceField.getText());

            rm.updateRoomPrice(roomNumber, newPrice);

            roomPriceFrame.dispose();
        });

        roomPriceFrame.add(optionsPanel);
        roomPriceFrame.setVisible(true);
    }

    public void roomStatusGUI() {

        JFrame roomStatusFrame = new JFrame("Set Room Status");
        roomStatusFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        roomStatusFrame.setBounds(450, 250, 600, 350);
        roomStatusFrame.setResizable(false);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(null);

        // Options
        JLabel roomNumberLabel = new JLabel("Enter Room Number:");
        roomNumberLabel.setFont(new Font("sans serif", Font.PLAIN, 15));
        roomNumberLabel.setBounds(120, 50, 150, 40);

        JTextField roomNumberField = new JTextField();
        roomNumberField.setFont(new Font("sans serif", Font.PLAIN, 15));
        roomNumberField.setBounds(300, 50, 150, 40);

        JPanel availabilityPanel = new JPanel();
        availabilityPanel.setBounds(120, 130, 330, 40);

        JLabel availabilityLabel = new JLabel("Availability:");
        availabilityLabel.setFont(new Font("sans serif", Font.PLAIN, 15));
        JRadioButton availableRadio = new JRadioButton("Available");
        JRadioButton unavailableRadio = new JRadioButton("Unavailable");

        ButtonGroup availabilityGroup = new ButtonGroup();
        availabilityGroup.add(availableRadio);
        availabilityGroup.add(unavailableRadio);

        availabilityPanel.add(availabilityLabel);
        availabilityPanel.add(availableRadio);
        availabilityPanel.add(unavailableRadio);

        JButton updateBtn = new JButton("Update Room Status");
        updateBtn.setBounds(160, 240, 250, 40);
        updateBtn.setBackground(Color.decode("#0047AB"));
        updateBtn.setForeground(Color.WHITE);
        updateBtn.setOpaque(true);
        updateBtn.setBorderPainted(false);

        optionsPanel.add(roomNumberLabel);
        optionsPanel.add(roomNumberField);
        optionsPanel.add(availabilityPanel);
        optionsPanel.add(updateBtn);

        // Action Listener
        updateBtn.addActionListener((ActionEvent e) -> {
            int roomNumber = Integer.parseInt(roomNumberField.getText());
            boolean isAvailable = availableRadio.isSelected();

            if (rm.checkRoom(roomNumber)) {
                rm.roomStatus(roomNumber, isAvailable, true);

                roomStatusFrame.dispose();
            }

        });

        roomStatusFrame.add(optionsPanel);
        roomStatusFrame.setVisible(true);
    }
}
