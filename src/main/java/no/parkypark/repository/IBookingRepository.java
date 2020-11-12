package no.parkypark.repository;

import no.parkypark.model.Booking;

public interface IBookingRepository {
    Booking addBooking(Booking booking);
}
