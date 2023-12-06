package main.java.com.carcustomizer.models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class CarModel {
    private Map<String, List<String>> carModels;

    public CarModel() {
        carModels = new HashMap<>(); // Initialize a HashMap to store car makes and models
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

        // Skip the first token which contains the year
        tokenizer.nextToken();

        // Extract and clean each token (value) from the CSV line
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().replaceAll("\"", "").trim();
            values.add(token); // Add cleaned token to the list
        }

        String make = values.get(0); // Get the make (first value)
        values.remove(0); // Remove the make from the values list

        // Update carModels map with make as key and list of models as value
        if (carModels.containsKey(make)) {
            carModels.get(make).addAll(values); // Add models if make already exists
        } else {
            carModels.put(make, values); // Add a new make with its models to the map
        }
    }

    public void printCarModels() {
        // Print each make and its associated models
        for (Map.Entry<String, List<String>> entry : carModels.entrySet()) {
            System.out.println("Make: " + entry.getKey());
            System.out.println("Model, [BodyType]: " + entry.getValue());
            System.out.println();
        }
    }

    // The main method is commented out to be used separately
    // public static void main(String[] args) {
    //     CarModel carModels = new CarModel();
    //     carModels.readCSV("path/to/your/file.csv");
    //     carModels.printCarModels();
    // }
}