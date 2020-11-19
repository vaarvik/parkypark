package no.parkypark.controller;

import io.javalin.http.Context;
import no.parkypark.model.Parkinglot;
import no.parkypark.repository.ParkinglotsRepository;

public class ParkinglotsController {
    private final ParkinglotsRepository parkinglotsRepository;
    public ParkinglotsController(ParkinglotsRepository parkinglotsRepository) {
        this.parkinglotsRepository = parkinglotsRepository;
    }

    public void getAllParkinglots(Context ctx) {
        ctx.json(parkinglotsRepository.getAllParkinglots());
    }

    public void getParkinglot(Context ctx) {
        ctx.json(parkinglotsRepository.getParkinglotById(ctx.pathParam(":parkinglotid")));
    }

    public void addParkinglot(Context ctx){
        Parkinglot lot = ctx.bodyAsClass(Parkinglot.class);

        try {
            parkinglotsRepository.addParkinglot(lot);
            ctx.json(lot);
        } catch (Exception e) {
            ctx.result("Input error");
        }
    }

    public void updateParkinglot(Context ctx) {
        try {
            Parkinglot lot = ctx.bodyAsClass(Parkinglot.class);
            parkinglotsRepository.updateParkinglot(lot);
            ctx.json(lot);
        } catch (Exception e) {
            ctx.status();
        }

    }
}
