package main.java.com.carcustomizer.ui;
import main.java.com.carcustomizer.services.Car;

import java.util.Scanner;

public class ConsoleUI {
    private final Scanner scanner;

    public ConsoleUI() {
        this.scanner = new Scanner(System.in);
    }

    public void displayMainMenu() {
        // Display main menu options
        System.out.println("=== Car Customizer ===");
        System.out.println("1. View Available Car Makes");
        System.out.println("2. Customize a Car");
        System.out.println("3. Exit");
    }

    // Method to capture and display finalized car details
    public void displayFinalizedDetails(String selectedMake, String selectedModel, String selectedBodyType, Car car) {
        System.out.println("Finalized Car Details:");
        System.out.println("Make: " + selectedMake);
        System.out.println("Model: " + selectedModel);
        System.out.println("Body Type: " + selectedBodyType);
        System.out.println("Color: " + car.getColor());
        System.out.println("Wheel Type: " + car.getWheelType());
        System.out.println("Interior: " + car.getInterior());
        System.out.println("Sound System: " + car.getSoundSystem());
        System.out.println("Total Customization Cost: $" + car.calculateCustomizationCost());
        System.out.println("Note: The base of the car is $50,000");

    }

    public int getUserChoice() {
        return scanner.nextInt();
    }

}
