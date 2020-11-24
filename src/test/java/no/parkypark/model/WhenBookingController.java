package no.parkypark.model;

import io.javalin.http.Context;
import no.parkypark.controller.BookingController;
import no.parkypark.repository.BookingRepository;
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
public class WhenBookingController {
    private List<Booking> expectedBookings;
    private Booking expectedBooking;

    private void setUpExpectedBookings() {
        LocalDateTime d1 = LocalDateTime.of(2020, Month.DECEMBER, 12, 19, 5, 40);
        LocalDateTime d2 = LocalDateTime.of(2020, Month.DECEMBER, 22, 19, 4, 40);
        LocalDateTime d3 = LocalDateTime.of(2021, Month.JANUARY, 10, 19, 30, 40);
        LocalDateTime d4 = LocalDateTime.of(2021, Month.JANUARY, 9, 19, 2, 40);

        expectedBookings = new ArrayList<>();
        expectedBooking = new Booking("userId", "parkinglotId", "carLicenceNumber", d1, d2, null);
        expectedBookings.add(expectedBooking);
        expectedBookings.add(new Booking("String userId2", "String parkinglotId2", "String carLicenceNumber2", d3, d4, null));
    }

    @BeforeEach
    public void setup() {
        setUpExpectedBookings();
    }

    private final Context ctx = mock(Context.class); // "mock-maker-inline" must be enabled
    private final BookingRepository repo = mock(BookingRepository.class); // "mock-maker-inline" must be enabled

    @Test
    public void fetchAllBookingsByUser() {
        when(repo.getBookingsByUserId(ctx.pathParam(":userid"))).thenReturn(expectedBookings);

        BookingController controller = new BookingController(repo);
        controller.getAllBookingsByUser(ctx);
        verify(ctx).json(expectedBookings);
    }

    @Test
    public void createsABooking() {
        when(ctx.bodyAsClass(Booking.class)).thenReturn(expectedBooking);

        Booking newBooking = ctx.bodyAsClass(Booking.class);
        BookingController controller = new BookingController(repo);
        controller.bookParkinglot(ctx);
        verify(ctx).json(newBooking);
    }
}
