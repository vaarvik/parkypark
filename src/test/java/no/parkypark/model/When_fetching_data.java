

package no.parkypark.model;

import no.parkypark.repository.ParkyparkRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.of;

public class When_fetching_data {

    private static Stream<Arguments> parkinglotDataReceived(){
        Parkinglot parkinglot = new Parkinglot("Smith", "21 2nd Street");
        return Stream.of(
                of(
                        parkinglot,"src\\test\\resources\\parkinglots.json"
                )

        );
    }

    @ParameterizedTest()
    @MethodSource("parkinglotDataReceived")
    public void parkinglot_is_received(Parkinglot expectedLots, String filePath) {

        Parkinglot resultLot = new ParkyparkRepository().readJSONFile(filePath);

        Assertions.assertEquals(expectedLots.toString(), resultLot.toString());
    }

}