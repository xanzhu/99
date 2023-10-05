package hotelmanager;

import javax.swing.JButton;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author junhy
 */
public class UserDashboardViewTest {

    private UserDashboardView ud;
    private BookingView bv;
    
    @Before
    public void setUp() {
        ud = new UserDashboardView("test", "test");
        bv = new BookingView();
    }

    @Test
    public void bookingBtn() {
        JButton bookingBtn = ud.getBookingBtn();
        assertNotNull(bookingBtn);
        assertTrue(bookingBtn.isVisible());
    }

    @Test
    public void roomServicesBtn() {
        JButton roomBtn = ud.getServiceBtn();
        assertNotNull(roomBtn);
        assertTrue(roomBtn.isVisible());
    }

    @Test
    public void testBookingBtnActionListener() {
        ud.simulateBookingBtnClick();
        assertTrue(ud.isAddBookingBtnEnabled());
    }
    
    public void testBookingBtnGUI(){
        ud.getAddBookingBtn().doClick();
        
        bv.addBookingGUI();
        
        assertTrue(bv.getBookingFrame().isVisible());
    }

}
