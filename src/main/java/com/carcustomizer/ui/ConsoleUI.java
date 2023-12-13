package main.java.com.carcustomizer.ui;
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

    //might not need this...
//    public void displayCarModelsSubMenu() {
//        System.out.println("=== Available Car Makes ===");
//        System.out.println("1. Model 1: BMW");
//        System.out.println("2. Model 2: Audi");
//        // Display other available models similarly
//        System.out.println("0. Back to Main Menu");
//        System.out.print("Please select a car model (1-N or 0 to go back): ");
//    }

    public int getUserChoice() {
        return scanner.nextInt();
    }

    // Other methods to display sub-menus, messages, etc.
}
