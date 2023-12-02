package main.java.com.carcustomizer;

public class CustomizationOptions {

    public enum Color {
        RED, BLUE, BLACK, WHITE;

        public static Color fromString(String colorStr) {
            for (Color color : Color.values()) {
                if (color.toString().equalsIgnoreCase(colorStr)) {
                    return color;
                }
            }
            return null; // Or throw an exception
        }

        @Override
        public String toString() {
            return name().charAt(0) + name().substring(1).toLowerCase();
        }
    }

    public enum WheelType {
        ALLOY, STEEL, CHROME;

        public static WheelType fromString(String wheelTypeStr) {
            for (WheelType wheelType : WheelType.values()) {
                if (wheelType.toString().equalsIgnoreCase(wheelTypeStr)) {
                    return wheelType;
                }
            }
            return null; // Or throw an exception
        }

        @Override
        public String toString() {
            return name().charAt(0) + name().substring(1).toLowerCase();
        }
    }

    public enum Interior {
        LEATHER, FABRIC, SUEDE;

        public static Interior fromString(String interiorStr) {
            for (Interior interior : Interior.values()) {
                if (interior.toString().equalsIgnoreCase(interiorStr)) {
                    return interior;
                }
            }
            return null; // Or throw an exception
        }

        @Override
        public String toString() {
            return name().charAt(0) + name().substring(1).toLowerCase();
        }
    }

    public enum SoundSystem {
        STANDARD, PREMIUM, DELUXE;

        public static SoundSystem fromString(String soundSystemStr) {
            for (SoundSystem soundSystem : SoundSystem.values()) {
                if (soundSystem.toString().equalsIgnoreCase(soundSystemStr)) {
                    return soundSystem;
                }
            }
            return null; // Or throw an exception
        }

        @Override
        public String toString() {
            return name().charAt(0) + name().substring(1).toLowerCase();
        }
    }

}

