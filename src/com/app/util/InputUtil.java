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

    // NEW: Number reader that prevents crashes!
    public static int getInt(String prompt) {
        int input = -1;
        boolean valid = false;
        while (!valid) {
            System.out.print(prompt);
            try {
                input = Integer.parseInt(scanner.nextLine().trim());
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("\tX Invalid input. Please enter a valid number.");
            }
        }

        return input;
    }
    public static String getString(String prompt) {
        System.out.print(prompt);
        // If you are using a static Scanner named 'scanner', use it here.
        // The .trim() removes any accidental spaces the user types before or after.
        return scanner.nextLine().trim();
    }
}