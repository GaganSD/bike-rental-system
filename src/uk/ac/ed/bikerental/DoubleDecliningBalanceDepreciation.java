package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

public class DoubleDecliningBalanceDepreciation implements ValuationPolicy {

    public BigDecimal calculateValue(Bike bike, LocalDate date) {


        // Period is number of years
        LocalDate today = LocalDate.now();
        Period period = Period.between(date, today);

        // Original value of the bike
        BigDecimal replacementVal = bike.getBikeType().getReplacementValue();

        // Deprecation rate chosen by the designer
        double deprecationRate = 10;
        
        // replacementVal = replacementVal*(1 - 2*0.1)^3 
        replacementVal =  replacementVal.multiply(  
                BigDecimal.valueOf(1-2*deprecationRate/100).pow(period.getYears()) );
                

        bike.getBikeType().setReplacementValue(replacementVal);

        return replacementVal;

    }
}