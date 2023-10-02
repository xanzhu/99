package bobbyhyunhotel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingGUI {

    private Booking booking;

    public BookingGUI() {
        booking = new Booking();
    }

    public void addBookingGUI() {
        JFrame addBookingFrame = new JFrame("Add Booking");
        addBookingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addBookingFrame.setBounds(200, 200, 400, 250); // Adjust the size as needed

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));

        JLabel userEmailLabel = new JLabel("User Email:");
        JTextField userEmailField = new JTextField();
        JLabel roomNumberLabel = new JLabel("Room Number:");
        JTextField roomNumberField = new JTextField();
        JLabel bookingDateLabel = new JLabel("Booking Date (yyyy-MM-dd):");
        JTextField bookingDateField = new JTextField();

        inputPanel.add(userEmailLabel);
        inputPanel.add(userEmailField);
        inputPanel.add(roomNumberLabel);
        inputPanel.add(roomNumberField);
        inputPanel.add(bookingDateLabel);
        inputPanel.add(bookingDateField);

        JButton addButton = new JButton("Add Booking");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userEmail = userEmailField.getText();
                String roomNumberText = roomNumberField.getText();
                String bookingDateString = bookingDateField.getText();

                try {
                    int roomNumber = Integer.parseInt(roomNumberText);

                    // Check if the room exists and is available
                    if (isRoomValidAndAvailable(roomNumber)) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date parsedDate = dateFormat.parse(bookingDateString);
                        java.sql.Date bookingDate = new java.sql.Date(parsedDate.getTime());

                        booking.addBooking(userEmail, roomNumber, bookingDate);

                        userEmailField.setText("");
                        roomNumberField.setText("");
                        bookingDateField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid room number or room is not available.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid room number format.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid date format. Use yyyy-MM-dd.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        addBookingFrame.add(mainPanel);
        addBookingFrame.setVisible(true);
    }

    public boolean isRoomValidAndAvailable(int roomNumber) {
        // JDBC URL for connecting to the Derby embedded database
        String jdbcUrl = "jdbc:derby:HotelRecords;create=true";

        try (Connection connection = DriverManager.getConnection(jdbcUrl); PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT IsAvailable FROM RoomRecords WHERE RoomNumber = ?")) {

            preparedStatement.setInt(1, roomNumber);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    boolean isAvailable = resultSet.getBoolean("IsAvailable");
                    return isAvailable;
                }
            }
        } catch (SQLException ex) {
            System.err.println("SQL error occurred:");
            ex.printStackTrace();
        }

        return false; // Return false if the room doesn't exist or there was an error
    }

    public void viewBookingGUI(String userEmail) {
        // JDBC URL for connecting to the Derby embedded database
        String jdbcUrl = "jdbc:derby:HotelRecords;create=true";

        try {
            // Step 1: Retrieve UserID based on the email
            int userId = booking.getUserIdByEmail(userEmail);

            if (userId == -1) {
                JOptionPane.showMessageDialog(null, "User not found.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Step 2: Retrieve booking details for the given UserID
            String selectBookingSQL = "SELECT * FROM BookingRecords WHERE UserID = ?";

            try (Connection connection = DriverManager.getConnection(jdbcUrl); PreparedStatement preparedStatement = connection.prepareStatement(selectBookingSQL,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, // Make the ResultSet scrollable
                    ResultSet.CONCUR_READ_ONLY)) {

                preparedStatement.setInt(1, userId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    // Check if there are any rows in the ResultSet
                    if (!resultSet.isBeforeFirst()) {
                        JOptionPane.showMessageDialog(null, "No bookings found for this user.", "Information", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }

                    // Create a StringBuilder to collect the booking details
                    StringBuilder bookingDetails = new StringBuilder();

                    while (resultSet.next()) {
                        int bookingID = resultSet.getInt("BookingID");
                        int roomNumber = resultSet.getInt("RoomNumber");
                        java.sql.Date bookingDate = resultSet.getDate("BookingDate");

                        // Append booking details to the StringBuilder
                        bookingDetails.append("Booking ID: ").append(bookingID).append("\n");
                        bookingDetails.append("Room Number: ").append(roomNumber).append("\n");
                        bookingDetails.append("Booking Date: ").append(bookingDate).append("\n");
                        bookingDetails.append("------------------------\n");
                    }

                    // Display the booking details using a dialog or text area, e.g., JOptionPane
                    JTextArea textArea = new JTextArea(bookingDetails.toString());
                    textArea.setEditable(false);
                    JScrollPane scrollPane = new JScrollPane(textArea);
                    scrollPane.setPreferredSize(new Dimension(400, 300));
                    JOptionPane.showMessageDialog(null, scrollPane, "Booking Details", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException ex) {
                System.err.println("SQL error occurred:");
                ex.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cancelBookingGUI(String userEmail) {
        // JDBC URL for connecting to the Derby embedded database
        String jdbcUrl = "jdbc:derby:HotelRecords;create=true";

        try {
            // Step 1: Retrieve UserID based on the email
            int userId = booking.getUserIdByEmail(userEmail);

            if (userId == -1) {
                JOptionPane.showMessageDialog(null, "User not found.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Step 2: Retrieve booking details for the given UserID
            String selectBookingSQL = "SELECT * FROM BookingRecords WHERE UserID = ?";

            try (Connection connection = DriverManager.getConnection(jdbcUrl); PreparedStatement preparedStatement = connection.prepareStatement(selectBookingSQL,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, // Make the ResultSet scrollable
                    ResultSet.CONCUR_READ_ONLY)) {

                preparedStatement.setInt(1, userId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    // Check if there are any rows in the ResultSet
                    if (!resultSet.isBeforeFirst()) {
                        JOptionPane.showMessageDialog(null, "No bookings found for this user.", "Information", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }

                    // Create a StringBuilder to collect the booking details
                    StringBuilder bookingDetails = new StringBuilder();

                    // Create a list to store booking IDs for cancellation
                    List<Integer> bookingIds = new ArrayList<>();
                    while (resultSet.next()) {
                        int bookingID = resultSet.getInt("BookingID");
                        int roomNumber = resultSet.getInt("RoomNumber");
                        java.sql.Date bookingDate = resultSet.getDate("BookingDate");

                        // Append booking details to the StringBuilder
                        bookingDetails.append("Booking ID: ").append(bookingID).append("\n");
                        bookingDetails.append("Room Number: ").append(roomNumber).append("\n");
                        bookingDetails.append("Booking Date: ").append(bookingDate).append("\n");
                        bookingDetails.append("------------------------\n");

                        // Add the booking ID to the list
                        bookingIds.add(bookingID);
                    }

                    // Display the booking details using a dialog or text area, e.g., JOptionPane
                    JTextArea textArea = new JTextArea(bookingDetails.toString());
                    textArea.setEditable(false);

                    // Create a combo box for selecting a booking to cancel
                    JComboBox<Integer> bookingComboBox = new JComboBox<>(bookingIds.toArray(new Integer[0]));

                    // Create a panel to hold the combo box and text area
                    JPanel panel = new JPanel();
                    panel.setLayout(new BorderLayout());
                    panel.add(bookingComboBox, BorderLayout.NORTH);
                    panel.add(new JScrollPane(textArea), BorderLayout.CENTER);

                    // Show the dialog with the combo box and text area
                    int option = JOptionPane.showConfirmDialog(null, panel, "Select Booking to Cancel",
                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                    if (option == JOptionPane.OK_OPTION) {
                        // Get the selected booking ID
                        int selectedBookingId = (int) bookingComboBox.getSelectedItem();

                        // TODO: Implement cancellation logic for the selected booking
                        booking.cancelBooking(selectedBookingId);
                        JOptionPane.showMessageDialog(null, "Booking with ID " + selectedBookingId + " canceled.", "Information", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } catch (SQLException ex) {
                System.err.println("SQL error occurred:");
                ex.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
