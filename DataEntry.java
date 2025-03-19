import java.util.Scanner;

public class DataEntry {
    private static final Scanner scanner = new Scanner(System.in);

    // Get any string input
    public static String getString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    // Get a string with a max character limit (ensures not blank)
    public static String getStringWithLimit(String prompt, int maxLength) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (input.isBlank() || input.length() > maxLength) {
                System.out.println("Input must be max " + maxLength + " characters and not blank.");
            }
        } while (input.isBlank() || input.length() > maxLength);
        return input;
    }

    // Get a numeric string with an exact length (e.g., SSN, ZIP code)
    public static String getNumericString(String prompt, int length) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (!input.matches("\\d{" + length + "}")) {
                System.out.println("Input must be exactly " + length + " numeric characters.");
            }
        } while (!input.matches("\\d{" + length + "}"));
        return input;
    }

    // Get a valid double within a range
    public static double getDoubleInRange(String prompt, double min, double max) {
        double value;
        while (true) {
            try {
                System.out.print(prompt);
                value = Double.parseDouble(scanner.nextLine().trim());
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.println("Value must be between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
            }
        }
    }

    // Get a valid US state abbreviation (2 uppercase letters)
    public static String getState(String prompt) {
        String state;
        while (true) {
            System.out.print(prompt);
            state = scanner.nextLine().trim().toUpperCase();
            if (state.matches("^[A-Z]{2}$")) {
                return state;
            } else {
                System.out.println("Invalid state. Please enter a valid 2-letter state abbreviation (e.g., TX, CA, NY).");
            }
        }
    }
}
