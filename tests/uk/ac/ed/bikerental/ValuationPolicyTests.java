package uk.ac.ed.bikerental;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.*;

public class ValuationPolicyTests {
    // You can add attributes here
    
    BikeType bikeType1;
    Bike testBike1;
    LocalDate date1;
    LinearDeprecation ld1;
    DoubleDecliningBalanceDepreciation dd1;


    @BeforeEach
    void setUp() throws Exception {

        bikeType1 = new BikeType("MountainBike", new BigDecimal(900));
        testBike1 = new Bike("100", bikeType1);

        ld1 = new LinearDeprecation();
        dd1 = new DoubleDecliningBalanceDepreciation();
        date1 = LocalDate.of(2016,03,01);
    }
    
    @Test
    public void TestLinearDeprecation() {
        BigDecimal output1 = ld1.calculateValue(testBike1, date1);
        assertEquals(new BigDecimal(630).stripTrailingZeros(), output1.stripTrailingZeros());
    }

    @Test
    public void TestDoubleDecliningBalanceDepreciation() {
        BigDecimal output1 = dd1.calculateValue(testBike1, date1);
        assertEquals(BigDecimal.valueOf(460.800).stripTrailingZeros(), output1.stripTrailingZeros());

    }
}
