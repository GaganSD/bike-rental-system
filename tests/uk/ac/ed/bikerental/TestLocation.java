package uk.ac.ed.bikerental;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class TestLocation {
    Location location1;
    Location location2;
    Location location3;

    @BeforeEach
    void setUp() throws Exception {
        location1 = new Location("EH1AAA", "address1");
        location2 = new Location("EH2AAA", "address2");
        location3 = new Location("EF3AAA", "address3");
    }

    @Test
    void testGetPostcode() {
        assert (location1.getPostcode() == "EH1AAA");
    }

    @Test
    void testGetAddress() {
        assert (location1.getAddress() == "address1");
    }

    @Test
    void testIsNearToTrue() {
        assert (location1.isNearTo(location2));
        assert (location2.isNearTo(location1));
    }

    @Test
    void testIsNearToFalse() {
        assert (!location2.isNearTo(location3));
        assert (!location3.isNearTo(location2));
    }
}
