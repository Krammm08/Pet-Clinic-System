package com.app.model;

public class Vet {
    private int vetID;
    private String vetName;
    private String lastName;
    private String firstName;
    private int age;
    private String gender;
    private String contactNumber;
    private String email;
    private String specialization;

    public Vet(){}
    
    public int getVetID(){return vetID;}
    public void setVetID(int vetID){this.vetID = vetID; }
    
    public String getVetName(){return vetName;}
    public void setVetName(String vetName){this.vetName = vetName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSpecialization(){return specialization;}
    public void setSpecialization(String specialization){this.specialization = specialization;}
}
