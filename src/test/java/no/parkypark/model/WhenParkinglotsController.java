package no.parkypark.model;

import io.javalin.http.Context;
import no.parkypark.controller.ParkinglotsController;
import no.parkypark.repository.ParkinglotsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WhenParkinglotsController {
    private List<Parkinglot> expectedParkinglots;
    private Parkinglot expectedParkinglot;
    private LocalDateTime d1;
    private LocalDateTime d2;
    private String imageUrl;

    private void setUpExpectedParkinglots() {
        imageUrl = "https://placekitten.com/200/300";
        d1 = LocalDateTime.of(2020, Month.DECEMBER, 12, 19, 5, 40);
        d2 = LocalDateTime.of(2020, Month.DECEMBER, 22, 19, 4, 40);

        expectedParkinglots = new ArrayList<>();
        expectedParkinglot = new Parkinglot("Flott parkeringplass ved sjøen", "21 2nd Street", "123", 20, imageUrl, "", d1, d2);
        expectedParkinglots.add(expectedParkinglot);
        expectedParkinglots.add(new Parkinglot("Plass for parkering", "Matrix Street", "456", 30, imageUrl, "", d1, d2));
    }

    @BeforeEach
    public void setup() {
        setUpExpectedParkinglots();
    }

    private final Context ctx = mock(Context.class); // "mock-maker-inline" must be enabled
    private final ParkinglotsRepository repo = mock(ParkinglotsRepository.class); // "mock-maker-inline" must be enabled

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

        ParkinglotsController controller = new ParkinglotsController(repo);
        controller.getParkinglot(ctx);
        verify(ctx).json(expectedParkinglot);
    }

    @Test
    public void fetchAllParkinglotsByUser() {
        when(repo.getParkinglotsByOwnerId(ctx.pathParam(":userid"))).thenReturn(expectedParkinglots);

        ParkinglotsController controller = new ParkinglotsController(repo);
        controller.getAllParkinglotsByUser(ctx);
        verify(ctx).json(expectedParkinglots);
    }

    @Test
    public void updateAParkinglot() {
        LocalDateTime updatedD1 = LocalDateTime.of(2021, Month.DECEMBER, 12, 19, 5, 40);
        LocalDateTime updatedD2 = LocalDateTime.of(2022, Month.DECEMBER, 12, 19, 5, 40);

        when(ctx.bodyAsClass(Parkinglot.class)).thenReturn(expectedParkinglot);
        expectedParkinglot.update(new Parkinglot(
                "NewName",
                "NewAddress",
                "13",
                2,
                "https://newimage",
                "NewDescription",
                updatedD1,
                updatedD2
        ));

        ParkinglotsController controller = new ParkinglotsController(repo);
        controller.updateParkinglot(ctx);
        verify(ctx).json(expectedParkinglot);
    }

    @Test
    public void addAParkinglot() {
        when(ctx.bodyAsClass(Parkinglot.class)).thenReturn(expectedParkinglot);

        Parkinglot newParkinglot = ctx.bodyAsClass(Parkinglot.class);
        ParkinglotsController controller = new ParkinglotsController(repo);
        controller.addParkinglot(ctx);
        verify(ctx).json(newParkinglot);
    }
}
