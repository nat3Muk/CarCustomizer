package main.java.com.carcustomizer.models;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.StringTokenizer;
//
//public class CarModel {
//    private Map<String, List<String>> carModels;
//
//    public CarModel() {
//        carModels = new HashMap<>(); // Initialize a HashMap to store car makes and models
//    }
//
//    public void readCSV(String filePath) {
//        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
//            String line;
//            boolean firstLine = true;
//
//            while ((line = br.readLine()) != null) {
//                if (firstLine) {
//                    firstLine = false;
//                    continue; // Skip the header line
//                }
//                parseCSVLine(line); // Parse each line of the CSV file
//            }
//        } catch (IOException e) {
//            e.printStackTrace(); // Handle file IO exceptions
//        }
//    }
//
//    private void parseCSVLine(String line) {
//        StringTokenizer tokenizer = new StringTokenizer(line, ",");
//        List<String> values = new ArrayList<>(); // Create a list to hold values
//
//        // Skip the first token which contains the year
//        tokenizer.nextToken();
//
//        // Extract and clean each token (value) from the CSV line
//        while (tokenizer.hasMoreTokens()) {
//            String token = tokenizer.nextToken().replaceAll("\"", "").trim();
//            values.add(token); // Add cleaned token to the list
//        }
//
//        String make = values.get(0); // Get the make (first value)
//        values.remove(0); // Remove the make from the values list
//
//        // Update carModels map with make as key and list of models as value
//        if (carModels.containsKey(make)) {
//            carModels.get(make).addAll(values); // Add models if make already exists
//        } else {
//            carModels.put(make, values); // Add a new make with its models to the map
//        }
//    }
//
//    public void printCarModels() {
//        // Print each make and its associated models
//        for (Map.Entry<String, List<String>> entry : carModels.entrySet()) {
//            System.out.println("Make: " + entry.getKey());
//            System.out.println("Model, [BodyType]: " + entry.getValue());
//            System.out.println();
//        }
//    }
//
//    // The main method is commented out to be used separately
//    // public static void main(String[] args) {
//    //     CarModel carModels = new CarModel();
//    //     carModels.readCSV("path/to/your/file.csv");
//    //     carModels.printCarModels();
//    // }
//}

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class CarModel {
    private Map<Integer, Map<String, Map<String, List<String>>>> carData; // Store car data by year, make, and body type

    public CarModel() {
        carData = new HashMap<>(); // Initialize a HashMap to store car data by year
    }

    public void readCSV(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; // Skip the header line
                }
                parseCSVLine(line); // Parse each line of the CSV file
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle file IO exceptions
        }
    }

    private void parseCSVLine(String line) {
        StringTokenizer tokenizer = new StringTokenizer(line, ",");
        List<String> values = new ArrayList<>(); // Create a list to hold values

        // Extract and clean each token (value) from the CSV line
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().replaceAll("\"", "").trim();
            values.add(token); // Add cleaned token to the list
        }

        int year = Integer.parseInt(values.get(0)); // Get the year (first value)
        String make = values.get(1); // Get the make (second value)
        String model = values.get(2); // Get the model (third value)
        String bodyType = values.get(values.size() - 1); // Get the body type (last value)

        // Add data to the carData map
        if (!carData.containsKey(year)) {
            carData.put(year, new HashMap<>()); // Initialize a new map for the year if it doesn't exist
        }

        Map<String, Map<String, List<String>>> yearData = carData.get(year);

        if (!yearData.containsKey(make)) {
            yearData.put(make, new HashMap<>()); // Initialize a new map for the make if it doesn't exist
        }

        Map<String, List<String>> makeData = yearData.get(make);

        if (!makeData.containsKey(model)) {
            makeData.put(model, new ArrayList<>()); // Initialize a new list for the model if it doesn't exist
        }

        List<String> models = makeData.get(model);
        models.add(bodyType); // Add the body type to the list
    }

    public void printCarModelsByYear(int year) {
        if (carData.containsKey(year)) {
            Map<String, Map<String, List<String>>> yearData = carData.get(year);

            for (Map.Entry<String, Map<String, List<String>>> makeEntry : yearData.entrySet()) {
                String make = makeEntry.getKey();
                Map<String, List<String>> makeData = makeEntry.getValue();

                for (Map.Entry<String, List<String>> modelEntry : makeData.entrySet()) {
                    String model = modelEntry.getKey();
                    List<String> bodyTypes = modelEntry.getValue();

                    System.out.println("Year: " + year);
                    System.out.println("Make: " + make);
                    System.out.println("Model: " + model);
                    System.out.println("Body Types: " + bodyTypes);
                    System.out.println();
                }
            }
        } else {
            System.out.println("No data available for the year " + year);
        }
    }

//    public static void main(String[] args) {
//        CarModel carModels = new CarModel();
//        carModels.readCSV("main/java/com/carcustomizer/models/csvs/2020Models.csv");
//        carModels.printCarModelsByYear(2020);
//    }
}
