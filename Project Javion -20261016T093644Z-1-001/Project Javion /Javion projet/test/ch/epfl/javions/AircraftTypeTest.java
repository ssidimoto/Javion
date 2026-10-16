package ch.epfl.javions;


import ch.epfl.javions.aircraft.AircraftDescription;
import ch.epfl.javions.aircraft.AircraftRegistration;
import ch.epfl.javions.aircraft.AircraftTypeDesignator;
import ch.epfl.javions.aircraft.IcaoAddress;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class AircraftTypeTest {
    @Test
    void validArgumentAircraftTypeDesignator(){
        new AircraftTypeDesignator("");
    }

    @Test
    void invalidArgumentAircraftRegistration(){
        assertThrows(IllegalArgumentException.class, () -> new AircraftRegistration(""));
    }

    @Test
    void invalidArgumentAircraftDescription(){
        new AircraftDescription("");
    }

    @Test
    void validArgumentIcaoAddress(){
        assertThrows(IllegalArgumentException.class, () -> new IcaoAddress(""));
    }
}
