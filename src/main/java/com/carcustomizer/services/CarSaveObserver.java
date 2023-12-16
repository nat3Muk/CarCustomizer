package main.java.com.carcustomizer.services;

import java.io.FileWriter;
import java.io.IOException;

public class CarSaveObserver implements CustomizationObserver {
    @Override
    public void update(Car car) {
        try {
            FileWriter fileWriter = new FileWriter("customized_car.txt", true); // Append mode
            fileWriter.write("Car Make: " + car.getMake() + "\n"); // You need to have getter methods for these properties
            fileWriter.write("Car Model: " + car.getModel() + "\n");
            fileWriter.write("Color: " + car.getColor() + "\n");
            fileWriter.write("Wheel Type: " + car.getWheelType() + "\n");
            fileWriter.write("Interior: " + car.getInterior() + "\n");
            fileWriter.write("Sound System: " + car.getSoundSystem() + "\n");
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving the customized car details.");
            e.printStackTrace();
        }
    }
}
