package hotelmanager;

import javax.swing.JButton;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Bobby Jenkins, Hyun il Jun
 */
public class UserDashboardViewTest {

    private UserDashboardView ude;

    private BookingView bv;
    private RoomServiceView rv;
    
    
    @Before
    public void setUp() {
        
        // Define member with active booking (Existing only)
        ude = new UserDashboardView("test", "test");
 
        rv = new RoomServiceView();
        bv = new BookingView();
    }

    /**
     * Verify Buttons are Visible.
     */
    @Test
    public void bookingBtn() {
        JButton bookingBtn = ude.getBookingBtn();
        assertNotNull(bookingBtn);
        assertTrue(bookingBtn.isVisible());
    }

    @Test
    public void roomServicesBtn() {
        JButton roomBtn = ude.getServiceBtn();
        assertNotNull(roomBtn);
        assertTrue(roomBtn.isVisible());
    }
    
    @Test
    public void chargesBtn(){
        JButton charges = ude.getBillingBtn();
        assertNotNull(charges);
        assertTrue(charges.isVisible());
    }

    /**
     * Test BookingGUI opens correctly.
     */
    @Test
    public void testBookingBtnGUI(){
        ude.getAddBookingBtn().doClick();
        
        bv.addBookingGUI();
        
        assertTrue(bv.getBookingFrame().isVisible());
    }
    
    /**
     * Test RoomServices View Menu GUI Opens correctly
     */
    @Test
    public void testBillingBtnGUI(){
        ude.getServiceBtn().doClick();
        
        rv.viewRoomServicesGUI();
        
        assertTrue(rv.getViewFrame().isVisible());
    }
    
}
