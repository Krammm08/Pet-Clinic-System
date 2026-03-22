package com.app.util;

import java.util.Scanner;

public class InputUtil {

    private static final Scanner SCAN = new Scanner(System.in);

    public static int getInt(String message) {
        int value;
        while (true) {
            try {
                System.out.print(message);
                value = Integer.parseInt(SCAN.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    public static double getDouble(String message) {
        double value;
        while (true) {
            try {
                System.out.print(message);
                value = Double.parseDouble(SCAN.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    public static String getString(String message) {
        System.out.print(message);
        return SCAN.nextLine().trim();
    }

    public static String getNonEmptyString(String message) {
        String input;
        while (true) {
            System.out.print(message);
            input = SCAN.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            } else {
                System.out.println("Input cannot be empty.");
            }
        }
    }

    public static String getChoice(String message, String... options) {
        String input;
        while (true) {
            System.out.print(message);
            input = SCAN.nextLine().trim();

            for (String option : options) {
                if (input.equalsIgnoreCase(option)) {
                    return input;
                }
            }

            System.out.print("Invalid choice. Options: ");
            for (String option : options) {
                System.out.print(option + " ");
            }
            System.out.println();
        }
    }
}
