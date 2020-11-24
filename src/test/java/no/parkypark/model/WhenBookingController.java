package no.parkypark.model;

import io.javalin.http.Context;
import no.parkypark.controller.BookingController;
import no.parkypark.repository.BookingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WhenBookingController {
    private List<Booking> expectedBookings;
    private Booking expectedBooking;

    private void setUpExpectedBookings() {
        expectedBookings = new ArrayList<>();
        expectedBooking = new Booking("userId", "parkinglotId", "carLicenceNumber", LocalDate.of(2020, 01, 23), LocalDate.of(2020, 01, 25), null);
        expectedBookings.add(expectedBooking);
        expectedBookings.add(new Booking("String userId2", "String parkinglotId2", "String carLicenceNumber2", LocalDate.of(2020, 01, 18), LocalDate.of(2020, 01, 28), null));
    }

    @BeforeEach
    public void setup() {
        setUpExpectedBookings();
    }

    private Context ctx = mock(Context.class); // "mock-maker-inline" must be enabled
    private BookingRepository repo = mock(BookingRepository.class); // "mock-maker-inline" must be enabled

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
