package hotelmanager;

import javax.swing.UIManager;

/**
 *
 * @author bobby
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

        // Load in default view
        WelcomeView welcomeView = new WelcomeView();
        WelcomeController welcomeController = new WelcomeController(welcomeView);
        welcomeView.setVisible(true);
        
        // Use Mac Buttons! :D
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
