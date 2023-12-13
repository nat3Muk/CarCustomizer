package main.java.com.carcustomizer.ui;

import main.java.com.carcustomizer.ui.ConsoleUI;
import main.java.com.carcustomizer.ui.InputHandler;
import main.java.com.carcustomizer.models.CarModel;
import main.java.com.carcustomizer.services.CustomizationOptions.Color;
import main.java.com.carcustomizer.services.CustomizationOptions.Interior;
import main.java.com.carcustomizer.services.CustomizationOptions.SoundSystem;
import main.java.com.carcustomizer.services.CustomizationOptions.WheelType;
import main.java.com.carcustomizer.Car;

import java.util.Scanner;

public class CarCustomizerManager {
    private ConsoleUI ui;
    private InputHandler input;
    private Car car;
    private CarModel carModels;

    public CarCustomizerManager() {
        this.ui = new ConsoleUI();
        this.input = new InputHandler();
        this.car = new Car();
        this.carModels = new CarModel(); // Instantiate CarModel
    }

    public void start() {
        boolean running = true;
        while (running) {
            ui.displayMainMenu();
            int choice = input.getUserChoice(1, 3);
            switch (choice) {
                case 1:
                    viewCarOptions();
                    break;
                case 2:
                    selectCarInCatalog();
                    break;
                // ... other cases for saving, loading, exiting
                case 3:
                    System.out.println("Exiting program...");
                    running = false; // This will exit the while loop and end the program
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }
// ... [Previous Class Definition Continues]


    private void viewCarOptions() {
        CarModel carModels = new CarModel();
        carModels.readCSV("main/java/com/carcustomizer/models/csvs/2020Models.csv");
        carModels.printCarModelsByYear(2020);
        // Wait for user input to continue
        System.out.println("Press Enter to return to the main menu...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
    //implement this stuff into CarCustomization options
    private void selectCarInCatalog() {
        CarModel carModels = new CarModel();
        carModels.readCSV("main/java/com/carcustomizer/models/csvs/2020Models.csv");

        Scanner scanner = new Scanner(System.in);

        // Select Car Make
        System.out.println("========Select a Car Make==========");
        carModels.printCarMakes();
        String selectedMake = scanner.nextLine();

        // Select Car Model for the chosen make
        System.out.println("[Select a Model for " + selectedMake + "]" + ":");
        carModels.printCarModelsByMake(selectedMake);
        String selectedModel = scanner.nextLine();

        // Display Body Types for the chosen make and model
        System.out.println("[Available Body Types for " + selectedModel + " under " + selectedMake +  "]" + ":");
        carModels.printBodyTypesByModel(selectedMake, selectedModel);

        System.out.println("Do you want to customize this car? (y/n)");
        String customizeChoice = scanner.nextLine();
        if (customizeChoice.equalsIgnoreCase("y")) {
            customizeCar();
        }
    }

    private void customizeCar() {
        System.out.println("BUILD YOUR DREAM CAR MODEL");

        // Customize Wheel Type
        System.out.println("Select Wheel Type:");
        WheelType[] wheelTypes = WheelType.values();
        for (int i = 0; i < wheelTypes.length; i++) {
            System.out.println((i + 1) + ". " + wheelTypes[i]);
        }
        int wheelChoice = input.getUserChoice(1, wheelTypes.length);
        car.setWheelType(wheelTypes[wheelChoice - 1]);
        System.out.println("Wheel Type selected: " + car.getWheelType());

        // Customize Color
        System.out.println("Select Color:");
        Color[] colors = Color.values();
        for (int i = 0; i < colors.length; i++) {
            System.out.println((i + 1) + ". " + colors[i]);
        }
        int colorChoice = input.getUserChoice(1, colors.length);
        car.setColor(colors[colorChoice - 1]);
        System.out.println("Color selected: " + car.getColor());

        // Customize Interior
        System.out.println("Select Interior:");
        Interior[] interiors = Interior.values();
        for (int i = 0; i < interiors.length; i++) {
            System.out.println((i + 1) + ". " + interiors[i]);
        }
        int interiorChoice = input.getUserChoice(1, interiors.length);
        car.setInterior(interiors[interiorChoice - 1]);
        System.out.println("Interior selected: " + car.getInterior());

        // Customize Sound System
        System.out.println("Select Sound System:");
        SoundSystem[] soundSystems = SoundSystem.values();
        for (int i = 0; i < soundSystems.length; i++) {
            System.out.println((i + 1) + ". " + soundSystems[i]);
        }
        int soundSystemChoice = input.getUserChoice(1, soundSystems.length);
        car.setSoundSystem(soundSystems[soundSystemChoice - 1]);
        System.out.println("Sound System selected: " + car.getSoundSystem());
    }

// ... Methods for other functionalities (save, load, etc year maike model .)
}


