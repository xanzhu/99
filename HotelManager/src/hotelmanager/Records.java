package hotelmanager;

/**
 *
 * @author bobby
 */
import java.util.Date;

public class Records {
    private int bookingID;
    private int userID;
    private int roomNumber;
    private Date bookingDate;
    
    // Room Services
    private int orderID;
    private String foodName;
    private double price;

    public Records(int bookingID, int userID, int roomNumber, Date bookingDate) {
        this.bookingID = bookingID;
        this.userID = userID;
        this.roomNumber = roomNumber;
        this.bookingDate = bookingDate;
    }
    
    // Constructor for Room Service Records
    public Records(int orderID, int userID, int roomNumber, String foodPrice, double price){
        this.orderID = orderID;
        this.userID = userID;
        this.roomNumber = roomNumber;
        this.foodName = foodPrice;
        this.price = price;
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Date getBookingDate() {
        return bookingDate;
    }
    
    public int getOrderID(){
        return orderID;
    }
    
    public String getFoodName(){
        return foodName;
    }
    
    public double getPrice(){
        return price;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    @Override
    public String toString() {
        return "BookingRecord{" +
                "bookingID=" + bookingID +
                ", userID=" + userID +
                ", roomNumber=" + roomNumber +
                ", bookingDate=" + bookingDate +
                '}';
    }
}

