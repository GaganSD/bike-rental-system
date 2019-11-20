package uk.ac.ed.bikerental;

import java.util.Collection;

public class Quote {

    BikeProvider bProvider;
    Collection<Bike> bikes;
    Integer totalPrice;
    Integer totalDeposit;

    public Quote(BikeProvider bProvider, Collection<Bike> bikes, 
                Integer totalPrice, Integer totalDeposit) {
        
        this.bProvider = bProvider;
        this.bikes = bikes;
        this.totalPrice = totalPrice;
        this.totalDeposit = totalDeposit;
    }


    
}