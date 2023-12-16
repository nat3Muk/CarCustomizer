package main.java.com.carcustomizer.ui;

import main.java.com.carcustomizer.models.CarModel;
import main.java.com.carcustomizer.services.CarFactory;
import main.java.com.carcustomizer.services.CustomizationOptions.Color;
import main.java.com.carcustomizer.services.CustomizationOptions.Interior;
import main.java.com.carcustomizer.services.CustomizationOptions.SoundSystem;
import main.java.com.carcustomizer.services.CustomizationOptions.WheelType;
import main.java.com.carcustomizer.services.Car;
import main.java.com.carcustomizer.services.BasicCostStrategy;
import main.java.com.carcustomizer.services.CarSaveObserver;

import java.util.List;
import java.util.Scanner;

public class CarCustomizerManager {
    private ConsoleUI ui;
    private InputHandler input;
    private Car car;
    private CarFactory carFactory;  // Added CarFactory

    private CarModel carModels;
    private Scanner scanner;

    private String selectedMake;
    private String selectedModel;
    private String selectedBodyType;


public CarCustomizerManager() {
    this.carFactory = new CarFactory();
    this.ui = new ConsoleUI();
    this.input = new InputHandler();
    this.car = new Car();
    this.carModels = new CarModel(); // Instantiate CarModel
    this.carModels.readCSV("main/java/com/carcustomizer/models/csvs/2020Models.csv"); // Read CSV here
    this.scanner = new Scanner(System.in); // Initialize the Scanner object
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
                    ui.displayFinalizedDetails(selectedMake, selectedModel, selectedBodyType, car);; // Display the finalized car details
                    System.out.println("Do you want to save this customized car? (y/n)");
                    String saveChoice = scanner.nextLine();
                    if (saveChoice.equalsIgnoreCase("y")) {
                        saveCustomizedCar(); // Save the customized car details
                    }
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

private void viewCarOptions() {
    carModels.printCarModelsByYear(2020);
    // Wait for user input to continue
    System.out.println("Press Enter to return to the main menu...");
    scanner.nextLine();
}



    private void customizeCar() {
        // Check if car make, model, and body type are selected
        if (selectedMake == null || selectedModel == null || selectedBodyType == null) {
            System.out.println("Please select a car make, model, and body type before customizing.");
            return; // Exit the method
        }

        WheelType wheelType = getWheelTypeChoice();
        Color color = getColorChoice();
        Interior interior = getInteriorChoice();
        SoundSystem soundSystem = getSoundSystemChoice();

        // Set the properties on the car
        car.setWheelType(wheelType);
        car.setColor(color);
        car.setInterior(interior);
        car.setSoundSystem(soundSystem);

        // Debug output
        System.out.println("Wheel Type: " + wheelType);
        System.out.println("Color: " + color);
        System.out.println("Interior: " + interior);
        System.out.println("Sound System: " + soundSystem);

        // Set cost strategy and calculate cost
        car.setCostStrategy(new BasicCostStrategy());

        //Output to user what they have made.
        System.out.println("You have made a: " + selectedMake + " " + selectedModel + " " + selectedBodyType + "\t(ﾉ◕ヮ◕)ﾉ*:・ﾟ✧");
        System.out.println("With Custom:");
        System.out.println("Color: " + car.getColor());
        System.out.println("Wheel Type: " + car.getWheelType());
        System.out.println("Interior: " + car.getInterior());
        System.out.println("Sound System: " + car.getSoundSystem());

        car.attach(new CarSaveObserver());  // Attach an observer

        // Notify observers when customization is finalized
        car.notifyAllObservers();
    }



    private void selectCarInCatalog() {
        System.out.println("========Select a Car Make==========");
        carModels.printCarMakes();
        this.selectedMake = scanner.nextLine();


        System.out.println("[Select a Model for " + this.selectedMake + "]" + ":");
        carModels.printCarModelsByMake(this.selectedMake);
        this.selectedModel = scanner.nextLine();

        carModels.printBodyTypesByModel(this.selectedMake, this.selectedModel);

        // Get available body types for the selected model
        List<String> availableBodyTypes = carModels.getBodyTypesByModel(this.selectedMake, this.selectedModel);

        if (availableBodyTypes.isEmpty()) {
            System.out.println("No body types available for the selected model.");
            return;
        }

        // Display available body types
        for (int i = 0; i < availableBodyTypes.size(); i++) {
            System.out.println((i + 1) + ". " + availableBodyTypes.get(i));
        }

        int bodyTypeChoice = input.getUserChoice(1, availableBodyTypes.size());
        this.selectedBodyType = availableBodyTypes.get(bodyTypeChoice - 1);
        System.out.println("Body Type selected: " + this.selectedBodyType);

        System.out.println("Do you want to customize this car? (y/n)");
        String customizeChoice = scanner.nextLine();
        if (customizeChoice.equalsIgnoreCase("y")) {
            customizeCar(); // First, customize the car

            // Display the finalized car details, including the cost
            ui.displayFinalizedDetails(selectedMake, selectedModel, selectedBodyType, car);

            // Ask the user if they want to save the customized car
            System.out.println("Do you want to save this customized car? (y/n)");
            String saveChoice = scanner.nextLine();
            if (saveChoice.equalsIgnoreCase("y")) {
                saveCustomizedCar(); // Save the customized car details
            }
        }
    }

    private WheelType getWheelTypeChoice() {
        System.out.println("Select Wheel Type:");
        WheelType[] wheelTypes = WheelType.values();
        for (int i = 0; i < wheelTypes.length; i++) {
            System.out.println((i + 1) + ". " + wheelTypes[i]);
        }
        int wheelChoice = input.getUserChoice(1, wheelTypes.length);
        return wheelTypes[wheelChoice - 1];
    }

    private Color getColorChoice() {
        System.out.println("Select Color:");
        Color[] colors = Color.values();
        for (int i = 0; i < colors.length; i++) {
            System.out.println((i + 1) + ". " + colors[i]);
        }
        int colorChoice = input.getUserChoice(1, colors.length);
        return colors[colorChoice - 1];
    }

    private Interior getInteriorChoice() {
        System.out.println("Select Interior:");
        Interior[] interiors = Interior.values();
        for (int i = 0; i < interiors.length; i++) {
            System.out.println((i + 1) + ". " + interiors[i]);
        }
        int interiorChoice = input.getUserChoice(1, interiors.length);
        return interiors[interiorChoice - 1];
    }

    private SoundSystem getSoundSystemChoice() {
        System.out.println("Select Sound System:");
        SoundSystem[] soundSystems = SoundSystem.values();
        for (int i = 0; i < soundSystems.length; i++) {
            System.out.println((i + 1) + ". " + soundSystems[i]);
        }
        int soundSystemChoice = input.getUserChoice(1, soundSystems.length);
        return soundSystems[soundSystemChoice - 1];
    }

    //Method to save the finalized car details
    //Change this to save to a List<> instead of a txt
    //Method to save the finalized car details
//Change this to save to a List<> instead of a txt
    private void saveCustomizedCar() {
        // Assuming you want to save to a file named "customized_car.txt"
        // You can modify this to suit your data storage needs
        try {
            String filename = "customized_car.txt";
            java.io.FileWriter fileWriter = new java.io.FileWriter(filename, true); // Use 'true' to append

            // Save the selected make, model, and body type
            fileWriter.write("Make: " + selectedMake + "\n");
            fileWriter.write("Model: " + selectedModel + "\n");
            fileWriter.write("Body Type: " + selectedBodyType + "\n");

            // Save the customization details
            fileWriter.write("Color: " + car.getColor() + "\n");
            fileWriter.write("Wheel Type: " + car.getWheelType() + "\n");
            fileWriter.write("Interior: " + car.getInterior() + "\n");
            fileWriter.write("Sound System: " + car.getSoundSystem() + "\n");

            // Save the total cost
            fileWriter.write("Total Cost: $" + car.calculateCustomizationCost() + "\n");

            // Add a separator (blank line) between each saved vehicle
            fileWriter.write("\n");

            fileWriter.close();
            System.out.println("Customized car details saved to " + filename);
        } catch (java.io.IOException e) {
            System.out.println("An error occurred while saving the customized car details.");
            e.printStackTrace();
        }
    }


    //Method for deleteing saved List<> of customized cars when user presses 3. Exit Program
}


