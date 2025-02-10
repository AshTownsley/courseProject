package courseProject;

import java.util.Scanner;

public class DataEntry {
    private static final Scanner scanner = new Scanner(System.in);

    public static String getString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static String getStringWithLimit(String prompt, int maxLength) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine();
            if (input.length() > maxLength || input.isBlank()) {
                System.out.println("Input must be max " + maxLength + " characters and not blank.");
            }
        } while (input.length() > maxLength || input.isBlank());
        return input;
    }

    public static String getNumericString(String prompt, int length) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine();
            if (!input.matches("\\d{" + length + "}")) {
                System.out.println("Input must be exactly " + length + " numeric characters.");
            }
        } while (!input.matches("\\d{" + length + "}"));
        return input;
    }
}
