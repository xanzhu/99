package hotelmanager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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

    private final Booking bk;
    private final DBManager db;
    private final AppUtils u;

    public BookingView() {
        this.db = new DBManager();
        this.bk = new Booking(db);
        this.u = new AppUtils();
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
        EmailLabel.setFont(u.formatText(15));
        EmailLabel.setBounds(130, 50, 150, 40);

        JTextField EmailField = new JTextField();
        EmailField.setBounds(280, 50, 200, 40);

        // Room Number
        JLabel RoomLabel = new JLabel("Room Number:");
        RoomLabel.setFont(u.formatText(15));
        RoomLabel.setBounds(130, 110, 150, 40);

        JTextField RoomField = new JTextField();
        RoomField.setBounds(280, 110, 200, 40);

        // Date 
        JLabel DateLabel = new JLabel("Booking Date:");
        DateLabel.setFont(u.formatText(15));
        DateLabel.setBounds(130, 180, 300, 40);

        // TODO: Lookup setting date using JCalendar instead of TextField ?  
        JTextField DateField = new JTextField();
        DateField.setBounds(280, 180, 200, 40);

        JLabel DateNote = new JLabel("Format: (YYYY-MM-DD)");
        DateNote.setBounds(310, 215, 200, 40);
        DateNote.setFont(u.formatText(12));

        // Submit Button
        JButton SubmitBtn = new JButton("Book Room");
        SubmitBtn.setBounds(180, 290, 250, 40);
        SubmitBtn.setHorizontalAlignment(JButton.CENTER);
        SubmitBtn.setBackground(u.userColour());
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

        // Validate Staff !
        boolean isStaffBooking = Email.contains("@hotel.com");

        try {
            int roomNumber = Integer.parseInt(RoomNum);

            if (bk.validateAddRoom(roomNumber)) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date parsedDate = dateFormat.parse(Date);
                java.sql.Date bookingDate = new java.sql.Date(parsedDate.getTime());

                if (isStaffBooking) {
                    bk.staffAddBooking(Email, roomNumber, bookingDate);
                } else {
                    bk.addBooking(Email, roomNumber, bookingDate);
                }
                   
            }
        // TODO: Fix error messages
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid room number format.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Invalid date format. Use yyyy-MM-dd.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        BookingFrame.dispose();
    });

    }

    // TODO Reimplement db logic -> booking
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

    // TODO Reimplement db logic -> booking
    public void cancelBookingGUI(String userEmail) {
        try {
            int userId = bk.userEmailID(userEmail);

            if (userId == -1) {
                JOptionPane.showMessageDialog(null, "User not found.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String selectBookingSQL = "SELECT * FROM BookingRecords WHERE UserID = ?";

            Connection connection = db.getConnection();

            try (PreparedStatement preparedStatement = connection.prepareStatement(selectBookingSQL,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

                preparedStatement.setInt(1, userId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (!resultSet.isBeforeFirst()) {
                        JOptionPane.showMessageDialog(null, "No bookings found for this user.", "Booking Cancel Menu",
                                JOptionPane.ERROR_MESSAGE);
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
                        bk.cancelBooking(selectedBookingId);
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

    // Check for Existing Booking
    public boolean NewBookingCheck(String userEmail) {
        int userId = bk.userEmailID(userEmail);
        return bk.hasBooking(userId);
    }

    // TODO: Validation methods
    
    public void staffCancelBookingGUI(){
        JFrame staffCancelFrame = new JFrame("Staff Booking Remove");
        staffCancelFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        staffCancelFrame.setBounds(450, 250, 600, 400);
        staffCancelFrame.setResizable(false);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(null);
        
        JLabel BookingLabel = new JLabel("Enter Booking ID:");
        BookingLabel.setFont(u.formatText(15));
        BookingLabel.setBounds(120, 70, 150, 40);
        
        JTextField BookingField = new JTextField();
        BookingField.setBounds(300, 70, 150, 40);
        
        JLabel BookingWarning = new JLabel("Warning: This will delete a users booking.");
        BookingWarning.setFont(u.formatText(12));
        BookingWarning.setForeground(Color.red);
        BookingWarning.setBounds(180, 240, 200, 40);
        
        JButton removeBtn = new JButton("Remove Booking");
        removeBtn.setBounds(180, 290, 250, 40);
        removeBtn.setHorizontalAlignment(JButton.CENTER);
        removeBtn.setBackground(u.staffColour());
        removeBtn.setForeground(Color.WHITE);
        removeBtn.setOpaque(true);
        removeBtn.setBorderPainted(false);
        
        optionsPanel.add(BookingLabel);
        optionsPanel.add(BookingField);
        optionsPanel.add(BookingWarning);
        optionsPanel.add(removeBtn);
        
        staffCancelFrame.add(optionsPanel);
        staffCancelFrame.setVisible(true);
        
        removeBtn.addActionListener((ActionEvent e) -> {

            int bookingID = Integer.parseInt(BookingField.getText());
            
            bk.staffCancelBooking(bookingID);

            staffCancelFrame.dispose();
        });
    }
}
