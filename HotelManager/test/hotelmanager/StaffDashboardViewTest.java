/*
 * Hello world
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package hotelmanager;

import javax.swing.JButton;
import javax.swing.SwingUtilities;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author bobby
 */
public class StaffDashboardViewTest {

    private StaffDashboardView SD;
    private RoomServiceView roomServiceView;

    @Before
    public void setUp() {
        roomServiceView = new RoomServiceView();
        SD = new StaffDashboardView("Test", "john@Hotel.com");

        javax.swing.SwingUtilities.invokeLater(() -> {
            SD.setVisible(true);
        });
    }

    /**
     * Check 1: Verify main buttons are visible.
     */
    @Test
    public void checkRoomBtn() {
        JButton roomBtn = SD.getManageRoomBtn();
        assertNotNull(roomBtn);
        assertTrue(roomBtn.isVisible());
    }

    @Test
    public void checkServicesBtn() {
        JButton serviceBtn = SD.getRoomServiceBtn();
        assertNotNull(serviceBtn);
        assertTrue(serviceBtn.isVisible());
    }

    @Test
    public void checkBookingBtn() {
        JButton bookingBtn = SD.getBookingsBtn();
        assertNotNull(bookingBtn);
        assertTrue(bookingBtn.isVisible());
    }
    
     /**
     * Check 2: Verify Add Food GUI Opens
     */
    @Test
    public void testFoodGUI() {
        SD.getRoomServiceBtn().doClick();
        
         roomServiceView.addFoodGUI();

        assertTrue(roomServiceView.getAddFoodGUI().isVisible());
    }
}
