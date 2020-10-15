package no.parkypark.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import no.parkypark.model.Parkinglot;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class ParkyparkRepository implements IParkyparkRepository {
    private ArrayList<Parkinglot> parkinglots = null;

    @Override
    public ArrayList<Parkinglot> getParkinglots() {
        return this.parkinglots;
    }

    @Override
    public Parkinglot getParkinglot(String name) {
        return null;
    }

    @Override
    public Parkinglot addParkinglot(Parkinglot parkinglot) {
        return null;
    }

    @Override
    public Parkinglot getParkinglotById(String name) {
        return null;
    }

    @Override
    public Parkinglot readJSONFile(String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        Parkinglot parkinglot = null;
        try {
            // JSON file to Java object
            parkinglot = mapper.readValue(new File(filePath), Parkinglot.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parkinglot;
    }















}
