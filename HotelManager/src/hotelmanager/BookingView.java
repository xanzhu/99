package hotelmanager;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author bobby
 */
public class BookingView {
    
    private Booking bk;
    
    public BookingView(){
        DBManager db = new DBManager();
        bk = new Booking(db);
    }
    
    public void addBookingGUI(){
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
        
        // TODO: Action Listener & Validation
    }
    
//    public void viewBookingGUI(){
//        
//    }
//    
//    public void cancelBookingGUI(){
//        
//    }
    
    // TODO: Validation methods
}
