package no.parkypark.model;

import no.parkypark.repository.ParkinglotsRepository;
import no.parkypark.utils.GetStaticID;
import no.parkypark.utils.JSONFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class WhenUpdatingParkinglot {	//create a static array with IDs for the userIds
    private static GetStaticID idRepo = new GetStaticID();

    //Responsible for generating the data in the JSON file which the expected data needs to be based on
    private JSONFile file = new JSONFile(idRepo, "src\\test\\resources\\parkinglots.json");

    private List<Parkinglot> expectedLots;

    private void setUpExpectedParkinglots() {
        expectedLots = new ArrayList<>();
        expectedLots.add(new Parkinglot("Smith", "21 2nd Street", idRepo.getStaticId(0, true), 20));
        expectedLots.add(new Parkinglot("John", "Matrix Street", idRepo.getStaticId(1, true), 20));
    }

    @BeforeEach
    private void setup() {
        setUpExpectedParkinglots();

        //clean the JSON file to have the exact content that is passed in the writer.write method
        file.prepare();
    }

    @Test
    public void parkinglotIsUpdatedAndAddedToJSON() {
        expectedLots.set(0, new Parkinglot("Best Spot!", "Planetroad 2", idRepo.getStaticId(0), 50));

        ParkinglotsRepository repo = new ParkinglotsRepository(file.getFile());

        //set repo
        Map<String, List<String>> changes = new HashMap<>();
        changes.put("name", Collections.singletonList("Best Spot!"));
        changes.put("address", Collections.singletonList("Planetroad 2"));
        changes.put("price", Collections.singletonList("50"));
        changes.put("id", Collections.singletonList(file.getUserId(0)));
        changes.put("userid", Collections.singletonList(idRepo.getStaticId(0)));

        repo.updateParkinglot(changes);

        List<Parkinglot> resultLots = repo.getAllParkinglots();

        //loop through all the objects and confirm that the file contains the same information as the expected data
        for (int i=0; i<expectedLots.size(); i++){
            //compare the name
            assertEquals(expectedLots.get(i).getName(), resultLots.get(i).getName());
            //compare the address
            assertEquals(expectedLots.get(i).getAddress(), resultLots.get(i).getAddress());
            //check that there is an ID
            assertNotNull(resultLots.get(i).getId());
            //check that the user IDs match
            assertEquals(expectedLots.get(i).getUserId(), resultLots.get(i).getUserId());
        }
    }
}
