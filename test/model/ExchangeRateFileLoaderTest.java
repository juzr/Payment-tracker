/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.nio.file.NoSuchFileException;
import java.util.HashMap;
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
public class ExchangeRateFileLoaderTest {
    ExchangeRateFileLoader fileLoader;

    
    public ExchangeRateFileLoaderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        fileLoader = new ExchangeRateFileLoader();
    }
    
    @After
    public void tearDown() {
    }

    @Test(expected = NoSuchFileException.class)
    public void testGetExchangeRates_fileNotExist() throws Exception {
        String filename = "this_file_not_exist";
        fileLoader.getExchangeRates(filename);
    }
    
    @Test(expected = InputMismatchException.class)
    public void testGetExchangeRates_invalidFormat() throws Exception {
        String filename = "testfile_invalid_format";
        fileLoader.getExchangeRates(filename);
    }
    
    @Test
    public void testGetExchangeRates_ok() throws Exception {
        String filename = "er_USD";
        HashMap<String, Double> res = fileLoader.getExchangeRates(filename);
        assertEquals(7, res.size());
        assertEquals(0.62859, res.get("NZD"), 0.0005);
    }
    
}
