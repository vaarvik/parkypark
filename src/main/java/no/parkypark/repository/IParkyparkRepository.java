package no.parkypark.repository;

import no.parkypark.model.Parkinglot;

import java.util.ArrayList;

public interface IParkyparkRepository {
    //hente alle parkeringsplasser, Arraylist foreløpig?
    ArrayList<Parkinglot> getParkinglots();

    //hente en spesifikk parkeringsplass
    Parkinglot getParkinglot(String name);

    //hente en spesifikk parkeringsplass
    Parkinglot addParkinglot(Parkinglot parkinglot);

    //hente en spesifikk parkeringsplass basert på ID
    Parkinglot getParkinglotById(String name);

    //read a file with json data
    Parkinglot readJSONFile(String filePath);
}
