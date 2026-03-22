package com.app.view;

import com.app.exception.DatabaseException;
import com.app.model.Transaction;
import com.app.model.User;
import com.app.service.TransactionService;
import com.app.service.impl.TransactionServiceImpl;
import com.app.util.Asciiart;
import com.app.util.InputUtil;

import java.util.List;

public class TransactionView {

    private final TransactionService transactionService = new TransactionServiceImpl();
    private final Asciiart art = new Asciiart();

    // CUSTOMER SIDE
    public void customerMenu(User user) {

        while (true) {
            
            System.out.println("\n\t============ TRANSACTION MENU ============");
            System.out.println("\t|\t1. View My Transactions");
            System.out.println("\t|\t2. Pay Transaction");
            System.out.println("\t|\t3. Back");
            System.out.println("\t==========================================");
            int choice = InputUtil.getInt("\tChoose option: ");
            System.out.println("\t==========================================\n");
            switch (choice) {

                case 1:
                    viewUserTransactions(user);
                    break;

                case 2:
                    payTransaction();
                    break;

                case 3:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // ADMIN SIDE
    public void adminMenu() {

        while (true) {
            System.out.println("\n===== ALL TRANSACTIONS =====");
            System.out.println("1. View All Transactions");
            System.out.println("2. Back");

            int choice = InputUtil.getInt("Choose option: ");

            switch (choice) {

                case 1:
                    viewAllTransactions();
                    break;

                case 2:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // VIEW USER TRANSACTIONS
    private void viewUserTransactions(User user) {
        List<Transaction> list = transactionService.getUserTransactions(user.getUserId());

        System.out.println("\n\t===== MY TRANSACTIONS =====");

        if (list.isEmpty()) {
            System.out.println("\tNo transactions found.");
            return;
        }

        for (Transaction t : list) {
            System.out.println("\tID: " + t.getTransactionId());
            System.out.println("\tTotal: ₱" + t.getTotalAmount());
            System.out.println("\tStatus: " + getStatus(t.getIsPaid()));
            System.out.println("\tDate: " + t.getTransactionDateTime());
            System.out.println("\t--------------------------");
        }
    }

    // VIEW ALL (ADMIN)
    private void viewAllTransactions() {
        List<Transaction> list = transactionService.getAllTransactions();

        System.out.println("\n\t===== ALL TRANSACTIONS =====");

        if (list.isEmpty()) {
            System.out.println("\tNo transactions found.");
            return;
        }

        for (Transaction t : list) {
            System.out.println("\tID: " + t.getTransactionId());
            System.out.println("\tUser ID: " + t.getUserId());
            System.out.println("\tTotal: ₱" + t.getTotalAmount());
            System.out.println("\tStatus: " + getStatus(t.getIsPaid()));
            System.out.println("\t--------------------------");
        }
    }

    // PAY TRANSACTION
    private void payTransaction() {
        int id = InputUtil.getInt("\tEnter Transaction ID to pay: ");

        boolean success = transactionService.payTransaction(id);

        if (success) {
            System.out.println("\t-> Payment successful! Thank you.");
        } else {
            System.out.println("\tX Payment failed. Please check the Transaction ID.");
        }
    }

    // STATUS HELPER
    private String getStatus(int status) {
        return (status == 1) ? "PAID" : "PENDING";
    }
}
