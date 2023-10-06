package hotelmanager;

import java.awt.Color;
import java.awt.Font;

/**
 *
 * @author Bobby Jenkins, Hyun il Jun
 */
public class AppUtils {

    // Reusable Text Format Function 
    public Font formatText(int size, boolean isBold) {
        int style = isBold ? Font.BOLD : Font.PLAIN;
        return new Font("sans serif", style, size);
    }

    // Reusable Text Format function.
    public Font formatText(int size) {
        return formatText(size, false);
    }

    // Define colour schemes
    public Color staffColour() {
        return Color.decode("#0047AB");
    }

    public Color userColour() {
        return Color.decode("#0096FF");
    }
}
