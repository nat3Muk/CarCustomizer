package main.java.com.carcustomizer.ui;

import main.java.com.carcustomizer.models.CarModel;

import java.util.Scanner; // Import Scanner class

public class InputHandler {
    private final Scanner scannerInput; // Scanner object for user input

    public InputHandler() { // Constructor to initialize the scanner
        this.scannerInput = new Scanner(System.in);
    }

    // Method to get and validate user's choice within a specified range
    public int getUserChoice(int minChoice, int maxChoice) throws NumberFormatException {
        int choice;
        while (true) {
            try {
                // Prompt user to enter their choice within the specified range
                System.out.print("Please enter your choice (" + minChoice + "-" + maxChoice + "): ");
                choice = Integer.parseInt(scannerInput.nextLine()); // Get user input
                if (choice < minChoice || choice > maxChoice) {
                    throw new IllegalArgumentException(); // Throw exception for invalid input
                }
                break; // Break the loop if input is valid
            } catch (IllegalArgumentException e) {
                // Display message for invalid input and prompt again
                System.out.println("Invalid input! Please enter a number between " + minChoice + " and " + maxChoice + ".");
            }
        }
        return choice; // Return the validated user choice
    }

    // Method to get a valid car model for the selected car make
    public String getValidCarModel(CarModel carModels, String selectedMake) {
        String selectedModel;
        while (true) {
            // Prompt user to select a model for the chosen car make
            System.out.println("[Select a Model for " + selectedMake + "]" + ":");
            carModels.printCarModelsByMake(selectedMake); // Print available models for the make
            selectedModel = scannerInput.nextLine(); // Get user input for the selected model
            if (carModels.isValidCarModel(selectedMake, selectedModel)) {
                break; // Break the loop if the selected model is valid for the chosen make
            } else {
                // Display message for invalid car model selection
                System.out.println("Invalid Car Model. Please select a valid Car Model for the chosen make.");
            }
        }
        return selectedModel; // Return the validated selected car model
    }

    // Method to get a valid body type for the selected car make and model
    public String getValidBodyType(CarModel carModels, String selectedMake, String selectedModel) {
        String selectedBodyType;
        while (true) {
            // Prompt user to select a body type for the chosen car make and model
            System.out.println("[Available Body Types for " + selectedModel + " under " + selectedMake + "]" + ":");
            carModels.printBodyTypesByModel(selectedMake, selectedModel); // Print available body types for the model
            selectedBodyType = scannerInput.nextLine(); // Get user input for the selected body type
            if (carModels.isValidBodyType(selectedMake, selectedModel, selectedBodyType)) {
                break; // Break the loop if the selected body type is valid for the chosen make and model
            } else {
                // Display message for invalid body type selection
                System.out.println("Invalid Body Type. Please select a valid Body Type for the chosen make and model.");
            }
        }
        return selectedBodyType; // Return the validated selected body type
    }

    // Method to get a valid car make from the available car makes
    public String getValidCarMake(CarModel carModels) {
        String selectedMake;
        while (true) {
            // Prompt user to select a car make from available options
            System.out.println("========Select a Car Make==========");
            carModels.printCarMakes(); // Print available car makes
            System.out.println("Please Pick a Model (Case Sensitive): ");
            selectedMake = scannerInput.nextLine(); // Get user input for the selected car make

            if (carModels.isValidCarMake(selectedMake)) {
                break; // Break the loop if the selected car make is valid
            } else {
                // Display message for invalid car make selection
                System.out.println("Invalid Car Make. Please select a valid Car Make.");
            }
        }
        return selectedMake; // Return the validated selected car make
    }
}
