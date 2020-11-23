package no.parkypark.repository;

import no.parkypark.model.Parkinglot;

import java.util.List;

public interface IParkinglotsRepository {
    List<Parkinglot> getAllParkinglots();

    Parkinglot getParkinglot(String name);

    Parkinglot addParkinglot(Parkinglot parkinglot) throws Exception;

    Parkinglot getParkinglotById(String id);

    Parkinglot updateParkinglot(Parkinglot updatedLot) throws Exception;
}
