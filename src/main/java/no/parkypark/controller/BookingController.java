package no.parkypark.controller;


import io.javalin.http.Context;
import no.parkypark.model.Booking;
import no.parkypark.model.Payment;
import no.parkypark.repository.BookingRepository;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public class BookingController {
	private BookingRepository bookingRepository;
	public BookingController(BookingRepository bookingRepository) {
		this.bookingRepository = bookingRepository;
	}

	public void bookParkinglot(Context ctx) {
		try {
			Map<String, List<String>> formParams = ctx.formParamMap();
			//set new values
			Booking newBooking = new Booking(
					formParams.get("userId").get(0),
					formParams.get("parkinglotId").get(0),
					formParams.get("car").get(0),
					new SimpleDateFormat("yyyy-MM-dd").parse(formParams.get("checkin").get(0)),
					new SimpleDateFormat("yyyy-MM-dd").parse(formParams.get("checkout").get(0)),
					new Payment(
							1,
							"",
							"",
							false,
							1,
							1)
			);
			bookingRepository.addBooking(newBooking);
			ctx.redirect("/");
		} catch (Exception e) {
			ctx.result("input Error!");
		}
	}
}