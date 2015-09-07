/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import data_objects.Money;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author development
 */
public class DataManager {
    private List<Money> paymentList;
    private ConcurrentHashMap<String, Integer> netAmounts;
    private HashMap<String, Double> exchangeRates;

    public DataManager() {
        paymentList = new LinkedList<>();
        netAmounts = new ConcurrentHashMap<>();
    }

    public DataManager(String inputFileName) throws InputMismatchException, IOException {
        PaymentFileLoader paymentFileLoader = new PaymentFileLoader();
        paymentList = paymentFileLoader.getPaymentList(inputFileName);
        netAmounts = new ConcurrentHashMap<>();
        for (Money payment : paymentList) {
            updateNetAmounts(payment);
        }
    }
    
    public void putPayment(Money payment){
        paymentList.add(payment);
        updateNetAmounts(payment);
    }
    
    public List<Money> getNetAmounts() {
        List<Money> res = new ArrayList<>();
        Set<Map.Entry<String, Integer>> entrySet = netAmounts.entrySet();
        for (Map.Entry<String, Integer> item : entrySet) {
            res.add(new Money(item.getKey(), item.getValue()));
        }
        return res;
    }

    private void updateNetAmounts(Money payment) {
        Integer netAmount = netAmounts.get(payment.getCurrency());
        if (netAmount == null){
            netAmount = payment.getAmount();
        } else {
            netAmount += payment.getAmount();
        }
        netAmounts.put(payment.getCurrency(), netAmount);
    }

    public Double getExchangeRate(String currency) {
        if (exchangeRates == null) return null;
        return exchangeRates.get(currency);
    }

    public void loadExchangeRates(String filename) throws InputMismatchException, IOException {
        ExchangeRateFileLoader fileLoader = new ExchangeRateFileLoader();
        exchangeRates = fileLoader.getExchangeRates(filename);
    }

}
