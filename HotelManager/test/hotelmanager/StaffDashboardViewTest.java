package hotelmanager;

import javax.swing.JButton;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Bobby Jenkins, Hyun il Jun
 */
public class StaffDashboardViewTest {

    private StaffDashboardView SD;
    private RoomServiceView roomServiceView;

    @Before
    public void setUp() {
        roomServiceView = new RoomServiceView();
        
        // Define Staff member
        SD = new StaffDashboardView("Hyun", "hyun@hotel.com");
        
        // Invalid staff example
        // SD = new StaffDashboardView("Bobby", "bobby@gmail.com");

        javax.swing.SwingUtilities.invokeLater(() -> {
            SD.setVisible(true);
        });
    }

    /**
     *  Verify menu buttons are visible for staff.
     */
    @Test
    public void checkRoomBtn() {
        JButton roomBtn = SD.manageRoomBtn; // Access directly
        assertNotNull(roomBtn);
        assertTrue(roomBtn.isVisible());
    }

    @Test
    public void checkServicesBtn() {
        JButton serviceBtn = SD.roomServiceBtn; // Access directly
        assertNotNull(serviceBtn);
        assertTrue(serviceBtn.isVisible());
    }

    @Test
    public void checkBookingBtn() {
        JButton bookingBtn = SD.bookingsBtn; // Access directly
        assertNotNull(bookingBtn);
        assertTrue(bookingBtn.isVisible());
    }
    
    /**
     * Verify Add Food GUI Opens correctly.
     */
    @Test
    public void testFoodGUI() {
        SD.roomServiceBtn.doClick(); // Access directly
        
        roomServiceView.addFoodGUI();

        assertTrue(roomServiceView.getAddFoodGUI().isVisible());
    }
    
    /**
     * Verify Staff is a staff member.
     */
    @Test
    public void checkEmail(){
        String emailCheck = SD.emailField; // Access directly
        
        if(emailCheck.contains("@hotel.com"))
        {
           assertTrue(true);
        } else {
            fail();
        }
    }
}
