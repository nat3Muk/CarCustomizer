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
    // Declaring necessary variables
    private ConsoleUI ui; // User interface
    private InputHandler input; // Input handler
    private Car car; // Car instance
    private CarFactory carFactory; // Car factory instance

    private CarModel carModels; // Car models
    private Scanner scanner; // Scanner for user input

    private String selectedMake; // Selected car make
    private String selectedModel; // Selected car model
    private String selectedBodyType; // Selected car body type

    // Constructor initializing various components
    public CarCustomizerManager() {
        this.carFactory = new CarFactory(); // Instantiating CarFactory
        this.ui = new ConsoleUI(); // Instantiating ConsoleUI
        this.input = new InputHandler(); // Instantiating InputHandler
        this.car = new Car(); // Instantiating Car
        this.carModels = new CarModel(); // Instantiating CarModel
        this.carModels.readCSV("main/java/com/carcustomizer/models/csvs/2020Models.csv"); // Reading CSV for car models
        this.scanner = new Scanner(System.in); // Initializing Scanner object for user input
    }

    // Method to start the car customization process
    public void start() {
        boolean running = true;
        while (running) {
            ui.displayMainMenu(); // Displaying main menu
            int choice = input.getUserChoice(1, 3); // Getting user choice
            switch (choice) {
                case 1:
                    viewCarOptions(); // Viewing available car options
                    break;
                case 2:
                    selectCarInCatalog(); // Selecting a car from the catalog and customizing
                    ui.displayFinalizedDetails(selectedMake, selectedModel, selectedBodyType, car);
                    System.out.println("Do you want to save this customized car? (y/n)");
                    String saveChoice = scanner.nextLine();
                    if (saveChoice.equalsIgnoreCase("y")) {
                        saveCustomizedCar(); // Saving customized car details
                    }
                    break;
                case 3:
                    System.out.println("Exiting program..."); // Exiting the program
                    running = false; // Exiting the while loop to end the program
                    break;
                default:
                    System.out.println("Invalid choice."); // Handling invalid choices
                    break;
            }
        }
    }

    // Method to view available car options
    private void viewCarOptions() {
        carModels.printCarModelsByYear(2020); // Printing car models for the year 2020
        System.out.println("Press Enter to return to the main menu..."); // Prompting user to return to the main menu
        scanner.nextLine(); // Waiting for user input to continue
    }

    // Method to customize the selected car
    private void customizeCar() {
        // Check if car make, model, and body type are selected
        if (selectedMake == null || selectedModel == null || selectedBodyType == null) {
            System.out.println("Please select a car make, model, and body type before customizing.");
            return; // Exiting the method if selections are incomplete
        }

        // Getting user choices for wheel type, color, interior, and sound system
        WheelType wheelType = getWheelTypeChoice();
        Color color = getColorChoice();
        Interior interior = getInteriorChoice();
        SoundSystem soundSystem = getSoundSystemChoice();

        // Setting properties on the car based on user choices
        car.setWheelType(wheelType);
        car.setColor(color);
        car.setInterior(interior);
        car.setSoundSystem(soundSystem);

        // Debug output showing the chosen customizations
        System.out.println("Wheel Type: " + wheelType);
        System.out.println("Color: " + color);
        System.out.println("Interior: " + interior);
        System.out.println("Sound System: " + soundSystem);

        // Setting cost strategy and calculating cost for customization
        car.setCostStrategy(new BasicCostStrategy());

        // Outputting the details of the customized car to the user
        System.out.println("You have made a: " + selectedMake + " " + selectedModel + " " + selectedBodyType + "\t(ﾉ◕ヮ◕)ﾉ*:・ﾟ✧");
        System.out.println("With Custom:");
        System.out.println("Color: " + car.getColor());
        System.out.println("Wheel Type: " + car.getWheelType());
        System.out.println("Interior: " + car.getInterior());
        System.out.println("Sound System: " + car.getSoundSystem());

        car.attach(new CarSaveObserver()); // Attaching an observer for car customization

        // Notifying observers when customization is finalized
        car.notifyAllObservers();
    }

    // Method to select a car from the catalog
    private void selectCarInCatalog() {
        // Selecting car make
        System.out.println("========Select a Car Make==========");
        carModels.printCarMakes();
        this.selectedMake = scanner.nextLine();

        // Selecting car model
        System.out.println("[Select a Model for " + this.selectedMake + "]" + ":");
        carModels.printCarModelsByMake(this.selectedMake);
        this.selectedModel = scanner.nextLine();

        // Selecting available body types for the chosen car model
        carModels.printBodyTypesByModel(this.selectedMake, this.selectedModel);
        List<String> availableBodyTypes = carModels.getBodyTypesByModel(this.selectedMake, this.selectedModel);

        // Handling scenario where no body types are available for the selected model
        if (availableBodyTypes.isEmpty()) {
            System.out.println("No body types available for the selected model.");
            return;
        }

        // Displaying available body types for selection
        for (int i = 0; i < availableBodyTypes.size(); i++) {
            System.out.println((i + 1) + ". " + availableBodyTypes.get(i));
        }

        // Getting user choice for body type
        int bodyTypeChoice = input.getUserChoice(1, availableBodyTypes.size());
        this.selectedBodyType = availableBodyTypes.get(bodyTypeChoice - 1);
        System.out.println("Body Type selected: " + this.selectedBodyType);

        // Asking user if they want to customize the chosen car
        System.out.println("Do you want to customize this car? (y/n)");
        String customizeChoice = scanner.nextLine();
        if (customizeChoice.equalsIgnoreCase("y")) {
            customizeCar(); // Customizing the car

            // Displaying the finalized car details, including cost
            ui.displayFinalizedDetails(selectedMake, selectedModel, selectedBodyType, car);

            // Asking the user if they want to save the customized car
            System.out.println("Do you want to save this customized car? (y/n)");
            String saveChoice = scanner.nextLine();
            if (saveChoice.equalsIgnoreCase("y")) {
                saveCustomizedCar(); // Saving the customized car details
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



}


