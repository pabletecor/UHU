/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pila;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Pc
 */
public class PilaLentaTest {
    
    public PilaLentaTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
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
        assertEquals(expResult, result);
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
        assertEquals(expResult, result);
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
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
