package hotelmanager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultCaret;

/**
 *
 * @author bobby
 */
public class BookingView {

    private Booking bk;
    private DBManager db;

    public BookingView() {
        db = new DBManager();
        bk = new Booking(db);
    }

    public void addBookingGUI() {
        JFrame BookingFrame = new JFrame("Add Booking");
        BookingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        BookingFrame.setBounds(450, 250, 600, 400);
        BookingFrame.setResizable(false);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(null);

        // Email
        JLabel EmailLabel = new JLabel("Enter Email:");
        EmailLabel.setFont(new Font("sans serif", Font.PLAIN, 15));
        EmailLabel.setBounds(130, 50, 150, 40);

        JTextField EmailField = new JTextField();
        EmailField.setBounds(280, 50, 200, 40);

        // Room Number
        JLabel RoomLabel = new JLabel("Room Number:");
        RoomLabel.setFont(new Font("sans serif", Font.PLAIN, 15));
        RoomLabel.setBounds(130, 110, 150, 40);

        JTextField RoomField = new JTextField();
        RoomField.setBounds(280, 110, 200, 40);

        // Date 
        JLabel DateLabel = new JLabel("Booking Date:");
        DateLabel.setFont(new Font("sans serif", Font.PLAIN, 15));
        DateLabel.setBounds(130, 180, 300, 40);

        // TODO: Lookup setting date using JCalendar instead of TextField ?  
        JTextField DateField = new JTextField();
        DateField.setBounds(280, 180, 200, 40);

        JLabel DateNote = new JLabel("Format: (YYYY-MM-DD)");
        DateNote.setBounds(310, 215, 200, 40);
        DateNote.setFont(new Font("sans serif", Font.ITALIC, 12));

        // Submit Button
        JButton SubmitBtn = new JButton("Book Room");
        SubmitBtn.setBounds(180, 290, 250, 40);
        SubmitBtn.setHorizontalAlignment(JButton.CENTER);
        SubmitBtn.setBackground(Color.decode("#0096FF"));
        SubmitBtn.setForeground(Color.WHITE);
        SubmitBtn.setOpaque(true);
        SubmitBtn.setBorderPainted(false);

        optionsPanel.add(EmailLabel);
        optionsPanel.add(EmailField);
        optionsPanel.add(RoomLabel);
        optionsPanel.add(RoomField);
        optionsPanel.add(DateLabel);
        optionsPanel.add(DateField);
        optionsPanel.add(DateNote);
        optionsPanel.add(SubmitBtn);

        // Add Options panel to frame
        BookingFrame.add(optionsPanel);
        BookingFrame.setVisible(true);

        SubmitBtn.addActionListener((ActionEvent e) -> {
            String Email = EmailField.getText();
            String RoomNum = RoomField.getText();
            String Date = DateField.getText();

            try {
                int roomNumber = Integer.parseInt(RoomNum);

                if (bk.validateAddRoom(roomNumber)) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date parsedDate = dateFormat.parse(Date);
                    java.sql.Date bookingDate = new java.sql.Date(parsedDate.getTime());

                    bk.addBooking(Email, roomNumber, bookingDate);

                    // Debugging
                    System.out.println("Added Room!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid room number format.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, "Invalid date format. Use yyyy-MM-dd.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            BookingFrame.dispose();
        });
    }

    public void viewBookingGUI(String email) {
        try {
            int userId = bk.userEmailID(email);

            if (userId == -1) {
                JOptionPane.showMessageDialog(null, "User not found.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String viewBookingSQL = "SELECT * FROM BookingRecords WHERE UserID = ?";

            Connection connection = db.getConnection();
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(viewBookingSQL,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

                preparedStatement.setInt(1, userId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (!resultSet.isBeforeFirst()) {
                        JOptionPane.showMessageDialog(null, "No bookings found for this user.", "Information",
                                JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }

                    StringBuilder bookingDetails = new StringBuilder();

                    while (resultSet.next()) {
                        int bookingID = resultSet.getInt("BookingID");
                        int roomNumber = resultSet.getInt("RoomNumber");
                        java.sql.Date bookingDate = resultSet.getDate("BookingDate");

                        bookingDetails.append("------------------------\n");
                        bookingDetails.append("Booking ID: ").append(bookingID).append("\n");
                        bookingDetails.append("Room Number: ").append(roomNumber).append("\n");
                        bookingDetails.append("Booking Date: ").append(bookingDate).append("\n");
                        bookingDetails.append("------------------------\n");
                    }

                    JTextArea textArea = new JTextArea(bookingDetails.toString());
                    textArea.setEditable(false);
                    JScrollPane scrollPane = new JScrollPane(textArea);
                    scrollPane.setPreferredSize(new Dimension(400, 300));
                    DefaultCaret caret = (DefaultCaret) textArea.getCaret();
                    caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
                    JOptionPane.showMessageDialog(null, scrollPane, "Booking Details",
                            JOptionPane.INFORMATION_MESSAGE);
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
        try {
            int userId = bk.userEmailID(userEmail);

            if (userId == -1) {
                JOptionPane.showMessageDialog(null, "User not found.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String selectBookingSQL = "SELECT * FROM BookingRecords WHERE UserID = ?";

            try (Connection connection = db.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(selectBookingSQL,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

                preparedStatement.setInt(1, userId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (!resultSet.isBeforeFirst()) {
                        JOptionPane.showMessageDialog(null, "No bookings found for this user.", "Information",
                                JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }

                    StringBuilder bookingDetails = new StringBuilder();
                    List<Integer> bookingIds = new ArrayList<>();

                    while (resultSet.next()) {
                        int bookingID = resultSet.getInt("BookingID");
                        int roomNumber = resultSet.getInt("RoomNumber");
                        java.sql.Date bookingDate = resultSet.getDate("BookingDate");

                        bookingDetails.append("Booking ID: ").append(bookingID).append("\n");
                        bookingDetails.append("Room Number: ").append(roomNumber).append("\n");
                        bookingDetails.append("Booking Date: ").append(bookingDate).append("\n");
                        bookingDetails.append("------------------------\n");

                        bookingIds.add(bookingID);
                    }

                    JTextArea textArea = new JTextArea(bookingDetails.toString());
                    textArea.setEditable(false);

                    JComboBox<Integer> bookingComboBox = new JComboBox<>(bookingIds.toArray(new Integer[0]));

                    JPanel panel = new JPanel();
                    panel.setLayout(new BorderLayout());
                    panel.add(bookingComboBox, BorderLayout.NORTH);
                    panel.add(new JScrollPane(textArea), BorderLayout.CENTER);

                    int option = JOptionPane.showConfirmDialog(null, panel, "Select Booking to Cancel",
                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                    if (option == JOptionPane.OK_OPTION) {
                        int selectedBookingId = (int) bookingComboBox.getSelectedItem();
                        bk.cancelBooking(selectedBookingId); // Implement cancellation logic
                        JOptionPane.showMessageDialog(null, "Booking with ID " + selectedBookingId + " canceled.",
                                "Information", JOptionPane.INFORMATION_MESSAGE);
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
    // TODO: Validation methods
}
