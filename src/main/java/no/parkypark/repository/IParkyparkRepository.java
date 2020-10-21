package no.parkypark.repository;

import no.parkypark.model.Parkinglot;

import java.util.List;

public interface IParkyparkRepository {
    //hente alle parkeringsplasser, Arraylist foreløpig?
    List<Parkinglot> getAllParkinglots();

    //hente en spesifikk parkeringsplass
    Parkinglot getParkinglot(String name);

    //hente en spesifikk parkeringsplass
    Parkinglot createParkinglot(String name, String adress) throws Exception;

    //hente en spesifikk parkeringsplass basert på ID
    Parkinglot getParkinglotById(String id);

}
