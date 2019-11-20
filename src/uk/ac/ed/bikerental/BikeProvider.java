package uk.ac.ed.bikerental;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BikeProvider {

    private String name;
    private Location location;
    private String phoneNumber;
    private String openingHours;
    private Collection<Bike> bikes = new ArrayList<>();
    private Collection<BikeProvider> partners = new ArrayList<>();
    // private Collection<DeliveryDriver> drivers;
    private Map<BikeType, Integer> rentalPrice = new HashMap<>();
    private Map<BikeType, Map<String, Integer>> depositPolicy = new HashMap<>();

    // constructor with only fields relevant to testing
    public BikeProvider(Location location, Collection<Bike> bikes) {
        this.location = location;
        this.bikes = bikes;
    }

    public Location getLocation() {
        return this.location;
    }

    public Collection<Bike> getBikes() {
        return this.bikes;
    }

    public void addBike(Bike bike) {
        bikes.add(bike);
    }

    public void registerOriginalBikeReturn(String serialNo) {
        // TODO: implement this method

        return;
    }

    public void registerPartnerBikeReturn(String serialNo) {
        // TODO: implement this method

        return;
    }

}