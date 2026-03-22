package com.app.model;

public class Vet {

    private int vetId;
    private String vetName;
    private String lastName;
    private String firstName;
    private int age;
    private String gender;
    private String contactNumber;
    private String emailAddress;
    private String specialization;

    // Default Constructor
    public Vet() {
    }

    // Full Constructor
    public Vet(int vetId, String vetName, String lastName, String firstName,
               int age, String gender, String contactNumber,
               String emailAddress, String specialization) {
        this.vetId = vetId;
        this.vetName = vetName;
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.gender = gender;
        this.contactNumber = contactNumber;
        this.emailAddress = emailAddress;
        this.specialization = specialization;
    }

    // Getters and Setters

    public int getVetId() {
        return vetId;
    }

    public void setVetId(int vetId) {
        this.vetId = vetId;
    }

    public String getVetName() {
        return vetName;
    }

    public void setVetName(String vetName) {
        this.vetName = vetName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
