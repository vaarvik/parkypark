package no.parkypark.model;

import no.parkypark.repository.BookingRepository;
import no.parkypark.utils.JsonStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookingRepositoryTest {
    private List<Booking> expectedBookings;
    private Booking booking;
    private  LocalDateTime d1 = LocalDateTime.of(2020, Month.NOVEMBER, 23, 19, 30, 40);
    private LocalDateTime d2 = LocalDateTime.of(2020, Month.NOVEMBER, 25, 19, 30, 40);

    private void setUpExpectedParkinglots() {
        LocalDateTime d3 = LocalDateTime.of(2020, Month.DECEMBER, 13, 19, 30, 40);
        LocalDateTime d4 = LocalDateTime.of(2020, Month.DECEMBER, 24, 19, 30, 40);

        expectedBookings = new ArrayList<>();
        booking = new Booking("String userId", "String parkinglotId", "String carLicenceNumber", d1, d2, null);
        expectedBookings.add(booking);
        expectedBookings.add(new Booking("String userId2", "String parkinglotId2", "String carLicenceNumber2", d3, d4, null));
    }

    @BeforeEach
    public void setup() {
        setUpExpectedParkinglots();
    }

    @Test
    public void getAllBookingsTest(@Mock JsonStorage<Booking> storage) {
        when(storage.read()).thenReturn(expectedBookings);
        BookingRepository repo = new BookingRepository(storage);
        assertEquals(expectedBookings, repo.getAllBookings());
        verify(storage).read();
    }

    @Test
    public void addBookingTest(@Mock JsonStorage<Booking> storage) {
        when(storage.read()).thenReturn(expectedBookings);

        BookingRepository repo = new BookingRepository(storage);
        Booking booking = new Booking("String userId3", "String parkinglotId3", "String carLicenceNumber3", d1, d2, null);

        expectedBookings.add(booking);
        storage.write(expectedBookings);

        assertEquals(expectedBookings, repo.getAllBookings());
        verify(storage).write(expectedBookings);
    }
}
