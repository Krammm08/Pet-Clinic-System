
package com.app.model;

public class Pet {
    
    private int petId;
    private String petName;
    private String animalType;
    private String breed;
    private int age;
    private String gender;
    private int weight;
    private int userId;
    
    // CONSTRUCTOR
    public void Pet(){
        
    }
    
    public void Pet(int petId, String petName, String animalType, String breed, int age, String gender, int weight, int userId){
        this.petId = petId;
        this.petName = petName;
        this.animalType = animalType;
        this.breed = breed;
        this.age = age;
        this.gender = gender;
        this.weight = weight;
        this.userId = userId;
    }
    
    // GETTERS & SETTERS

    public int getPetId(){ 
        return petId; 
    }
    
    public void setPetId(int petId) {
        this.petId = petId; 
    }

    public String getPetName() {
        return petName; 
    }
    
    public void setPetName(String petName) {
        this.petName = petName; 
    }

    public String getAnimalType() {
        return animalType; 
    }
    
    public void setAnimalType(String animalType) {
        this.animalType = animalType; 
    }

    public String getBreed() {
        return breed; 
    }
    
    public void setBreed(String breed) {
        this.breed = breed; 
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

    public int getWeight() {
        return weight; 
    }
    
    public void setWeight(int weight) {
        this.weight = weight; 
    }

    public int getUserId() {
        return userId; 
    }
    
    public void setUserId(int userId) {
        this.userId = userId; 
    }
}
