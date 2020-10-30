package no.parkypark.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import no.parkypark.model.Booking;
import no.parkypark.model.Payment;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookingRepository implements IBookingRepository{
	private List<Booking> bookings;
	private String bookingsFile;

	public BookingRepository(String filePath) {
		this.readJSONFile(filePath);
	}


	public BookingRepository() {

	}

	@Override
	public List<Booking> getAllBookings() {
		return null;
	}

	@Override
	public Booking updateBooking() {
		return null;
	}

	@Override
	public Booking deleteBooking() {
		return null;
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
		this.bookings = new ArrayList<>(readJSONFile(this.bookingsFile));
		this.bookings.add(booking);
		writeToJSONFile(this.bookingsFile, this.bookings);
		return booking;
	}

	/**
	 * Update Booking
	 * ----------
	 * Updates a booking in the repository and in the JSON file.
	 *
	 * @param changes The booking to be added. A Map object.
	 * @return The booking that was updated.
	 */
	public Booking updateBooking(Map<String, List<String>> changes) throws ParseException {
		Booking booking = getBookingById(changes.get("id").get(0));

		//set new values
		Booking newBooking = new Booking(
				changes.get("userId").get(0),
				changes.get("parkinglotId").get(0),
				changes.get("car").get(0),
				new SimpleDateFormat("dd/MM/yyyy").parse(changes.get("checking").get(0)),
				new SimpleDateFormat("dd/MM/yyyy").parse(changes.get("checkout").get(0)),
				new Payment(
						1,
						"",
						"",
						false,
						1,
						1)
		);

		//update JSON
		writeToJSONFile(this.bookingsFile, this.bookings);
		return newBooking;
	}

	@Override
	public Booking getBookingById(String id) {
		for (Booking i: this.bookings) {
			if(i.getId().equals(id)) {
				return i;
			}
		}
		return null;
	}

	/**
	 * Write to JSON File
	 * ----------
	 * Writes to a JSON file and attach the updated data to an instance variable in this repository.
	 *
	 * @param filePath The path that the data should be fetched from.
	 * @return The data that is fetched. A List object.
	 */
	private List<Booking> writeToJSONFile(String filePath, List<Booking> bookings) {
		//create the mapper that we use to create the data into an object
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), bookings);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this.bookings;
	}

	/**
	 * Read JSON File
	 * ----------
	 * Reads a JSON file and attaches the data that is received to an instance variable in this repository.
	 *
	 * @param filePath The path that the data should be fetched from.
	 * @return The data that is fetched. A List object.
	 */
	private List<Booking> readJSONFile(String filePath) {
		//create the mapper that we use to create the data into an object
		this.bookingsFile = filePath;
		ObjectMapper mapper = new ObjectMapper();
		try {
			// JSON file to Java object
			Booking[] bookings = mapper.readValue(new File(filePath), Booking[].class);
			//store the data in the instance variable that stores the data
			this.bookings = Arrays.asList(bookings);
		} catch (IOException e) {
			//if there's an error fetching the data...
			e.printStackTrace();
		}
		return this.bookings;
	}
}
