/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import java.util.Scanner;
import data_objects.*;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.InputMismatchException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author development
 */
public class UserInteractor {

    private Controller controller;    
    
    public void run() {
        System.out.println(" ### Payment Tracker ### ");
        
        controller = new Controller();

        Scanner in = new Scanner(System.in);
        tryLoadFile(in);
        tryLoadExchangeRates();
        Thread viewerThread = startViewerThread(controller);
        userInputCycle(in);
        viewerThread.interrupt();
    }

    private void tryLoadFile(Scanner in) {
        String input;
        for (int i=0; i<10; i++){
            try {
                System.out.println("Enter filename or '.' for no file: ");
                input = in.nextLine();
                if (input.equals(".")) break;
                controller.loadFile(input);
                System.out.println("File loaded");
                break;
            } catch (InputMismatchException ex) {
                System.out.println("Invalid format of this file");
            } catch (NoSuchFileException ex){
                System.out.println("File not found");
            } catch (IOException ex) {
                System.out.println("IO error");
            }
        }
    
    }

    private void tryLoadExchangeRates() {
        try {
            controller.loadExchangeRates();
            System.out.println("Exchange rates loaded");
        } catch (InputMismatchException ex) {
            System.out.println("Invalid format of exchange rates file... ignoring...");
        } catch (NoSuchFileException ex) {
            System.out.println("Exchange rates file not found... ignoring...");
        } catch (IOException ex) {
            System.out.println("IO error - exchange rates file... ignoring...");
        }
    }

    private Thread startViewerThread(Controller controller) {
        NetAmountsViewerRun viewerRun = new NetAmountsViewerRun(controller);
        Thread thread = new Thread(viewerRun);
        thread.start();
        return thread;
    }

    private void userInputCycle(Scanner in) {
        String input;
        System.out.println("Enter payment ([CURRENCY] [amount]) or \"quit\" to exit: ");
        while (true) {
            input = in.nextLine();
            
            if (input.equals("q")) break;
            if (input.equals("quit")) break;
            
            try {
                Money payment = new Money(input);
                controller.paymentEntered(payment);
            } catch (InputMismatchException exc) {
                System.out.println("Invalid format. [A-Z][A-Z][A-Z] [n]   e.g. USD -500");
            }
        }
    }

}
