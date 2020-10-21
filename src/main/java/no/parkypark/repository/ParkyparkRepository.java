package no.parkypark.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import no.parkypark.model.Parkinglot;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


public class ParkyparkRepository implements IParkyparkRepository {
    private List<Parkinglot> parkinglots = new ArrayList<>();

    public ParkyparkRepository(List<Parkinglot> parkinglots) {
        this.parkinglots = parkinglots;
    }

    public ParkyparkRepository(String filePath) {
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
    public Parkinglot createParkinglot(String name, String adress) throws Exception {
        if (name == null || adress == null) {
            throw new Exception("Adress and name can not be null!");
        }
        Parkinglot addedParkinglot = new Parkinglot(name, adress, UUID.randomUUID().toString());
        this.parkinglots.add(addedParkinglot);
        return addedParkinglot;
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
     * Read JSON File
     * ----------
     * Reads a JSON file and attaches the data that is received to a instance variable in this repository.
     *
     * @param filePath The path that the data should be fetched from.
     * @return The data that is fetched. A List object.
     */
    private List<Parkinglot> readJSONFile(String filePath) {
        //create the mapper that we use to create the data into an object
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
