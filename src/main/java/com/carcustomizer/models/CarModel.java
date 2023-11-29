package main.java.com.carcustomizer.models;
// import com.opencsv.CSVReader;
// import com.opencsv.exceptions.CsvValidationException;

// import java.io.FileReader;
// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;


public class CarModel {
    // private Map<String, List<CarModel>> carModelsByMake;

    // public CarModel() {
    //     carModelsByMake = new HashMap<>();
    // }

    // public void readCSV(String filePath) {
    //     try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
    //         String[] line;
    //         reader.readNext(); // Skip header line
    //         while ((line = reader.readNext()) != null) {
    //             int year = Integer.parseInt(line[0]);
    //             String make = line[1];
    //             String model = line[2];
    //             String[] bodyStyles = line[3].replaceAll("[\\[\\]\"]", "").split(", ");

    //             CarModel carModel = new CarModel();
    //             carModel.setYear(year);
    //             carModel.setMake(make);
    //             carModel.setModel(model);
    //             carModel.setBodyStyles(bodyStyles);

    //             carModelsByMake.computeIfAbsent(make, k -> new ArrayList<>()).add(carModel);
    //         }
    //     } catch (IOException | CsvValidationException e) {
    //         e.printStackTrace();
    //     }
    // }

    // public void displayModelsByMake(String make) {
    //     List<CarModel> models = carModelsByMake.get(make);
    //     if (models != null) {
    //         System.out.println("Available models for " + make + ":");
    //         for (CarModel model : models) {
    //             System.out.println("- " + model.getModel() + " (" + model.getYear() + ")");
    //         }
    //     } else {
    //         System.out.println("No models found for " + make);
    //     }
    // }

    // Other methods and functionalities related to car models
    // ...
}
    

