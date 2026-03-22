package com.app.model;

public class OfferedService {

    private int serviceId;
    private String serviceType;
    private double serviceFee;

    // Default Constructor
    public OfferedService() {
    }

    // Full Constructor
    public OfferedService(int serviceId, String serviceType, double serviceFee) {
        this.serviceId = serviceId;
        this.serviceType = serviceType;
        this.serviceFee = serviceFee;
    }

    // Getters and Setters

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public double getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(double serviceFee) {
        this.serviceFee = serviceFee;
    }
}
