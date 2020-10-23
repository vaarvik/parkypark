package no.parkypark.model;

import no.parkypark.repository.ParkyparkRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FetchParkinglotTest {
	@Test
	public void returnsParkingLotObjectIfIdIsFound() {
		List<Parkinglot> parkinglots = new ArrayList<>();
		parkinglots.add(new Parkinglot("Smith", "21 2nd Street","1"));
		parkinglots.add(new Parkinglot("John", "Matrix Street", "2"));

		Parkinglot resultLot = new ParkyparkRepository(parkinglots).getParkinglotById("1");

		Assertions.assertEquals("Smith",resultLot.getName());
	}

	@Test
	public void returnsNullIfParkingLotNotFound() {
		List<Parkinglot> parkinglots = new ArrayList<>();
		parkinglots.add(new Parkinglot("Smith", "21 2nd Street","1"));
		parkinglots.add(new Parkinglot("John", "Matrix Street", "2"));

		Parkinglot resultLot = new ParkyparkRepository(parkinglots).getParkinglotById("3");

		Assertions.assertEquals(null,resultLot);
	}

	@Test
	public void returnsAllParkingLots() {
		List<Parkinglot> parkinglots = new ArrayList<>();
		parkinglots.add(new Parkinglot("Smith", "21 2nd Street","1"));
		parkinglots.add(new Parkinglot("John", "Matrix Street", "2"));

		List<Parkinglot> resultLots = new ParkyparkRepository(parkinglots).getAllParkinglots();

		Assertions.assertEquals(2, resultLots.size());
	}
}
