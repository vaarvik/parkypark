package no.parkypark.controller;


import io.javalin.http.Context;
import no.parkypark.model.Booking;
import no.parkypark.repository.BookingRepository;

import java.util.ArrayList;
import java.util.List;

public class BookingController {
	private final BookingRepository bookingRepository;
	public BookingController(BookingRepository bookingRepository) {
		this.bookingRepository = bookingRepository;
	}

	public void getAllBookingsByUser(Context ctx) {
		ctx.json(bookingRepository.getBookingsByUserId(ctx.pathParam(":userid")));
	}

	public void bookParkinglot(Context ctx) {
		try {
			Booking newBooking = ctx.bodyAsClass(Booking.class);
			bookingRepository.addBooking(newBooking);
			ctx.json(newBooking);
		} catch (Exception e) {
			ctx.result("Input error");
		}
	}
}