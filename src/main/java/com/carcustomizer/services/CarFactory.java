package main.java.com.carcustomizer.services;

// CarFactory class: Implements the Factory Design Pattern for creating Car objects.
public class CarFactory {

    // createCar method: Constructs a Car object with specified customization options.
    // This method encapsulates the object creation process, providing flexibility and maintainability.
    public Car createCar(CustomizationOptions.Color color,
                         CustomizationOptions.WheelType wheelType,
                         CustomizationOptions.Interior interior,
                         CustomizationOptions.SoundSystem soundSystem) {

        // Instantiates a new Car object.
        Car car = new Car();

        // Sets the color of the Car. The color is passed as a parameter to this method.
        car.setColor(color);

        // Sets the wheel type of the Car. The wheel type is passed as a parameter.
        car.setWheelType(wheelType);

        // Sets the interior type of the Car. The interior type is passed as a parameter.
        car.setInterior(interior);

        // Sets the sound system of the Car. The sound system choice is passed as a parameter.
        car.setSoundSystem(soundSystem);

        // Returns the fully customized Car object.
        // This is the Car object that the rest of the application will interact with.
        return car;
    }
}
