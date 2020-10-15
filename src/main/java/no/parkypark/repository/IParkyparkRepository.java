package no.parkypark.repository;

import no.parkypark.model.Parkinglot;

import java.util.ArrayList;

public interface IParkyparkRepository {
    //hente alle parkeringsplasser, Arraylist foreløpig?
    ArrayList<Parkinglot> getParkinglots();

    //hente en spesifikk parkeringsplass
    Parkinglot getParkinglot(String name);

    //read a file with json data
    ArrayList<Parkinglot> readJSONFile();
}
