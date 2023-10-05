package hotelmanager;

import javax.swing.UIManager;

/**
 *
 * @author Bobby Jenkins, Hyun il Jun
 */
public class HotelManager {

    public static void main(String[] args) {

        // Load & Create databases!
        DBManager dbManager = new DBManager();
        dbManager.createHotelData();
        dbManager.createRoomData();
        dbManager.createRoomServiceData();
        dbManager.createBookingData();
        dbManager.createOrderData();

        // Loads in default splash screen.
        WelcomeView welcomeView = new WelcomeView();
        WelcomeController welcomeController = new WelcomeController(welcomeView);
        welcomeView.setVisible(true);
        
        /**
         * Ensures Designs are reflective of
         * Mac to Windows Systems.
         */
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
