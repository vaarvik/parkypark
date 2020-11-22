package no.parkypark.repository;

import no.parkypark.model.Booking;
import no.parkypark.model.IStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookingRepository implements IBookingRepository{
	private final IStorage<Booking> storage;
	private List<Booking> bookings;

	public BookingRepository(IStorage<Booking> storage) {
		this.storage = storage;
		this.bookings = storage.read();
	}

	public ArrayList<Booking> getBookingsByUserId(String ownerId) {
		ArrayList<Booking> bookings = new ArrayList<>();
		for(Booking i : this.bookings ) {
			if(i.getUserId().equals(ownerId)) {
				bookings.add(i);
			}
		}
		return bookings;
	}

	public List<Booking> getAllBookings() {
		return this.bookings;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BookingRepository that = (BookingRepository) o;
		return Objects.equals(storage, that.storage) &&
				Objects.equals(bookings, that.bookings);
	}

	@Override
	public int hashCode() {
		return Objects.hash(storage, bookings);
	}

	@Override
	public String toString() {
		return "BookingRepository{" +
				"storage=" + storage +
				", bookings=" + bookings +
				'}';
	}
}
