package main.java.com.carcustomizer;

import main.java.com.carcustomizer.CustomizationOptions.Color;
import main.java.com.carcustomizer.CustomizationOptions.WheelType;
import main.java.com.carcustomizer.CustomizationOptions.Interior;
import main.java.com.carcustomizer.CustomizationOptions.SoundSystem;

public class Car {
    private Color color;
    private WheelType wheelType;
    private Interior interior;
    private SoundSystem soundSystem;

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
}
