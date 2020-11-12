package no.parkypark.repository;

import no.parkypark.model.Booking;

import java.util.List;

public interface IBookingRepository {

    List<Booking> getAllBookings();

    Booking addBooking(Booking booking);
}
