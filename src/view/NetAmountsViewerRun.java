/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import data_objects.Money;
import java.util.List;

/**
 *
 * @author development
 */
public class NetAmountsViewerRun implements Runnable {
    final long WAITING_TIME = 60000;   // 1 min
    Controller controller;

    NetAmountsViewerRun(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        while (true){
            try {
                showMsg();
                Thread.sleep(WAITING_TIME);
            } catch (InterruptedException ex) {
                return;
            }
        }
    }

    private void showMsg() {
        List<Money> netAmounts = controller.getNetAmounts();
        String msg = "\n";
        for (Money netAmount : netAmounts) {
            if (netAmount.getAmount() != 0) msg += netAmount + "\n";
        }
        System.out.println(msg);
    }
    
}
