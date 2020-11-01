package no.parkypark.repository;

import no.parkypark.model.Parkinglot;

import java.util.List;
import java.util.Map;

public interface IParkinglotsRepository {
    List<Parkinglot> getAllParkinglots();

    Parkinglot getParkinglot(String name);

    Parkinglot addParkinglot(Parkinglot parkinglot) throws Exception;

    Parkinglot getParkinglotById(String id);

    Parkinglot updateParkinglot(Map<String, List<String>> changes) throws Exception;
}
