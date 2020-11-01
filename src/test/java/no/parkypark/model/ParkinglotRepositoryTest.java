package no.parkypark.model;

import no.parkypark.repository.ParkinglotsRepository;
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

		ParkinglotsRepository repo = new ParkinglotsRepository(storage);

		// Creating a new object here as the update, since merely changing
		// the values of the parkingLot object would change the object fields
		// before calling update.
		Parkinglot update = new Parkinglot();
		update.setId(parkingLot.getId());
		update.setName("updatedName");
		update.setAddress("updatedAdress");
		update.setOwnerId("updatedOwnerId");
		update.setPrice(123);

		repo.updateParkinglot(update);

		assertEquals(parkingLot.getId(), update.getId());
		assertEquals(parkingLot.getName(), update.getName());
		assertEquals(parkingLot.getAddress(), update.getAddress());
		assertEquals(parkingLot.getPrice(), update.getPrice());
		assertNotEquals(parkingLot.getOwnerId(), update.getOwnerId());
	}
}
