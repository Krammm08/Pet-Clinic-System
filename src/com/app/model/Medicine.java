package com.app.model;

public class Medicine {
    private int medicineID;
    private String medName;
    private int cost;
    private int inventoryCount;

    public Medicine(){}

    public int getMedicineID(){return medicineID;}
    public void setMedicineID(int medicineID){this.medicineID = medicineID;}

    public String getMedName(){return medName;}
    public void setMedName(String medName){this.medName = medName;}

    public int getCost(){return cost;}
    public void setCost(int cost){this.cost = cost;}

    public int getInventoryCount(){return inventoryCount;}
    public void setInventoryCount(int inventoryCount){this.inventoryCount = inventoryCount;}
}
