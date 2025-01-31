package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

public class LinearDeprecation implements ValuationPolicy {

    public BigDecimal calculateValue(Bike bike, LocalDate date) {

        // Period is number of years
        LocalDate today = LocalDate.now();
        Period period = Period.between(date, today);

        // Original value of the bike
        BigDecimal replacementVal = bike.getBikeType().getReplacementValue();

        // Deprecation rate chosen by the designer
        double deprecationRate = 10;
        
        // replacementVal = replacementVal - replacementVal*(deprecationRate/100)*(Num. of years)
        replacementVal =  replacementVal.subtract(
            replacementVal.multiply(BigDecimal.valueOf(deprecationRate/100)).multiply(
                BigDecimal.valueOf(  period.getYears() )));

        bike.getBikeType().setReplacementValue(replacementVal);

        return replacementVal;
    }
}
