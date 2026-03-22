package com.app.view;

import com.app.exception.DatabaseException;
import com.app.exception.ValidationException;
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

                case 1:
                    addPet(user);
                    break;

                case 2:
                    viewPets(user);
                    break;

                case 3:
                    updatePet();
                    break;

                case 4:
                    deletePet();
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // ADD PET
    private void addPet(User user) {
        try {
            Pet pet = new Pet();

            pet.setPetName(InputUtil.getNonEmptyString("Pet Name: "));
            pet.setAnimalType(InputUtil.getNonEmptyString("Animal Type: "));
            pet.setBreed(InputUtil.getNonEmptyString("Breed: "));
            pet.setAge(InputUtil.getInt("Age: "));
            pet.setGender(InputUtil.getNonEmptyString("Gender: "));
            pet.setWeightKg(InputUtil.getInt("Weight (kg): "));
            pet.setUserId(user.getUserId());

            boolean success = petService.addPet(pet);

            if (success) {
                System.out.println("Pet added successfully!");
            } else {
                System.out.println("Failed to add pet.");
            }

        } catch (ValidationException e) {
            System.out.println("X " + e.getMessage());
        } catch (DatabaseException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    // VIEW PETS
    private void viewPets(User user) {
        try {
            List<Pet> pets = petService.getPetsByUser(user.getUserId());

            System.out.println("\n===== MY PETS =====");

            if (pets.isEmpty()) {
                System.out.println("No pets found.");
                return;
            }

            for (Pet pet : pets) {
                System.out.println("ID: " + pet.getPetId());
                System.out.println("Name: " + pet.getPetName());
                System.out.println("Type: " + pet.getAnimalType());
                System.out.println("Breed: " + pet.getBreed());
                System.out.println("Age: " + pet.getAge());
                System.out.println("Gender: " + pet.getGender());
                System.out.println("Weight: " + pet.getWeightKg() + " kg");
                System.out.println("----------------------");
            }

        } catch (DatabaseException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    // UPDATE PET
    private void updatePet() {
        try {
            int petId = InputUtil.getInt("Enter Pet ID to update: ");
            Pet pet = petService.getPetById(petId);

            if (pet == null) {
                System.out.println("Pet not found.");
                return;
            }

            pet.setPetName(InputUtil.getNonEmptyString("New Name: "));
            pet.setAnimalType(InputUtil.getNonEmptyString("New Type: "));
            pet.setBreed(InputUtil.getNonEmptyString("New Breed: "));
            pet.setAge(InputUtil.getInt("New Age: "));
            pet.setGender(InputUtil.getNonEmptyString("New Gender: "));
            pet.setWeightKg(InputUtil.getInt("New Weight: "));

            boolean success = petService.updatePet(pet);

            if (success) {
                System.out.println("Pet updated successfully!");
            } else {
                System.out.println("Failed to update pet.");
            }

        } catch (ValidationException e) {
            System.out.println("X " + e.getMessage());
        } catch (DatabaseException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    // DELETE PET
    private void deletePet() {
        try {
            int petId = InputUtil.getInt("Enter Pet ID to delete: ");

            boolean success = petService.deletePet(petId);

            if (success) {
                System.out.println("Pet deleted successfully!");
            } else {
                System.out.println("Failed to delete pet.");
            }

        } catch (DatabaseException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
