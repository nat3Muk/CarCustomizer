package main.java.com.carcustomizer.services;

import java.io.FileWriter;
import java.io.IOException;

// CustomizationObserver implementation that saves customized car details to a file
public class CarSaveObserver implements CustomizationObserver {

    @Override
    // Updates the observer with the latest car customization details
    public void update(Car car) {
        try {
            // FileWriter is used to write to the file
            FileWriter fileWriter = new FileWriter("customized_car.txt", true); // Append mode

            // Writing car details to the file
            fileWriter.write("Car Make: " + car.getMake() + "\n"); // You need to have getter methods for these properties
            fileWriter.write("Car Model: " + car.getModel() + "\n");
            fileWriter.write("Color: " + car.getColor() + "\n");
            fileWriter.write("Wheel Type: " + car.getWheelType() + "\n");
            fileWriter.write("Interior: " + car.getInterior() + "\n");
            fileWriter.write("Sound System: " + car.getSoundSystem() + "\n");

            fileWriter.close();
            //checks for errors
        } catch (IOException e) {
            System.out.println("An error occurred while saving the customized car details.");
            e.printStackTrace();
        }
    }
}
