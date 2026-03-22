package com.app.model;

public class Transaction {

    private int transactionId;
    private int procedureId;
    private int userId;
    private int serviceId;
    private int medicineId;
    private int quantity;
    private double totalAmount;
    private int isPaid;
    private String transactionDateTime;

    // Default Constructor
    public Transaction() {
    }

    // Full Constructor
    public Transaction(int transactionId, int procedureId, int userId, int serviceId,
                       int medicineId, int quantity, int totalAmount,
                       int isPaid, String transactionDateTime) {
        this.transactionId = transactionId;
        this.procedureId = procedureId;
        this.userId = userId;
        this.serviceId = serviceId;
        this.medicineId = medicineId;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.isPaid = isPaid;
        this.transactionDateTime = transactionDateTime;
    }

    // Getters and Setters

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(int procedureId) {
        this.procedureId = procedureId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(int isPaid) {
        this.isPaid = isPaid;
    }

    public String getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(String transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }

    // Utility Methods

    public String getPaymentStatus() {
        return isPaid == 1 ? "PAID" : "PENDING";
    }
}
