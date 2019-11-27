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

        // 
        double deprecationRate = 3;
        
        replacementVal =  replacementVal - replacementVal.multiply(BigDecimal.valueOf(deprecationRate/100)).multiply(BigDecimal.valueOf(period.getYears()));

        bike.getBikeType().setReplacementValue(replacementVal);

        



        return new BigDecimal(10);
    }
}
