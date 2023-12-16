package main.java.com.carcustomizer.services;

import main.java.com.carcustomizer.services.CustomizationOptions.Color;
import main.java.com.carcustomizer.services.CustomizationOptions.Interior;
import main.java.com.carcustomizer.services.CustomizationOptions.SoundSystem;
import main.java.com.carcustomizer.services.CustomizationOptions.WheelType;
import main.java.com.carcustomizer.models.CarModel;
import java.util.ArrayList;
import java.util.List;

public class Car {
    // Car properties
    private String make;
    private String model;
    private Color color;
    private WheelType wheelType;
    private Interior interior;
    private SoundSystem soundSystem;
    private String bodyType;
    private CustomizationCostStrategy costStrategy;

    // Observers to notify when customization changes
    // Used this website to learn more about observers: https://www.tutorialspoint.com/design_pattern/observer_pattern.htm 
    private List<CustomizationObserver> observers = new ArrayList<>();

    // Attaches an observer to the list
    public void attach(CustomizationObserver observer) {
        observers.add(observer);
    }

    // Notifies all attached observers when customization changes
    public void notifyAllObservers() {
        for (CustomizationObserver observer : observers) {
            observer.update(this);
        }
    }

    // Sets the customization cost strategy
    public void setCostStrategy(CustomizationCostStrategy costStrategy) {
        this.costStrategy = costStrategy;
    }

    // Constructors
    public Car() {
        // Default cost strategy is BasicCostStrategy
        this.costStrategy = new BasicCostStrategy();
    }

    // Getter for the make property
    public String getMake() {
        return make;
    }

    // Setter for the model property
    public void setModel(String model) {
        this.model = model;
    }

    // Getter for the model property
    public String getModel() {
        return model;
    }

    // Setter for the make property
    public void setMake(String make) {
        this.make = make;
    }

    // Setter and getter for the color property
    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    // Setter and getter for the wheelType property
    public void setWheelType(WheelType wheelType) {
        this.wheelType = wheelType;
    }

    public WheelType getWheelType() {
        return wheelType;
    }

    // Setter and getter for the interior property
    public void setInterior(Interior interior) {
        this.interior = interior;
    }

    public Interior getInterior() {
        return interior;
    }

    // Setter and getter for the soundSystem property
    public void setSoundSystem(SoundSystem soundSystem) {
        this.soundSystem = soundSystem;
    }

    public SoundSystem getSoundSystem() {
        return soundSystem;
    }

    // Calculates the customization cost using the set strategy
    public double calculateCustomizationCost() {
        return costStrategy.calculateCost(this);
    }

    // Sets the body type if it's valid for the selected make and model
    public void setBodyType(String selectedMake, String selectedModel, String bodyType, CarModel car) {
        if (car.isValidBodyType(selectedMake, selectedModel, bodyType)) {
            this.bodyType = bodyType;
            System.out.println("Body type set to: " + bodyType);
        } else {
            System.out.println("Invalid body type for the selected make and model.");
        }
    }

    // Getter for the bodyType property
    public String getBodyType() {
        return bodyType;
    }
}
