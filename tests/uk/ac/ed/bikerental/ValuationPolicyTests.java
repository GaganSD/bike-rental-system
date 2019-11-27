package uk.ac.ed.bikerental;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.*;

public class ValuationPolicyTests {
    // You can add attributes here

    @BeforeEach
    void setUp() throws Exception {
        BikeType bikeType1 = BikeType("MountainBike", 900);
        Bike testBike1 = new Bike("100", bikeType1);

        LinearDeprecation ld1 = new LinearDeprecation();
        DoubleDecliningBalanceDepreciation dd1 = new DoubleDecliningBalanceDepreciation();
        LocalDate date1 = 2016-03-01;
    }
    
    @Test
    public void TestLinearDeprecation() {
        BigDecimal output1 = ld1.calculateValue(testBike1, date1);
        assertEquals(630, output1);
    }

    @Test
    public void TestDoubleDecliningBalanceDepreciation() {
        BigDecimal output1 = dd1.calculateValue(testBike1, date1);
        assertEquals(460.8, output1);

    }
}
