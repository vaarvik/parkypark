package no.parkypark.repository;

import no.parkypark.model.Booking;

import java.util.List;

public interface IBookingRepository {
    List<Booking> getAllBookings();

    Booking getBookingById(String id);

    Booking addBooking(Booking booking);

    Booking deleteBooking();
}
