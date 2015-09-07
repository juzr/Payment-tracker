/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import data_objects.CurrencyNumberPair;
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
public class PaymentFileLoaderTest {
    PaymentFileLoader fileLoader;
    
    public PaymentFileLoaderTest() {
        fileLoader = new PaymentFileLoader();
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
    public void testgetPaymentList() throws NoSuchFileException, IOException, InputMismatchException {
        String filename = "testfile_valid_input_5pairs_with_emptylines";
        List<Money> paymentList = fileLoader.getPaymentList(filename);
        assertEquals(5, paymentList.size());
        assertEquals(new Money("GBP 14000"), paymentList.get(4));
    }
    
    @Test(expected = NoSuchFileException.class)
    public void testgetPaymentList_FileNotExist() throws InputMismatchException, IOException{
        fileLoader.getPaymentList("testfile_this_file_not_exist");
    }
    
    @Test(expected = InputMismatchException.class)
    public void testgetPaymentList_InvalidFormat() throws IOException{
        fileLoader.getPaymentList("testfile_invalid_format");
    }

    @Test(expected = IOException.class)
    public void testGetPaymentList_emptyFilename() throws InputMismatchException, IOException{
        fileLoader.getPaymentList("");
    }

    @Test(expected = IOException.class)
    public void testGetPaymentList_nullFilename() throws InputMismatchException, IOException{
        fileLoader.getPaymentList(null);
    }
}
