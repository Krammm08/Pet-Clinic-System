package com.app.model;

public class User {

    private int userId;
    private String username;
    private String password;
    private String lastName;
    private String firstName;
    private int age;
    private String gender;
    private String contactNumber;
    private String emailAddress;
    private String cityAddress;
    private int userLevel; // 0 = Admin, 1 = Customer

    // Default Constructor
    public User() {
    }

    // Full Constructor
    public User(int userId, String username, String password, String lastName,
                String firstName, int age, String gender, String contactNumber,
                String emailAddress, String cityAddress, int userLevel) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.gender = gender;
        this.contactNumber = contactNumber;
        this.emailAddress = emailAddress;
        this.cityAddress = cityAddress;
        this.userLevel = userLevel;
    }

    // Getters and Setters

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getCityAddress() {
        return cityAddress;
    }

    public void setCityAddress(String cityAddress) {
        this.cityAddress = cityAddress;
    }

    public int getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }

    // Utility Methods

    public boolean isAdmin() {
        return userLevel == 0;
    }

    public boolean isCustomer() {
        return userLevel == 1;
    }
}
