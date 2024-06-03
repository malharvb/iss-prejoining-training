/*
 * Program to demonstrate read, modify and create JSON objects in Java using GSON
 */
package javapractice;

import com.google.gson.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JSONDemo {

    // Logging setup
    private static Logger fileLogger = LogManager.getLogger(JSONDemo.class.getName());
    
    private static final String FILE_PATH = "./outputs/carparts.json";

    public static void main(String[] args) {

        // Create JSON Object
        JsonObject carPart = new JsonObject();
        carPart.addProperty("partID", "12345");
        carPart.addProperty("partName", "Brake Pad");
        carPart.addProperty("manufacturer", "XYZ Corp");
        carPart.addProperty("price", 1250);

        // Modify JSON object to add array as property value
        JsonArray compatibleModels = new JsonArray();
        compatibleModels.add(new JsonPrimitive("Model 2018"));
        compatibleModels.add(new JsonPrimitive("Model 2020"));
        compatibleModels.add(new JsonPrimitive("Model 2024"));
        carPart.add("compatibleModels", compatibleModels);

        // Using GsonBuilder to print JSON in a readable manner
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(carPart);

        fileLogger.info("Created JSON object:");
        fileLogger.info(jsonString);

        // Write the JSON object to a file
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            writer.write(jsonString);
            writer.flush();
        } catch (IOException e) {
            fileLogger.error("IOException occurred", e);
        }

        // Read the JSON object from the file
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            JsonObject carPartFromFile = gson.fromJson(reader, JsonObject.class);
            fileLogger.info("Read JSON object from file:");
            fileLogger.info(gson.toJson(carPartFromFile));

            // Modify the JSON object
            carPartFromFile.addProperty("price", 1550);
            fileLogger.info("Modified JSON object:");
            fileLogger.info(gson.toJson(carPartFromFile));

            // Remove an element from the JSON object
            carPartFromFile.remove("manufacturer");
            fileLogger.info("JSON object after manufacturer property deletion:");
            fileLogger.info(gson.toJson(carPartFromFile));

            // Write the modified JSON object back to the file
            try (FileWriter writer = new FileWriter(FILE_PATH)) {
                writer.write(gson.toJson(carPartFromFile));
                writer.flush();
            } catch (IOException e) {
                fileLogger.error("IOException occurred", e);
            }

        } catch (IOException e) {
            fileLogger.error("IOException occurred", e);
        } catch (JsonSyntaxException e) {
            fileLogger.error("JsonSyntaxException occurred", e);
        }
    }
}
