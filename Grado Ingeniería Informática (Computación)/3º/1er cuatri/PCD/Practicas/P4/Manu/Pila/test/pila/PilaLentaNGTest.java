/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pila;

import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Pc
 */
public class PilaLentaNGTest {
    
    public PilaLentaNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of getNum method, of class PilaLenta.
     */
    @Test
    public void testGetNum() {
        System.out.println("getNum");
        PilaLenta instance = null;
        int expResult = 0;
        int result = instance.getNum();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Apila method, of class PilaLenta.
     */
    @Test
    public void testApila() throws Exception {
        System.out.println("Apila");
        Object o = null;
        PilaLenta instance = null;
        instance.Apila(o);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Desapila method, of class PilaLenta.
     */
    @Test
    public void testDesapila() throws Exception {
        System.out.println("Desapila");
        PilaLenta instance = null;
        Object expResult = null;
        Object result = instance.Desapila();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Primero method, of class PilaLenta.
     */
    @Test
    public void testPrimero() throws Exception {
        System.out.println("Primero");
        PilaLenta instance = null;
        Object expResult = null;
        Object result = instance.Primero();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
