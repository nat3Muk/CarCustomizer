package main.java.com.carcustomizer.models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

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
        carData
                .computeIfAbsent(year, k -> new HashMap<>())
                .computeIfAbsent(make, k -> new HashMap<>())
                .computeIfAbsent(model, k -> new ArrayList<>())
                .add(bodyType);
    }

    public void printCarMakes() {
        System.out.println("Available Car Makes:");
        for (Map.Entry<Integer, Map<String, Map<String, List<String>>>> entry : carData.entrySet()) {
            Map<String, Map<String, List<String>>> yearData = entry.getValue();
            for (String make : yearData.keySet()) {
                System.out.println(make);
            }
        }
    }

    public void printCarModelsByMake(String selectedMake) {
        System.out.println("Available Models for " + selectedMake + ":");
        for (Map.Entry<Integer, Map<String, Map<String, List<String>>>> entry : carData.entrySet()) {
            Map<String, Map<String, List<String>>> yearData = entry.getValue();
            Map<String, List<String>> makeData = yearData.get(selectedMake);
            if (makeData != null) {
                for (String model : makeData.keySet()) {
                    System.out.println(model);
                }
            }
        }
    }

    public void printBodyTypesByModel(String selectedMake, String selectedModel) {
        System.out.println("Available Body Types for " + selectedModel + " under " + selectedMake + ":");
        for (Map.Entry<Integer, Map<String, Map<String, List<String>>>> entry : carData.entrySet()) {
            Map<String, Map<String, List<String>>> yearData = entry.getValue();
            Map<String, List<String>> makeData = yearData.get(selectedMake);
            if (makeData != null) {
                List<String> bodyTypes = makeData.get(selectedModel);
                if (bodyTypes != null) {
                    for (String bodyType : bodyTypes) {
                        System.out.println(bodyType);
                    }
                }
            }
        }
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
}