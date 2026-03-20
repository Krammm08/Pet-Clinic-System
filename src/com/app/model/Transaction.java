package com.app.model;

import java.sql.Timestamp;

public class Transaction {
    private int transactionID;
    private int processID;
    private int userID;
    private int serviceID;
    private int medicineID;
    private int quantity;
    private int totalAmount;
    private int isPaid;
    private Timestamp transactionDateTime;

    public int getTransactionID(){return transactionID;}
    public void setTransactionID(int transactionID){this.transactionID = transactionID;}

    public int getProcessID(){return processID;}
    public void setProcessID(int processID){this.processID = processID;}

    public int getUserID(){return userID;}
    public void setUserID(int userID){this.userID = userID;}

    public int getServiceID(){return serviceID;}
    public void setServiceID(int serviceID){this.serviceID = serviceID;}

    public int getMedicineID(){return medicineID;}
    public void setMedicineID(int medicineID){this.medicineID = medicineID;}

    public int getQuantity(){return quantity;}
    public void setQuantity(int quantity){this.quantity = quantity;}

    public int getTotalAmount(){return totalAmount;}
    public void setTotalAmount(int totalAmount){this.totalAmount = totalAmount;}

    public int getIsPaid(){return isPaid;}
    public void setIsPaid(int isPaid){this.isPaid = isPaid;}

    public Timestamp getTransactionDateTime(){return transactionDateTime;}
    public void setTransactionDateTime(Timestamp transactionDateTime){this.transactionDateTime = transactionDateTime;}
}
