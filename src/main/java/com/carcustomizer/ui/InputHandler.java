package main.java.com.carcustomizer.ui;

import java.util.Scanner; // Import Scanner class

public class InputHandler {
    private final Scanner scannerInput;

    public InputHandler() { //constructor
        this.scannerInput = new Scanner(System.in);
    }

    public int getUserChoice(int minChoice, int maxChoice) throws NumberFormatException {
        int choice;
        while (true) {
            try {
                System.out.print("Please enter your choice (" + minChoice + "-" + maxChoice + "): ");
                choice = Integer.parseInt(scannerInput.nextLine());
                if (choice < minChoice || choice > maxChoice) {
                    throw new IllegalArgumentException();
                }
                break; // Break the loop if input is valid
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input! Please enter a number between " + minChoice + " and " + maxChoice + ".");
            }
        }
        return choice;
    }

    // Other methods for input processing and validation
}
