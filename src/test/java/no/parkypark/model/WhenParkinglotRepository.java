package no.parkypark.model;

import no.parkypark.repository.ParkinglotsRepository;
import no.parkypark.utils.JsonStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WhenParkinglotRepository {
	private List<Parkinglot> expectedLots;
	private Parkinglot parkingLot;
	private LocalDateTime d1;
	private LocalDateTime d2;
	private String imageUrl;

	private void setUpExpectedParkinglots() {
		imageUrl = "https://placekitten.com/200/300";
		d1 = LocalDateTime.of(2020, Month.DECEMBER, 12, 19, 5, 40);
		d2 = LocalDateTime.of(2020, Month.DECEMBER, 22, 19, 4, 40);

		expectedLots = new ArrayList<>();
		parkingLot = new Parkinglot("Flott parkeringplass ved sjøen", "21 2nd Street", "123", 20, imageUrl, "", d1, d2);
		expectedLots.add(parkingLot);
		expectedLots.add(new Parkinglot("Plass for parkering", "Matrix Street", "456", 30, imageUrl, "", d1, d2));
	}

	@BeforeEach
	public void setup() {
		setUpExpectedParkinglots();
	}

	@Test
	public void addingAParkinglot(@Mock JsonStorage<Parkinglot> storage) {
		when(storage.read()).thenReturn(expectedLots);

		ParkinglotsRepository repo = new ParkinglotsRepository(storage);

		Parkinglot lot = new Parkinglot("name", "adress", "ownerId", 666, imageUrl, "Description", d1, d2);

		Parkinglot result = repo.addParkinglot(lot);

		assertEquals(result, lot);

		assertEquals(3, expectedLots.size());
		verify(storage).write(expectedLots);
	}

	@Test
	public void getsAllParkingLots(@Mock JsonStorage<Parkinglot> storage) {
		when(storage.read()).thenReturn(expectedLots);

		ParkinglotsRepository repo = new ParkinglotsRepository(storage);

		List<Parkinglot> actual = repo.getAllParkinglots();

		assertEquals(expectedLots, actual);
	}

	@Test
	public void getsAParkinglotById(@Mock JsonStorage<Parkinglot> storage) {
		when(storage.read()).thenReturn(expectedLots);

		ParkinglotsRepository repo = new ParkinglotsRepository(storage);

		Parkinglot actual = repo.getParkinglotById(parkingLot.getId());

		assertEquals(parkingLot, actual);
	}

	@Test
	public void updatesAParkinglot(@Mock JsonStorage<Parkinglot> storage) throws Exception {
		when(storage.read()).thenReturn(expectedLots);

		// Create the repository
		ParkinglotsRepository repo = new ParkinglotsRepository(storage);

		LocalDateTime updatedD1 = LocalDateTime.of(2021, Month.DECEMBER, 12, 19, 5, 40);
		LocalDateTime updatedD2 = LocalDateTime.of(2022, Month.DECEMBER, 12, 19, 5, 40);

		// The updated parkinglot
		Parkinglot updatedLot = new Parkinglot(
						"updatedName",
						"updatedAdress",
						parkingLot.getOwnerId(),
						3,
						"https://new-image-url",
						"New description",
						updatedD1,
						updatedD2);

		updatedLot.setId(parkingLot.getId());

		// Update parkinglot
		repo.updateParkinglot(updatedLot);

		assertEquals(parkingLot, updatedLot);
	}
}
