package no.parkypark.repository;

import no.parkypark.model.Booking;
import no.parkypark.model.IStorage;

import java.util.List;

public class BookingRepository implements IBookingRepository{
	private final IStorage<Booking> storage;
	private List<Booking> bookings;

	public BookingRepository(IStorage<Booking> storage) {
		this.storage = storage;
		this.bookings = storage.read();
	}

	/**
	 * Add Parkinglot
	 * ----------
	 * Adds a booking to the repository and in the JSON file.
	 *
	 * @param booking The booking to be added
	 * @return The booking that was added.
	 */
	@Override
	public Booking addBooking(Booking booking) {
		this.bookings.add(booking);
		this.storage.write(this.bookings);
		this.bookings = this.storage.read();
		return booking;
	}
}
