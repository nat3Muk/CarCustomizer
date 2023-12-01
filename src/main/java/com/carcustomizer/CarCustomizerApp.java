package main.java.com.carcustomizer;

import main.java.com.carcustomizer.ui.ConsoleUI;
import main.java.com.carcustomizer.ui.InputHandler;
import main.java.com.carcustomizer.models.*;
public class CarCustomizerApp {
    public static void main(String[] args) {
        ConsoleUI ui = new ConsoleUI();
        InputHandler input = new InputHandler();

        ui.displayMainMenu();
        int choice = input.getUserChoice(1, 3);

        // Handle user choice
        switch (choice) {
            case 1:
                // View Available Car Models logic
                System.out.println("Car Models Availbe:");
                // ui.displayCarModelsSubMenu();
                // int choiceCarModels = input.getUserChoice(1,5);
                // switch (choiceCarModels) {
                //     case 1:
                //         //list of car modles in make choosen 
                //         System.out.println("BMW 3 Series");
                //         System.out.println("BMW 5 Series");
                //         System.out.println("BMW  Series");
                //         break;
                //     case 5:
                //         break;
                //     default:
                // }
                // break;
            case 2:
                // Customize a Car logic
                System.out.println("BUILD YOUR DREAM MODEL CAR");
                CarModel carModels = new CarModel();
                carModels.readCSV("/Users/nathanmukooba/Documents/cu_boulder20-24/Fall_2023_CUBoulder/CSCi4448-OOP/Project6n7/final-project-nat3Muk/src/main/java/com/carcustomizer/models/csvs/2020Models.csv");
                carModels.printCarModels();
                break;
            case 3:
                System.out.println("Exiting the application. Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
}
