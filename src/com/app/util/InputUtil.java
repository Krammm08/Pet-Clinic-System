package com.app.util;

import java.util.Scanner;

public class InputUtil {
    private static final Scanner scanner = new Scanner(System.in);

    public static String getNonEmptyString(String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("\tX Input cannot be empty. Please try again.");
            }
        } while (input.isEmpty());
        return input;
    }

    // Replace your current getInt method with this one:
    public static int getInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                // We read the whole line as a string, then try to convert it to a number
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                // If they typed letters, it fails gracefully and asks again!
                System.out.println("\t[!] Invalid input. Please enter a valid number.");
            }
        }
    }

    // You can do the exact same thing for getDouble!
    public static double getDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("\t[!] Invalid input. Please enter a valid decimal number.");
            }
        }
    }

    public static String getString(String prompt) {
        System.out.print(prompt);
        // If you are using a static Scanner named 'scanner', use it here.
        // The .trim() removes any accidental spaces the user types before or after.
        return scanner.nextLine().trim();
    }
}