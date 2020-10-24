package no.parkypark.controllers;


import io.javalin.http.Context;
import no.parkypark.model.Booking;
import no.parkypark.repository.BookingRepository;

public class BookingController {
	private BookingRepository repository;
	public BookingController(BookingRepository repository) {
		this.repository = repository;
	}

	public void bookParkinglot(Context ctx) {
		try {
			Booking booking = ctx.bodyAsClass(Booking.class);
			repository.addBooking(booking);
			ctx.json(booking);
		} catch (Exception e) {
			ctx.result("input Error!");
		}
	}
}