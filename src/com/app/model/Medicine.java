package com.app.model;

public class Medicine {

    private int medicineId;
    private String medName;
    private int cost;
    private int inventoryCount;

    // Default Constructor
    public Medicine() {
    }

    // Full Constructor
    public Medicine(int medicineId, String medName, int cost, int inventoryCount) {
        this.medicineId = medicineId;
        this.medName = medName;
        this.cost = cost;
        this.inventoryCount = inventoryCount;
    }

    // Getters and Setters

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getInventoryCount() {
        return inventoryCount;
    }

    public void setInventoryCount(int inventoryCount) {
        this.inventoryCount = inventoryCount;
    }
}
