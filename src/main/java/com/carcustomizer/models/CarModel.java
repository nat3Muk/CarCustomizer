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
        carModels = new HashMap<>();
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
                parseCSVLine(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseCSVLine(String line) {
        StringTokenizer tokenizer = new StringTokenizer(line, ",");
        List<String> values = new ArrayList<>();

        // Skip the first token which contains the year
        tokenizer.nextToken();

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().replaceAll("\"", "").trim();
            values.add(token);
        }

        String make = values.get(0);
        values.remove(0);

        if (carModels.containsKey(make)) {
            carModels.get(make).addAll(values);
        } else {
            carModels.put(make, values);
        }
    }

    public void printCarModels() {
        for (Map.Entry<String, List<String>> entry : carModels.entrySet()) {
            System.out.println("Make: " + entry.getKey());
            System.out.println("Models: " + entry.getValue());
            System.out.println();
        }
    }

    // public static void main(String[] args) {
    //     CarModel carModels = new CarModel();
    //     carModels.readCSV("path/to/your/file.csv");
    //     carModels.printCarModels();
    // }
}