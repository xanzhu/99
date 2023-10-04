package hotelmanager;

/**
 *
 * @author bobby
 */
import java.util.Date;

public class Book {
    private int bookingID;
    private int userID;
    private int roomNumber;
    private Date bookingDate;

    public Book(int bookingID, int userID, int roomNumber, Date bookingDate) {
        this.bookingID = bookingID;
        this.userID = userID;
        this.roomNumber = roomNumber;
        this.bookingDate = bookingDate;
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

