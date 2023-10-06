package hotelmanager;

import javax.swing.JButton;
import javax.swing.SwingUtilities;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Bobby Jenkins, Hyun il Jun
 */
public class UserDashboardViewTest {

    private UserDashboardView UD;

    private BookingView bv;
    private RoomServiceView rv;

    @Before
    public void setUp() {

        // Define member with active booking (Existing only)
        UD = new UserDashboardView("peter", "peter@gmail.com");

//        rv = new RoomServiceView();
//        bv = new BookingView();
    }

    /**
     * Verify Buttons are Visible.
     */
    @Test
    public void bookingBtn() {
        JButton bookingBtn = UD.bookingsBtn;
        assertNotNull(bookingBtn);
        assertTrue(bookingBtn.isVisible());
    }

    @Test
    public void roomServicesBtn() {
        JButton roomBtn = UD.roomServiceBtn;
        assertNotNull(roomBtn);
        assertTrue(roomBtn.isVisible());
    }

    @Test
    public void chargesBtn() {
        JButton charges = UD.getBillingBtn();
        assertNotNull(charges);
        assertTrue(charges.isVisible());
    }

    /**
     * Test BookingGUI opens correctly.
     */
    @Test
    public void testBookingBtnGUI() {
        SwingUtilities.invokeLater(() -> {
            UD.addBookingBtn.doClick();
            boolean viewBookingGUI = bv.getBookingFrame().isVisible();
            assertTrue(viewBookingGUI);
        });
    }

    /**
     * Test RoomServices View Menu GUI Opens correctly
     */
    @Test
    public void testBillingBtnGUI() {
        SwingUtilities.invokeLater(() -> {
        UD.roomServiceBtn.doClick();
        boolean viewMenuGUI = rv.getViewFrame().isVisible();
        assertTrue(viewMenuGUI);
        });
    }
}
