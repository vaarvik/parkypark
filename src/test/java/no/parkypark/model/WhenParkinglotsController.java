package no.parkypark.model;

import io.javalin.http.Context;
import no.parkypark.controller.ParkinglotsController;
import no.parkypark.repository.ParkinglotsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WhenParkinglotsController {
    private List<Parkinglot> expectedParkinglots;
    private Parkinglot expectedParkinglot;
    ArrayList<Parkinglot> depectedParkinglots = new ArrayList<>();

    private void setUpExpectedParkinglots() {
        expectedParkinglots = new ArrayList<>();
        expectedParkinglot = new Parkinglot("Flott parkeringplass ved sjøen", "21 2nd Street", "123", 20);
        expectedParkinglots.add(expectedParkinglot);
        expectedParkinglots.add(new Parkinglot("Plass for parkering", "Matrix Street", "456", 30));
    }

    @BeforeEach
    public void setup() {
        setUpExpectedParkinglots();
    }

    private Context ctx = mock(Context.class); // "mock-maker-inline" must be enabled
    private ParkinglotsRepository repo = mock(ParkinglotsRepository.class); // "mock-maker-inline" must be enabled

    @Test
    public void fetchAllParkinglots() {
        when(repo.getAllParkinglots()).thenReturn(expectedParkinglots);

        ParkinglotsController controller = new ParkinglotsController(repo);
        controller.getAllParkinglots(ctx);
        verify(ctx).json(expectedParkinglots);
    }

    @Test
    public void fetchASingleParkinglot() {
        when(repo.getParkinglotById(ctx.pathParam(":parkinglotid"))).thenReturn(expectedParkinglot);
//
        ParkinglotsController controller = new ParkinglotsController(repo);
        controller.getParkinglot(ctx);
        verify(ctx).json(expectedParkinglot);
    }
}
