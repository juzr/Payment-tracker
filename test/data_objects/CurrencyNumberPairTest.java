/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_objects;

import java.util.InputMismatchException;
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
public class CurrencyNumberPairTest {
    
    public CurrencyNumberPairTest() {
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
    public void testToString() {
        String currSpaceNumber = "GBP 7000";
        Money money = new Money(currSpaceNumber);
        assertEquals(currSpaceNumber, money.toString());
    }

    @Test(expected = InputMismatchException.class)
    public void testConstructor1(){
        Money money = new Money("USD300");
    }
    
    @Test(expected = InputMismatchException.class)
    public void testConstructor2(){
        Money money = new Money("US 300");
    }
    
    @Test(expected = InputMismatchException.class)
    public void testConstructor3(){
        Money money = new Money("Usd 300");
    }
    @Test(expected = InputMismatchException.class)
    public void testConstructor4(){
        Money money = new Money("USD 5000 200");
    }
    
    @Test(expected = InputMismatchException.class)
    public void testConstructor5(){
        Money money = new Money("!!! 10");
    }
    
    @Test(expected = InputMismatchException.class)
    public void testConstructor6(){
        Money money = new Money("100 100");
    }
    
    
    @Test
    public void testMoneyEquals_true(){
        Money money = new Money("ABC 900");
        assertTrue(money.equals(new Money("ABC", 900)));
    }
    
    @Test
    public void testMoneyEquals_false(){
        Money money = new Money("ABC 1000");
        assertFalse(money.equals(new Money("ABC 2000")));
        assertFalse(money.equals(new Money("CCC 1000")));
        assertFalse(money.equals(null));
        assertFalse(money.equals(new CurrencyNumberPair() {

            @Override
            protected void setNumber(String numberStr) {
                ;
            }

            @Override
            protected boolean numberEquals(CurrencyNumberPair currencyNumberPair) {
                return true;
            }
        }));
    }
    
    @Test
    public void testExchangeRateEquals(){
        ExchangeRate exchangeRate = new ExchangeRate("AAA 1.456");
        assertTrue(exchangeRate.equals(new ExchangeRate("AAA 1.456")));
        
        assertFalse(exchangeRate.equals(new ExchangeRate("BBB 1.456")));
        assertFalse(exchangeRate.equals(new ExchangeRate("AAA 1.457")));
        assertFalse(exchangeRate.equals(null));
    }
    
    @Test(expected = InputMismatchException.class)
    public void testExchangeRateEqualsInvalidFormat1(){
        ExchangeRate exchangeRate = new ExchangeRate("AAA 1.45.6");
    }

    @Test(expected = InputMismatchException.class)
    public void testExchangeRateEqualsInvalidFormat2(){
        ExchangeRate exchangeRate = new ExchangeRate("AAA 1456 -");
    }

    @Test(expected = InputMismatchException.class)
    public void testExchangeRateEqualsInvalidFormat3(){
        ExchangeRate exchangeRate = new ExchangeRate("AAA 14Z56");
    }

    @Test
    public void testMoneyWIthComparativeCurrency(){
        ExchangeRate exchangeRate = new ExchangeRate("ABC 4");
        Money money = new Money("GBP 200");
        money.setComparativeCurrency(exchangeRate);
        assertEquals("GBP 200 (ABC 800.00)", money.toString());
    }
    
    @Test
    public void testMoneyWIthComparativeCurrency_CompCurrIsSame(){
        ExchangeRate exchangeRate = new ExchangeRate("GBP 1");
        Money money = new Money("GBP 200");
        money.setComparativeCurrency(exchangeRate);
        assertEquals("GBP 200", money.toString());
    }
    
}
