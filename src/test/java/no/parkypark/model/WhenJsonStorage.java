package no.parkypark.model;

import no.parkypark.utils.JsonStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WhenJsonStorage {
	private static final String JSON_PARKINGLOT_FILE =
			"[" +
				"{ \"name\" : \"Oscars Parkeringsplass\", \"address\" : \"Oscarveien 14\",\"id\" : \"b5319909-1968-442c-93e9-fd08d24d985f\",\"ownerId\" : \"4\",\"price\" : 20.0}," +
				"{ \"name\" : \"Kristoffers Parkeringsplass\", \"address\" : \"Kristofferveien 14\",\"id\" : \"e32909-1968-442c-93e9-f343d985f\",\"ownerId\" : \"6\",\"price\" : 50.0}" +
			"]";

	private ArrayList<Parkinglot> expectedLots = new ArrayList<>();

	@BeforeEach
	void setUp() {
		expectedLots.add(new Parkinglot("Oscars Parkeringsplass", "Oscarveien 14", "4", 20));
		expectedLots.add(new Parkinglot("Kristoffers Parkeringsplass", "Kristofferveien 14", "6", 50));
	}

	@Test
	void readsFromFile(@TempDir Path tempDir) throws IOException {
		Path path = tempDir.resolve("parkinglots.json");
		Files.write(path, JSON_PARKINGLOT_FILE.getBytes());

		JsonStorage<Parkinglot> parkinglotJsonStorage = new JsonStorage<>(Parkinglot.class, path.toAbsolutePath().toString());
		List<Parkinglot> parkinglotsFromFile = parkinglotJsonStorage.read();

		for(int i = 0; i < expectedLots.size(); i++){
			expectedLots.get(i).setId(parkinglotsFromFile.get(i).getId());
		}

		assertEquals(parkinglotsFromFile, expectedLots);
	}

	@Test
	void writesToFile(@TempDir Path tempDir) {
		Path path = tempDir.resolve("parkinglots.json");

		JsonStorage<Parkinglot> parkinglotJsonStorage = new JsonStorage<>(Parkinglot.class, path.toAbsolutePath().toString());

		Parkinglot lot = new Parkinglot("Sigveien 14", "Sigveien 14, Trondheim", "14", 100);
		ArrayList<Parkinglot> lots = new ArrayList<>();
		lots.add(lot);

		parkinglotJsonStorage.write(lots);

		List<Parkinglot> actualContent = parkinglotJsonStorage.read();

		assertEquals(lots, actualContent);
	}
}
