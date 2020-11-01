package no.parkypark.controller;

import io.javalin.http.Context;
import no.parkypark.repository.ParkinglotsRepository;

import java.util.List;
import java.util.Map;

public class ParkinglotsController {
    private ParkinglotsRepository parkinglotsRepository;
    public ParkinglotsController(ParkinglotsRepository parkinglotsRepository) {
        this.parkinglotsRepository = parkinglotsRepository;
    }

    public void getAllParkinglots(Context ctx) {
        ctx.json(parkinglotsRepository.getAllParkinglots());
    }

    public void getParkinglot(Context ctx) {
        ctx.json(parkinglotsRepository.getParkinglotById(ctx.pathParam(":parkinglotid")));
    }

    public void updateParkinglot(Context ctx) {
        //map creates a JavaScript-like object
        Map<String, List<String>> formParams = ctx.formParamMap();
        parkinglotsRepository.updateParkinglot(formParams);
        //redirect user after update
        ctx.redirect("/");
    }
}
