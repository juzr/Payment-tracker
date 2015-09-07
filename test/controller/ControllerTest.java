/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import data_objects.Money;
import java.io.IOException;
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
public class ControllerTest {
    Controller controller = new Controller();

    public ControllerTest() {
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

    @Test
    public void testLoadExchangeRates() throws IOException {
        controller.loadExchangeRates();
        controller.paymentEntered(new Money("GBP 1000"));
        List<Money> netAmounts = controller.getNetAmounts();
        assertEquals("GBP 1000 (USD 1516.35)", netAmounts.get(0).toString());
    }

    @Test
    public void testGetNetAmounts_withExchRates() throws IOException {
        controller.loadExchangeRates();
        controller.paymentEntered(new Money("QQQ 1000"));
        List<Money> netAmounts = controller.getNetAmounts();
        assertEquals("QQQ 1000", netAmounts.get(0).toString());
    }

    @Test
    public void testGetNetAmounts_withoutExchRates() throws IOException {
        controller.paymentEntered(new Money("QQQ 1000"));
        List<Money> netAmounts = controller.getNetAmounts();
        assertEquals("QQQ 1000", netAmounts.get(0).toString());
    }

}
