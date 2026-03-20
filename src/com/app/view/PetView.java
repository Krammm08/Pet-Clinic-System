
package com.app.view;

import java.util.List;

public class PetView {
    /*
    private static void petMenu(User user) {

        while (true) {
            System.out.println("\n=== PET MENU ===");
            System.out.println("1. Add Pet");
            System.out.println("2. View Pets");
            System.out.println("3. Delete Pet");
            System.out.println("4. Back");
            System.out.print("Choose: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addPet(user);
                    break;
                case 2:
                    viewPets(user);
                    break;
                case 3:
                    deletePet();
                    break;
                case 4:
                    return;
            }
        }
    }

    private static void addPet(User user) {
        Pet pet = new Pet();

        System.out.print("Pet Name: ");
        pet.setPetName(sc.nextLine());

        System.out.print("Animal Type: ");
        pet.setAnimalType(sc.nextLine());

        System.out.print("Breed: ");
        pet.setBreed(sc.nextLine());

        System.out.print("Age: ");
        pet.setAge(sc.nextInt());

        System.out.print("Gender: ");
        pet.setGender(sc.nextLine());

        System.out.print("Weight: ");
        pet.setWeight(sc.nextInt());
        sc.nextLine();

        pet.setUserId(user.getUserId());

        if (petService.addPet(pet)) {
            System.out.println("Pet Added!");
        } else {
            System.out.println("Failed!");
        }
    }

    private static void viewPets(User user) {
        List<Pet> pets = petService.getPetsByUser(user.getUserId());

        for (Pet p : pets) {
            System.out.println(p.getPetId() + " | " + p.getPetName() + " | " + p.getAnimalType());
        }
    }

    private static void deletePet() {
        System.out.print("Enter Pet ID: ");
        int id = sc.nextInt();

        if (petService.deletePet(id)) {
            System.out.println("Deleted!");
        } else {
            System.out.println("Failed!");
        }
    }

    */
}
