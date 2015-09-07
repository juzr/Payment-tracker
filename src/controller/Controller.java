/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import data_objects.ExchangeRate;
import data_objects.Money;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import model.DataManager;

/**
 *
 * @author development
 */
public class Controller {
    
    final private static String COMPARATIVE_CURRENCY = "USD";
    final private static String EXCHANGE_RATES_FILE = "er_" + COMPARATIVE_CURRENCY;
    
    private boolean useExchRates = false;

    DataManager dataManager;

    public void loadFile(String input) throws InputMismatchException, IOException {
        dataManager = new DataManager(input);
    }

    public void loadExchangeRates() throws InputMismatchException, IOException {
        if (dataManager == null) dataManager = new DataManager();
        dataManager.loadExchangeRates(EXCHANGE_RATES_FILE);
        useExchRates = true;
    }

    public void paymentEntered(Money payment) {
        if (dataManager == null) dataManager = new DataManager();
        dataManager.putPayment(payment);
    }

    public List<Money> getNetAmounts(){
        if (dataManager == null) dataManager = new DataManager();
        if (useExchRates){
            return enrichNetAmountsByComparativeCurrency(dataManager.getNetAmounts());
        } else {
            return dataManager.getNetAmounts();
        }
    }

    private List<Money> enrichNetAmountsByComparativeCurrency(List<Money> netAmounts) {
        if (dataManager == null) dataManager = new DataManager();
        Double rate;
        for (Money netAmount : netAmounts) {
            rate = dataManager.getExchangeRate(netAmount.getCurrency());
            if (rate == null) continue;
            netAmount.setComparativeCurrency(new ExchangeRate(COMPARATIVE_CURRENCY, rate));
        }
        return netAmounts;
    }
}
