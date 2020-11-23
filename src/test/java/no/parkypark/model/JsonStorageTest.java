package no.parkypark.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonStorageTest {
	private static final String JSON_PARKINGLOT_FILE = "[{ \"name\" : \"Best Spot!\", \"address\" : \"Planetroad 2\",\"id\" : \"b5319909-1968-442c-93e9-fd08d24d985f\",\"ownerId\" : \"fe34e136-ff5a-4c2f-93c3-1820bc170f83\",\"price\" : 50.0}]";


	@Test
	void readFromFileTest(@TempDir Path tempDir) throws IOException {
		Path path = tempDir.resolve("parkinglots.json");
		Files.write(path, JSON_PARKINGLOT_FILE.getBytes());

		JsonStorage<Parkinglot> parkinglotJsonStorage = new JsonStorage<>(Parkinglot.class, path.toAbsolutePath().toString());
		List<Parkinglot> parkinglotsFromFile = parkinglotJsonStorage.read();

		assertEquals(1, parkinglotsFromFile.size());

		Parkinglot lot = parkinglotsFromFile.get(0);

		assertEquals(50.0, lot.getPrice());
		assertEquals("Planetroad 2", lot.getAddress());
		assertEquals("Best Spot!", lot.getName());
		assertEquals("b5319909-1968-442c-93e9-fd08d24d985f", lot.getId());
		assertEquals("fe34e136-ff5a-4c2f-93c3-1820bc170f83", lot.getOwnerId());
	}

	@Test
	void writeToFile(@TempDir Path tempDir) {
		Path path = tempDir.resolve("parkinglots.json");

		JsonStorage<Parkinglot> parkinglotJsonStorage = new JsonStorage<>(Parkinglot.class, path.toAbsolutePath().toString());

		Parkinglot lot = new Parkinglot("Name", "Adress", "OwnerId", 100);
		ArrayList<Parkinglot> lots = new ArrayList<>();
		lots.add(lot);

		parkinglotJsonStorage.write(lots);

		List<Parkinglot> actualContent = parkinglotJsonStorage.read();

		assertEquals(lots, actualContent);
	}
}
