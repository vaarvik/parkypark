package no.parkypark.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

public class FetchParkinglotByIdTest {

	public static Stream<Arguments> parkinglotArgs() {

		ParkinglotRegistry storedInputLots =  new ParkinglotRegistry();
		storedInputLots.addLot(new Parkinglot(
						"Billigbillig Parkering",
						"Lolveien 22",
						"123HH")
		);

		storedInputLots.addLot(new Parkinglot(
						"Billigbillig Parkering",
						"Lolveien 22",
						"Yolo")
		);

		storedInputLots.addLot(new Parkinglot(
						"Billigbillig Parkering",
						"Lolveien 22",
						"Wee")
		);

		return Stream.of(
						Arguments.of("123HH", storedInputLots),
						Arguments.of("Yolo", storedInputLots),
						Arguments.of("Wee", storedInputLots)
						);
	}

	@ParameterizedTest()
	@MethodSource("parkinglotArgs")
	public void fetchParkIdTest(String parkinglotId, ParkinglotRegistry listOfLots) {

		Parkinglot actualLot = listOfLots.getParkinglotById(parkinglotId);
		Assertions.assertEquals(parkinglotId, actualLot.getId());
	}
}
