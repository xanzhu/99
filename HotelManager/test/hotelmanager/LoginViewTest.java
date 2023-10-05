/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package hotelmanager;

import javax.swing.JButton;
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
public class LoginViewTest {
    
    public LoginViewTest() {
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
     * Test of getLoginButton method, of class LoginView.
     */
    @Test
    public void testGetLoginButton() {
        System.out.println("getLoginButton");
        LoginView instance = new LoginView();
        JButton expResult = null;
        JButton result = instance.getLoginButton();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getReturnButton method, of class LoginView.
     */
    @Test
    public void testGetReturnButton() {
        System.out.println("getReturnButton");
        LoginView instance = new LoginView();
        JButton expResult = null;
        JButton result = instance.getReturnButton();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEmail method, of class LoginView.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        LoginView instance = new LoginView();
        String expResult = "";
        String result = instance.getEmail();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPassword method, of class LoginView.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        LoginView instance = new LoginView();
        String expResult = "";
        String result = instance.getPassword();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class LoginView.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        LoginView.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
