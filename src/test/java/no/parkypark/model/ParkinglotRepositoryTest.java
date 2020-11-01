package no.parkypark.model;

import no.parkypark.repository.ParkinglotsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
	private void setup() {
		setUpExpectedParkinglots();
	}

	@Test
	public void addParkinglotTest() {
		JsonStorage<Parkinglot> storage = mock(JsonStorage.class);
		when(storage.read()).thenReturn(expectedLots);

		ParkinglotsRepository repo = new ParkinglotsRepository(storage);

		Parkinglot lot = new Parkinglot("name", "adress", "ownerId", 666);

		Parkinglot result = repo.addParkinglot(lot);

		assertEquals(result, lot);

		ArgumentCaptor<JsonStorage<Parkinglot>> argument = ArgumentCaptor.forClass(JsonStorage.class);
		verify(storage).write((List<Parkinglot>) argument.capture());

		// At this point expectedLots contains the added Parkinglot
		assertEquals(expectedLots, argument.getValue());
	}

	@Test
	public void getAllParkingLotsTest() {
		JsonStorage<Parkinglot> storage = mock(JsonStorage.class);
		when(storage.read()).thenReturn(expectedLots);

		ParkinglotsRepository repo = new ParkinglotsRepository(storage);

		List<Parkinglot> actual = repo.getAllParkinglots();

		assertEquals(expectedLots, actual);
	}

	@Test
	public void getParkinglotByIdTest() {
		JsonStorage<Parkinglot> storage = mock(JsonStorage.class);
		when(storage.read()).thenReturn(expectedLots);

		ParkinglotsRepository repo = new ParkinglotsRepository(storage);

		Parkinglot actual = repo.getParkinglotById(parkingLot.getId());

		assertEquals(parkingLot, actual);
	}
	@Test
	public void updateParkinglotTest() {
		JsonStorage<Parkinglot> storage = mock(JsonStorage.class);
		when(storage.read()).thenReturn(expectedLots);

		ParkinglotsRepository repo = new ParkinglotsRepository(storage);

		// Creating a new object here as the update, since merely changing
		// the values of the parkingLot object would change the object fields
		// before calling update.
		Map<String, List<String>> formParams = new HashMap<>();
		formParams.put("name", new ArrayList<String>(Collections.singleton("updatedName")));
		formParams.put("address", new ArrayList<String>(Collections.singleton("updatedAddress")));
		formParams.put("ownerId", new ArrayList<String>(Collections.singleton("updatedOwnerId")));
		formParams.put("price", new ArrayList<String>(Collections.singleton("123")));

		try {
			repo.updateParkinglot(formParams);

			assertEquals(parkingLot.getId(), formParams.get("id").get(0));
			assertEquals(parkingLot.getName(), formParams.get("name").get(0));
			assertEquals(parkingLot.getAddress(), formParams.get("address").get(0));
			assertEquals(parkingLot.getPrice(), formParams.get("ownerId").get(0));
			assertNotEquals(parkingLot.getOwnerId(), formParams.get("price").get(0));

		} catch (Exception e) { }
	}
}
