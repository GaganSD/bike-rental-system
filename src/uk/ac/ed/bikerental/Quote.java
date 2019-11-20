package uk.ac.ed.bikerental;

import java.util.Collection;
import java.util.ArrayList;

public class Quote {

    public BikeProvider bikeProvider;
    public Collection<Bike> bikes = new ArrayList<>();
    public Integer totalPrice;
    public Integer totalDeposit;

    public Quote(BikeProvider bikeProvider, Collection<Bike> bikes, Integer totalPrice, Integer totalDeposit) {
        this.bikeProvider = bikeProvider;
        this.bikes = bikes;
        this.totalPrice = totalPrice;
        this.totalDeposit = totalDeposit;
    }

}