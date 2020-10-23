package no.parkypark.model;

import no.parkypark.repository.ParkyparkRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.of;

public class WhenFetchingData {

    private static Stream<Arguments> parkinglotsDataReceived(){

        //create an array with the expected data
        List<Parkinglot> parkinglots = new ArrayList<>();
        parkinglots.add(new Parkinglot("Smith", "21 2nd Street"));
        parkinglots.add(new Parkinglot("John", "Matrix Street"));

        return Stream.of(
                of(
                        parkinglots,"src\\test\\resources\\parkinglots.json"
                )
        );
    }

    @ParameterizedTest()
    @MethodSource("parkinglotsDataReceived")
    public void parkinglotsAreReceived(List<Parkinglot> expectedLots, String filePath) {

        List<Parkinglot> resultLots = new ParkyparkRepository(filePath).getAllParkinglots();

        //loop through all the objects and confirm that the file contains the same information as the expected data
        for (int i=0; i<expectedLots.size(); i++){
            //compare the name
            Assertions.assertEquals(expectedLots.get(i).getName(), resultLots.get(i).getName());
            //compare the address
            Assertions.assertEquals(expectedLots.get(i).getAddress(), resultLots.get(i).getAddress());
        }
    }

}