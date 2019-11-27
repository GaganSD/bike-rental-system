package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

public class LinearDeprecation implements ValuationPolicy {

    public BigDecimal calculateValue(Bike bike, LocalDate date) {

        LocalDate today = LocalDate.now();
        Period period = Period.between(date, today);

        // Original value of the bike
        BigDecimal replacementVal = bike.getBikeType().getReplacementValue();

        // Deprication re
        BigDecimal deprecationRate = 3;
        
        replacementVal =  replacementVal - replacementVal*(deprecationRate/100)*period.getYears();

        bike.getBikeType().setReplacementValue(replacementVal);

        



        return new BigDecimal(10);
    }
}
