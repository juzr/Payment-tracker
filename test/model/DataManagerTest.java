/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import data_objects.Money;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.InputMismatchException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author development
 */
public class DataManagerTest {
    
    DataManager dataManager;
    
    public DataManagerTest() {

    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws InputMismatchException, IOException {
            dataManager = new DataManager("testfile_valid_format_more_lines");
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetNetAmounts(){
        List<Money> res = dataManager.getNetAmounts();
        assertEquals(5, res.size());
        assertTrue(res.contains(new Money("USD 1500")));
        assertTrue(res.contains(new Money("GBP 3600")));
        assertTrue(res.contains(new Money("HKD 150")));
        assertTrue(res.contains(new Money("RMB 900")));
        assertTrue(res.contains(new Money("NZD -1000")));
    }

    @Test
    public void testPutPayment_withFile() {
        dataManager.putPayment(new Money("USD 5000"));
        dataManager.putPayment(new Money("NZD -1000"));
        dataManager.putPayment(new Money("RMB -1000"));
        dataManager.putPayment(new Money("GBP 8000"));
        dataManager.putPayment(new Money("NZD -8000"));
        
        List<Money> res = dataManager.getNetAmounts();
        assertEquals(5, res.size());
        assertTrue(res.contains(new Money("USD 6500")));
        assertTrue(res.contains(new Money("GBP 11600")));
        assertTrue(res.contains(new Money("HKD 150")));
        assertTrue(res.contains(new Money("RMB -100")));
        assertTrue(res.contains(new Money("NZD -10000")));
    }
    
    @Test
    public void testPutPayment_withoutFile() {
        dataManager = new DataManager();
        dataManager.putPayment(new Money("USD 4000"));
        dataManager.putPayment(new Money("NZD -100"));
        dataManager.putPayment(new Money("RMB -800"));
        dataManager.putPayment(new Money("RMB 400"));
        dataManager.putPayment(new Money("GBP -3000"));
        dataManager.putPayment(new Money("NZD 6000"));
        
        List<Money> res = dataManager.getNetAmounts();
        assertEquals(4, res.size());
        assertTrue(res.contains(new Money("USD 4000")));
        assertTrue(res.contains(new Money("GBP -3000")));
        assertTrue(res.contains(new Money("RMB -400")));
        assertTrue(res.contains(new Money("NZD 5900")));
    }
    
}
