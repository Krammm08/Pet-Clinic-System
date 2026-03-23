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
                    payTransaction(user);
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
            // --- NEW LOGIC: Check if the date is null before printing ---
            String dateStr = (t.getTransactionDateTime() != null) ? t.getTransactionDateTime() : "Not Paid Yet";

            System.out.println("\tID: " + t.getTransactionId());
            System.out.println("\tTotal: ₱" + t.getTotalAmount());
            System.out.println("\tStatus: " + getStatus(t.getIsPaid()));
            System.out.println("\tDate: " + dateStr); // Prints the date or "Not Paid Yet"
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
            // --- NEW LOGIC: Do the same check for the admin view ---
            String dateStr = (t.getTransactionDateTime() != null) ? t.getTransactionDateTime() : "Not Paid Yet";

            System.out.println("\tID: " + t.getTransactionId());
            System.out.println("\tUser ID: " + t.getUserId());
            System.out.println("\tTotal: ₱" + t.getTotalAmount());
            System.out.println("\tStatus: " + getStatus(t.getIsPaid()));
            System.out.println("\tDate: " + dateStr); // Admin can now see the date too!
            System.out.println("\t--------------------------");
        }
    }

    // PAY TRANSACTION
    private void payTransaction(User user) {
        System.out.println("\n\t--- PAY TRANSACTION ---");

        try {
            // 1. Fetch ALL transactions for this user
            List<Transaction> myTransactions = transactionService.getUserTransactions(user.getUserId());

            // 2. Filter for UNPAID only (Status 0)
            List<Transaction> unpaidBills = new java.util.ArrayList<>();
            for (Transaction t : myTransactions) {
                if (t.getIsPaid() == 0) {
                    unpaidBills.add(t);
                }
            }

            if (unpaidBills.isEmpty()) {
                System.out.println("\t[!] You have no pending bills. All caught up!");
                return;
            }

            // 3. Display the detailed bill list
            System.out.println("\n\t--- YOUR UNPAID BILLS ---");
            System.out.println("\tID\tService ID\tAmount\t\tStatus");
            System.out.println("\t--------------------------------------------------");
            for (Transaction t : unpaidBills) {
                System.out.println("\t[" + t.getTransactionId() + "]\tService #" + t.getServiceId()
                        + "\tPhp " + t.getTotalAmount() + "\t[PENDING]");
            }
            System.out.println("\t--------------------------------------------------");

            // 4. Select the bill
            int transId = InputUtil.getInt("\tSelect Transaction ID to pay: ");

            // 5. Verification & Summary
            Transaction selectedBill = null;
            for (Transaction t : unpaidBills) {
                if (t.getTransactionId() == transId) {
                    selectedBill = t;
                    break;
                }
            }

            if (selectedBill == null) {
                System.out.println("\tX Invalid ID. Please choose a bill from the list above.");
                return;
            }

            // --- THE CONFIRMATION STEP ---
            System.out.println("\n\t--- PAYMENT SUMMARY ---");
            System.out.println("\tService ID: " + selectedBill.getServiceId());
            System.out.println("\tTotal Due : Php " + selectedBill.getTotalAmount());
            String confirm = InputUtil.getString("\tConfirm payment? (Y/N): ");

            if (confirm.equalsIgnoreCase("Y")) {
                boolean success = transactionService.payTransaction(transId);
                if (success) {
                    System.out.println("\t-> Payment successful! Your appointment is now cleared.");
                } else {
                    System.out.println("\tX Payment failed in database.");
                }
            } else {
                System.out.println("\t-> Payment cancelled.");
            }

        } catch (Exception e) {
            System.out.println("\tX Error: " + e.getMessage());
        }
    }

    // STATUS HELPER
    private String getStatus(int status) {
        return (status == 1) ? "PAID" : "PENDING";
    }
}
