package no.parkypark.controller;

import io.javalin.http.Context;
import no.parkypark.model.Parkinglot;
import no.parkypark.repository.ParkinglotsRepository;

public class ParkinglotsController {
    private ParkinglotsRepository parkinglotsRepository;
    public ParkinglotsController(ParkinglotsRepository parkinglotsRepository) {
        this.parkinglotsRepository = parkinglotsRepository;
    }

    public void getAllParkinglots(Context ctx){
        ctx.json(parkinglotsRepository.getAllParkinglots());
    }

    public void getParkinglot(Context ctx){
        ctx.json(parkinglotsRepository.getParkinglotById(ctx.pathParam(":parkinglotid")));
    }

    public void updateParkinglot(Context ctx) {
        Parkinglot lot = ctx.bodyAsClass(Parkinglot.class);

        try {
            parkinglotsRepository.updateParkinglot(lot);
        } catch (Exception e) {
            ctx.result("Parkinglot not found");
        }
        //redirect user after update
        ctx.redirect("/");
    }
}
