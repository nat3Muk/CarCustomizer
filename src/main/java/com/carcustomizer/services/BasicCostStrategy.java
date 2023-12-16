package main.java.com.carcustomizer.services;

public class BasicCostStrategy implements CustomizationCostStrategy {
    @Override
    public double calculateCost(Car car) {
        double cost = 50000; // Base cost

        // Example cost additions based on customization options
        // These values are placeholders and should be adjusted based on your application's requirements

        // Adding cost based on wheel type
        switch (car.getWheelType()) {
            case ALLOY:
                cost += 500; // Alloy wheels cost an additional $500
                break;
            case STEEL:
                cost += 300; // Steel wheels cost an additional $300
                break;
            case CHROME:
                cost += 700; // Chrome wheels cost an additional $700
                break;
        }

        // Adding cost based on color
        switch (car.getColor()) {
            case RED:
                cost += 300; //red would be 300
                break;
            case BLUE:
                cost += 200; // Red and blue colors cost an additional $200
                break;
            case BLACK:
                cost += 150; // Black color costs an additional $150
                break;
            case WHITE:
                cost += 100; // White color costs an additional $100
                break;
        }

        // Adding cost based on interior
        switch (car.getInterior()) {
            case LEATHER:
                cost += 1000; // Leather interior costs an additional $1000
                break;
            case FABRIC:
                cost += 500; // Fabric interior costs an additional $500
                break;
            case SUEDE:
                cost += 800; // Suede interior costs an additional $800
                break;
        }

        // Adding cost based on sound system
        switch (car.getSoundSystem()) {
            case STANDARD:
                cost += 300; // Standard sound system costs an additional $300
                break;
            case PREMIUM:
                cost += 500; // Premium sound system costs an additional $500
                break;
            case DELUXE:
                cost += 1000; // Deluxe sound system costs an additional $1000
                break;
        }

        return cost;
    }
}

