package main.java.com.carcustomizer;

import main.java.com.carcustomizer.ui.ConsoleUI;
import main.java.com.carcustomizer.ui.InputHandler;
import main.java.com.carcustomizer.models.CarModel;
import main.java.com.carcustomizer.services.CustomizationOptions.Color;
import main.java.com.carcustomizer.services.CustomizationOptions.Interior;
import main.java.com.carcustomizer.services.CustomizationOptions.SoundSystem;
import main.java.com.carcustomizer.services.CustomizationOptions.WheelType;

public class CarCustomizerApp {
    public static void main(String[] args) {
        ConsoleUI ui = new ConsoleUI();
        InputHandler input = new InputHandler();
        Car car = new Car(); // Create a new car instance

        ui.displayMainMenu();
        int choice = input.getUserChoice(1, 5);

        switch (choice) {
            case 1:
                System.out.println("View Car Options");
                CarModel carModels = new CarModel();
                carModels.readCSV("../src/main/java/com/carcustomizer/models/csvs/2020Models.csv");
                carModels.printCarModels();
                break;
            case 2:
                // Customize a Car logic
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

                break;
            case 3:
                // Save Customization logic
                // ...
                break;
            case 4:
                // Load Customization logic
                // ...
                break;
            case 5:
                // Exiting the application
                System.exit(0);
                break;
            default:
                // Invalid choice handling
                System.out.println("Invalid choice.");
                break;
        }
    }
}

