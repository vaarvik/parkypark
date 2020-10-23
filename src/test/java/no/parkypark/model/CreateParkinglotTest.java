package no.parkypark.model;

import no.parkypark.repository.ParkyparkRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sun.jvm.hotspot.utilities.Assert;

import java.util.ArrayList;
import java.util.List;

public class CreateParkinglotTest {
	@Test
	public void adressIsNull() {
		ParkyparkRepository repo = new ParkyparkRepository(new ArrayList<>());
		Assertions.assertThrows(Exception.class, ()->repo.createParkinglot("Hedda",null));
	}

	@Test
	public void nameIsNull() {
		ParkyparkRepository repo = new ParkyparkRepository(new ArrayList<>());
		Assertions.assertThrows(Exception.class, ()->repo.createParkinglot(null,"adress"));
	}

	@Test
	public void returnsParkinglotOnSuccess() throws Exception {
		ParkyparkRepository repo = new ParkyparkRepository(new ArrayList<>());
		Parkinglot lot = repo.createParkinglot("Hedda","adress");

		Assertions.assertEquals("Hedda", lot.getName());
		Assertions.assertEquals("adress", lot.getAddress());
		Assertions.assertNotNull(lot.getId());
	}

	@Test
	public void newParkinglotIsAddedToRepo() throws Exception {
		ParkyparkRepository repo = new ParkyparkRepository(new ArrayList<>());
		Parkinglot lot = repo.createParkinglot("Hedda","adress");
		Parkinglot lotFromRepo = repo.getParkinglotById(lot.getId());

		Assertions.assertEquals(1, repo.getAllParkinglots().size());
		Assertions.assertEquals("Hedda", lotFromRepo.getName());
		Assertions.assertEquals("adress", lotFromRepo.getAddress());
		Assertions.assertNotNull(lotFromRepo.getId());
	}
}
