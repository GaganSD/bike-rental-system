package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LinearDeprecation implements ValuationPolicy {

    public BigDecimal calculateValue(Bike bike, LocalDate date) {

        int deprecationRate = 20;

        LocalDate today = LocalDate.now();

        return new BigDecimal(10);
    }
}
