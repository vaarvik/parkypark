package no.parkypark.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import no.parkypark.model.Parkinglot;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class ParkinglotsRepository implements IParkinglotsRepository {
    private String parkinglotsFile = "";
    private List<Parkinglot> parkinglots = new ArrayList<>();

    public ParkinglotsRepository(String filePath) {
        this.readJSONFile(filePath);
    }

    @Override
    public List<Parkinglot> getAllParkinglots() {
        return this.parkinglots;
    }

    @Override
    public Parkinglot getParkinglot(String name) {
        return null;
    }

    @Override
    public Parkinglot getParkinglotById(String id) {
        for (Parkinglot i: this.parkinglots) {
            if(i.getId().equals(id)) {
                return i;
            }
        }
        return null;
    }

    /**
     * Add Parkinglot
     * ----------
     * Adds a parkinglot to the repository and in the JSON file.
     *
     * @param parkinglot The parkinglot to be added
     * @return The parkinglot that was added.
     */
    @Override
    public Parkinglot addParkinglot(Parkinglot parkinglot) {
        this.parkinglots = new ArrayList<>(readJSONFile(this.parkinglotsFile));
        this.parkinglots.add(parkinglot);
        writeToJSONFile(this.parkinglotsFile, this.parkinglots);
        return parkinglot;
    }

    /**
     * Update Parkinglot
     * ----------
     * Updates a parkinglot in the repository and in the JSON file.
     *
     * @param changes The parkinglot to be added. A Map object.
     * @return The parkinglot that was updated.
     */
    public Parkinglot updateParkinglot(Map<String, List<String>> changes) {
        Parkinglot parkinglot = getParkinglotById(changes.get("id").get(0));

        //set new values
        parkinglot.setName(changes.get("name").get(0));
        parkinglot.setAddress(changes.get("address").get(0));
        parkinglot.setPrice(Double.parseDouble(changes.get("price").get(0)));

        //update JSON
        writeToJSONFile(this.parkinglotsFile, this.parkinglots);
        return parkinglot;
    }

    /**
     * Write to JSON File
     * ----------
     * Writes to a JSON file and attach the updated data to an instance variable in this repository.
     *
     * @param filePath The path that the data should be fetched from.
     * @return The data that is fetched. A List object.
     */
    private List<Parkinglot> writeToJSONFile(String filePath, List<Parkinglot> parkinglots) {
        //create the mapper that we use to create the data into an object
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), parkinglots);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.parkinglots;
    }

    /**
     * Read JSON File
     * ----------
     * Reads a JSON file and attaches the data that is received to an instance variable in this repository.
     *
     * @param filePath The path that the data should be fetched from.
     * @return The data that is fetched. A List object.
     */
    private List<Parkinglot> readJSONFile(String filePath) {
        //create the mapper that we use to create the data into an object
        this.parkinglotsFile = filePath;
        ObjectMapper mapper = new ObjectMapper();
        try {
            // JSON file to Java object
            Parkinglot[] parkinglots = mapper.readValue(new File(filePath), Parkinglot[].class);
            //store the data in the instance variable that stores the data
            this.parkinglots = Arrays.asList(parkinglots);
        } catch (IOException e) {
            //if there's an error fetching the data...
            e.printStackTrace();
        }
        return this.parkinglots;
    }
}
