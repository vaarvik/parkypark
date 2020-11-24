package no.parkypark.model;

import no.parkypark.repository.ParkinglotsRepository;
import no.parkypark.utils.JsonStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ParkinglotRepositoryTest {
	private List<Parkinglot> expectedLots;
	private Parkinglot parkingLot;

	private void setUpExpectedParkinglots() {
		expectedLots = new ArrayList<>();
		parkingLot = new Parkinglot("Flott parkeringplass ved sjøen", "21 2nd Street", "123", 20);
		expectedLots.add(parkingLot);
		expectedLots.add(new Parkinglot("Plass for parkering", "Matrix Street", "456", 30));
	}

	@BeforeEach
	public void setup() {
		setUpExpectedParkinglots();
	}

	@Test
	public void addParkinglotTest(@Mock JsonStorage<Parkinglot> storage) {
		when(storage.read()).thenReturn(expectedLots);

		ParkinglotsRepository repo = new ParkinglotsRepository(storage);

		Parkinglot lot = new Parkinglot("name", "adress", "ownerId", 666);

		Parkinglot result = repo.addParkinglot(lot);

		assertEquals(result, lot);

		assertEquals(3, expectedLots.size());
		verify(storage).write(expectedLots);
	}

	@Test
	public void getAllParkingLotsTest(@Mock JsonStorage<Parkinglot> storage) {
		when(storage.read()).thenReturn(expectedLots);

		ParkinglotsRepository repo = new ParkinglotsRepository(storage);

		List<Parkinglot> actual = repo.getAllParkinglots();

		assertEquals(expectedLots, actual);
	}

	@Test
	public void getParkinglotByIdTest(@Mock JsonStorage<Parkinglot> storage) {
		when(storage.read()).thenReturn(expectedLots);

		ParkinglotsRepository repo = new ParkinglotsRepository(storage);

		Parkinglot actual = repo.getParkinglotById(parkingLot.getId());

		assertEquals(parkingLot, actual);
	}

	@Test
	public void updateParkinglotTest(@Mock JsonStorage<Parkinglot> storage) throws Exception {
		when(storage.read()).thenReturn(expectedLots);

		// Create the repository
		ParkinglotsRepository repo = new ParkinglotsRepository(storage);

		// The updated parkinglot
		Parkinglot updatedLot = new Parkinglot("updatedName", "updatedAdress", parkingLot.getOwnerId(), 3);
		updatedLot.setId(parkingLot.getId());

		// Update parkinglot
		repo.updateParkinglot(updatedLot);

		assertEquals(parkingLot, updatedLot);
	}
}
