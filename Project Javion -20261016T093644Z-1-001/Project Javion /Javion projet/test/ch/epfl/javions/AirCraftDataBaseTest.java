package ch.epfl.javions;
import ch.epfl.javions.aircraft.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class AirCraftDataBaseTest {
    @Test
    void IcaoResearchTest1(){
        try {
            AircraftData A = new AircraftData(new AircraftRegistration("ET-AKZ") ,new AircraftTypeDesignator("DH8B"),
                    "DE HAVILLAND DHC-8-200 Dash 8", new AircraftDescription("L2T"), WakeTurbulenceCategory.of("M"));
            assertEquals(A,  new AircraftDatabase("/aircraft.zip").get(new IcaoAddress("04000D")));
        }catch (IOException e){
            System.out.println("error " + e);
        }
    }
    @Test
    void IcaoResearchTest2(){
        try {
            AircraftData A = new AircraftData(new AircraftRegistration("230921") ,new AircraftTypeDesignator("P8"),
                    "BOEING P-8 Poseidon", new AircraftDescription("L2J"), WakeTurbulenceCategory.of("M"));
            assertEquals(A,  new AircraftDatabase("/aircraft.zip").get(new IcaoAddress("71F00D")));
        }catch (IOException e){
            System.out.println("error " + e);
        }
    }
    @Test
    void NoIcaoFoundTest(){
        try {
            AircraftData A = new AircraftData(new AircraftRegistration("ET-AKZ") ,new AircraftTypeDesignator("DH8B"),
                    "DE HAVILLAND DHC-8-200 Dash 8", new AircraftDescription("L2T"), WakeTurbulenceCategory.of("M"));
            assertNull(new AircraftDatabase("/aircraft.zip").get(new IcaoAddress("FFFFFF")));
        }catch (IOException e){System.out.println("error : " + e);}
    }
    @Test
    void InvalidPathTest(){
         assertThrows(NullPointerException.class, () -> new AircraftDatabase(null));
    }

}
