package hotelmanager;

import javax.swing.UIManager;

/**
 *
 * @author bobby
 */
public class HotelManager {

    public static void main(String[] args) {

        // Load in databases!
        DBManager dbManager = new DBManager();
        dbManager.createHotelData();
        dbManager.createRoomData();
        dbManager.createRoomServiceData();
        dbManager.createBookingData();

        // Load in default view
        WelcomeView welcomeView = new WelcomeView();
        WelcomeController welcomeController = new WelcomeController(welcomeView);
        welcomeView.setVisible(true);

        // Testing: shutting down server
//        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//            dbManager.shutdown();
//        }));


        // Use Mac Buttons! :D
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
