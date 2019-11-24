package uk.ac.ed.bikerental;

import java.util.Collection;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Quote {
    private BikeProvider bikeProvider;
    private Collection<Bike> selectedBikes = new ArrayList<>();
    private DateRange dateRange;
    private BigDecimal totalPrice;
    private BigDecimal totalDeposit;

    public Quote(BikeProvider bikeProvider, Collection<Bike> selectedBikes, DateRange dateRange, BigDecimal totalPrice,
            BigDecimal totalDeposit) {
        this.bikeProvider = bikeProvider;
        this.selectedBikes = selectedBikes;
        this.dateRange = dateRange;
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

    public BigDecimal getTotalPrice() {
        return this.totalPrice;
    }

    public BigDecimal getTotalDeposit() {
        return this.totalDeposit;
    }
}