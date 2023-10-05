/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package hotelmanager;

import javax.swing.JFrame;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author junhy
 */
public class RoomServiceViewTest {
    
    public RoomServiceViewTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAddFoodGUI method, of class RoomServiceView.
     */
    @Test
    public void testGetAddFoodGUI() {
        System.out.println("getAddFoodGUI");
        RoomServiceView instance = new RoomServiceView();
        JFrame expResult = null;
        JFrame result = instance.getAddFoodGUI();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addFoodGUI method, of class RoomServiceView.
     */
    @Test
    public void testAddFoodGUI() {
        System.out.println("addFoodGUI");
        RoomServiceView instance = new RoomServiceView();
        instance.addFoodGUI();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeFoodGUI method, of class RoomServiceView.
     */
    @Test
    public void testRemoveFoodGUI() {
        System.out.println("removeFoodGUI");
        RoomServiceView instance = new RoomServiceView();
        instance.removeFoodGUI();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateFoodPriceGUI method, of class RoomServiceView.
     */
    @Test
    public void testUpdateFoodPriceGUI() {
        System.out.println("updateFoodPriceGUI");
        RoomServiceView instance = new RoomServiceView();
        instance.updateFoodPriceGUI();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateFoodStatusGUI method, of class RoomServiceView.
     */
    @Test
    public void testUpdateFoodStatusGUI() {
        System.out.println("updateFoodStatusGUI");
        RoomServiceView instance = new RoomServiceView();
        instance.updateFoodStatusGUI();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of OrderFoodGUI method, of class RoomServiceView.
     */
    @Test
    public void testOrderFoodGUI() {
        System.out.println("OrderFoodGUI");
        String userEmail = "";
        RoomServiceView instance = new RoomServiceView();
        instance.OrderFoodGUI(userEmail);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of viewRoomServicesGUI method, of class RoomServiceView.
     */
    @Test
    public void testViewRoomServicesGUI() {
        System.out.println("viewRoomServicesGUI");
        RoomServiceView instance = new RoomServiceView();
        instance.viewRoomServicesGUI();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
