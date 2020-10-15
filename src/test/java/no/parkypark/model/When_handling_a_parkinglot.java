package no.parkypark.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.of;

public class When_handling_a_parkinglot {

    private static Stream<Arguments> parkinglotChanges() {
        return Stream.of(
                of(
                        new Parkinglot("Jans parkeringsplass", "Byåsen, Trondheim"),
                        new Parkinglot("Jans parkering", "Byåsen")
                )
        );
    }

    @ParameterizedTest()
    @MethodSource("parkinglotChanges")
    public void parkinglot_is_changed(Parkinglot expectedLot, Parkinglot inputLot) {

        inputLot.setName(expectedLot.getName());
        inputLot.setAddress(expectedLot.getAddress());

        Assertions.assertEquals(expectedLot.getName(), inputLot.getName());
        Assertions.assertEquals(expectedLot.getAddress(), inputLot.getAddress());
    }
}
