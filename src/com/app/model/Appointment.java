package com.app.model;

import java.sql.Time;
import java.sql.Date;

public class Appointment {
    private int appointmentID;
    private int userID;
    private int petID;
    private int serviceID;
    private Date appointmentDate;
    private Time appointmentTime;
    private int isApprove;

    public Appointment(){}

    public int getAppointmentID(){return appointmentID;}
    public void setAppointmentID(int appointmentID){this.appointmentID = appointmentID;}

    public int getUserID(){return userID;}
    public void setUserID(int userID){this.userID = userID;}

    public int getPetID(){return petID;}
    public void setPetID(int petID) {this.petID = petID;}

    public int getServiceID() {return serviceID;}
    public void setServiceId(int serviceID) {this.serviceID = serviceID;}

    public Date getAppointmentDate() {return appointmentDate;}
    public void setAppointmentDate(Date appointmentDate) {this.appointmentDate = appointmentDate;}

    public Time getAppointmentTime() {return appointmentTime;}
    public void setAppointmentTime(Time appointmentTime) {this.appointmentTime = appointmentTime;}

    public int getIsApprove() {return isApprove;}
    public void setIsApprove(int isApprove) {this.isApprove = isApprove;}
}
