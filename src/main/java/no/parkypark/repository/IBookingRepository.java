package no.parkypark.repository;

import no.parkypark.model.Booking;

import java.util.List;

public interface IBookingRepository {
    List<Booking> getAllBookings();

    Booking getBookingById();

    Booking addBooking();

    Booking updateBooking();

    Booking deleteBooking();
}
