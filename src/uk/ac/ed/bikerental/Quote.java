package uk.ac.ed.bikerental;

import java.util.Collection;
import java.util.ArrayList;

public class Quote {
    private BikeProvider bikeProvider;
    private Collection<Bike> selectedBikes = new ArrayList<>();
    private DateRange dateRange;
    private Integer totalPrice;
    private Integer totalDeposit;

    public Quote(BikeProvider bikeProvider, Collection<Bike> selectedBikes, Integer totalPrice, Integer totalDeposit) {
        this.bikeProvider = bikeProvider;
        this.selectedBikes = selectedBikes;
        this.totalPrice = totalPrice;
        this.totalDeposit = totalDeposit;
    }

    public BikeProvider getBikeProvider() {
        return this.bikeProvider;
    }

    public Collection<Bike> getSelectedBikes() {
        return this.selectedBikes;
    }

    public DateRange getDateRange() {
        return this.dateRange;
    }
}