package main.java.com.carcustomizer.services;

import main.java.com.carcustomizer.services.CustomizationOptions.Color;
import main.java.com.carcustomizer.services.CustomizationOptions.Interior;
import main.java.com.carcustomizer.services.CustomizationOptions.SoundSystem;
import main.java.com.carcustomizer.services.CustomizationOptions.WheelType;
import main.java.com.carcustomizer.models.CarModel;

public class Car {
    private Color color;
    private WheelType wheelType;
    private Interior interior;
    private SoundSystem soundSystem;
    private String bodyType;

    // Constructors
    public Car() {
    }

    // Setters and getters for each property
    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setWheelType(WheelType wheelType) {
        this.wheelType = wheelType;
    }

    public WheelType getWheelType() {
        return wheelType;
    }

    public void setInterior(Interior interior) {
        this.interior = interior;
    }

    public Interior getInterior() {
        return interior;
    }

    public void setSoundSystem(SoundSystem soundSystem) {
        this.soundSystem = soundSystem;
    }

    public SoundSystem getSoundSystem() {
        return soundSystem;
    }

    public void setBodyType(String selectedMake, String selectedModel, String bodyType, CarModel car) {
        //CarModel carModel = new CarModel();
        if (car.isValidBodyType(selectedMake, selectedModel, bodyType)) {
            this.bodyType = bodyType;
            System.out.println("Body type set to: " + bodyType);
        } else {
            System.out.println("Invalid body type for the selected make and model.");
        }
    }

    public String getBodyType() {
        return bodyType;
    }
}
