package no.parkypark.repository;

import no.parkypark.model.Parkinglot;

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
    public Parkinglot getParkinglotById(String name) {
        return null;
    }

    @Override
    public ArrayList<Parkinglot> readJSONFile() {
        return null;
    }

}
