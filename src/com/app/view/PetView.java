package com.app.view;

import com.app.model.Pet;
import com.app.model.User;
import com.app.service.PetService;
import com.app.service.impl.PetServiceImpl;
import com.app.util.Asciiart;
import com.app.util.InputUtil;

import java.util.List;

public class PetView {

    private final PetService petService = new PetServiceImpl();
    private final Asciiart art = new Asciiart();

    public void show(User user) {

        while (true) {
            System.out.println("\n\t============= PET MANAGEMENT =============");
            System.out.println("\t|\t1. Add Pet");
            System.out.println("\t|\t2. View My Pets");
            System.out.println("\t|\t3. Update Pet");
            System.out.println("\t|\t4. Delete Pet");
            System.out.println("\t|\t5. Back");
            System.out.println("\t==========================================");
            int choice = InputUtil.getInt("\tChoose option: ");
            System.out.println("\t==========================================\n");

            switch (choice) {
                case 1: addPet(user); break;
                case 2: viewPets(user); break;
                case 3: updatePet(); break;
                case 4: deletePet(); break;
                case 5: return;
                default: System.out.println("\tX Invalid choice.");
            }
        }
    }

    // ADD PET
    private void addPet(User user) {
        Pet pet = new Pet();

        pet.setPetName(InputUtil.getNonEmptyString("\tPet Name: "));
        pet.setAnimalType(InputUtil.getNonEmptyString("\tAnimal Type: "));
        pet.setBreed(InputUtil.getNonEmptyString("\tBreed: "));
        pet.setAge(InputUtil.getInt("\tAge: "));
        pet.setGender(InputUtil.getNonEmptyString("\tGender: "));
        pet.setWeightKg(InputUtil.getInt("\tWeight (kg): "));
        pet.setUserId(user.getUserId()); // Links pet to the logged-in user

        // Validation is handled inside the service!
        boolean success = petService.addPet(pet);

        if (success) {
            System.out.println("\t-> Pet added successfully!");
        } else {
            System.out.println("\tX Failed to add pet. Please check your inputs.");
        }
    }

    // VIEW PETS
    private void viewPets(User user) {
        // Fetch only the pets belonging to this specific user
        List<Pet> pets = petService.getPetsByUser(user.getUserId());

        System.out.println("\n===== MY PETS =====");

        if (pets.isEmpty()) {
            System.out.println("\tNo pets found. Add one from the menu!");
            return;
        }

        for (Pet pet : pets) {
            System.out.println("\tID: " + pet.getPetId());
            System.out.println("\tName: " + pet.getPetName());
            System.out.println("\tType: " + pet.getAnimalType());
            System.out.println("\tBreed: " + pet.getBreed());
            System.out.println("\tAge: " + pet.getAge());
            System.out.println("\tGender: " + pet.getGender());
            System.out.println("\tWeight: " + pet.getWeightKg() + " kg");
            System.out.println("\t----------------------");
        }
    }

    // UPDATE PET
    private void updatePet() {
        int petId = InputUtil.getInt("\tEnter Pet ID to update: ");

        // We need to fetch the existing pet first
        Pet pet = petService.getPetById(petId);

        if (pet == null) {
            System.out.println("\tX Pet not found.");
            return;
        }

        pet.setPetName(InputUtil.getNonEmptyString("\tNew Name: "));
        pet.setAnimalType(InputUtil.getNonEmptyString("\tNew Type: "));
        pet.setBreed(InputUtil.getNonEmptyString("\tNew Breed: "));
        pet.setAge(InputUtil.getInt("\tNew Age: "));
        pet.setGender(InputUtil.getNonEmptyString("\tNew Gender: "));
        pet.setWeightKg(InputUtil.getInt("\tNew Weight: "));

        boolean success = petService.updatePet(pet);

        if (success) {
            System.out.println("\t-> Pet updated successfully!");
        } else {
            System.out.println("\tX Failed to update pet.");
        }
    }

    // DELETE PET
    private void deletePet() {
        int petId = InputUtil.getInt("\tEnter Pet ID to delete: ");

        boolean success = petService.deletePet(petId);

        if (success) {
            System.out.println("\t-> Pet deleted successfully!");
        } else {
            System.out.println("\tX Failed to delete pet.");
        }
    }
}