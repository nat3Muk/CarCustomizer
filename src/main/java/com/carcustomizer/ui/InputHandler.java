package main.java.com.carcustomizer.ui;

import main.java.com.carcustomizer.models.CarModel;

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
    public String getValidCarModel(CarModel carModels, String selectedMake) {
        String selectedModel;
        while (true) {
            System.out.println("[Select a Model for " + selectedMake + "]" + ":");
            carModels.printCarModelsByMake(selectedMake);
            selectedModel = scannerInput.nextLine();
            if (carModels.isValidCarModel(selectedMake, selectedModel)) {
                break;
            } else {
                System.out.println("Invalid Car Model. Please select a valid Car Model for the chosen make.");
            }
        }
        return selectedModel;
    }

    public String getValidBodyType(CarModel carModels, String selectedMake, String selectedModel) {
        String selectedBodyType;
        while (true) {
            System.out.println("[Available Body Types for " + selectedModel + " under " + selectedMake + "]" + ":");
            carModels.printBodyTypesByModel(selectedMake, selectedModel);
            selectedBodyType = scannerInput.nextLine();
            if (carModels.isValidBodyType(selectedMake, selectedModel, selectedBodyType)) {
                break;
            } else {
                System.out.println("Invalid Body Type. Please select a valid Body Type for the chosen make and model.");
            }
        }
        return selectedBodyType;
    }

    public String getValidCarMake(CarModel carModels) {
        String selectedMake;
        while (true) {
            System.out.println("========Select a Car Make==========");
            carModels.printCarMakes();
            System.out.println("Please Pick a Model (Case Sensitive): ");
            selectedMake = scannerInput.nextLine();

            if (carModels.isValidCarMake(selectedMake)) {
                break; // Break the loop if the car make is valid
            } else {
                System.out.println("Invalid Car Make. Please select a valid Car Make.");
            }
        }
        return selectedMake;
    }
}

    // Other methods for input processing and validation

