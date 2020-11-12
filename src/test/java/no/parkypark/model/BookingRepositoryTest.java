package no.parkypark.model;

import no.parkypark.repository.BookingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookingRepositoryTest {
    private List<Booking> expectedBookings;
    private Booking booking;

    private void setUpExpectedParkinglots() {
        expectedBookings = new ArrayList<>();
        booking = new Booking("String userId", "String parkinglotId", "String carLicenceNumber", new Date(2020, 01, 23), new Date(2020, 01, 25), null);
        expectedBookings.add(booking);
        expectedBookings.add(new Booking("String userId2", "String parkinglotId2", "String carLicenceNumber2", new Date(2020, 01, 18), new Date(2020, 01, 28), null));
    }

    @BeforeEach
    public void setup() {
        setUpExpectedParkinglots();
    }

    @Test
    public void addBookingTest(@Mock JsonStorage<Booking> storage) {
        when(storage.read()).thenReturn(expectedBookings);

        BookingRepository repo = new BookingRepository(storage);

        Booking booking = new Booking("String userId3", "String parkinglotId3", "String carLicenceNumber3", new Date(2020, 01, 27), new Date(2020, 01, 29), null);

        Booking result = repo.addBooking(booking);

        assertEquals(result, booking);

        assertEquals(3, expectedBookings.size());
        verify(storage).write(expectedBookings);
    }
}
