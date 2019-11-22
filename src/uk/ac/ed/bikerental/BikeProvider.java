package uk.ac.ed.bikerental;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BikeProvider {
    private String uuid;
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
    public BikeProvider(Location location) {
        this.uuid = UUID.randomUUID().toString();
        this.location = location;
    }

    public String getUUID() {
        return this.uuid;
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

    public void registerBikeReturn(String bookingID) {
        // get first 36 characters
        String bikeProviderUUID = bookingID.substring(0, 35);

        // returned to original bike provider
        if (bikeProviderUUID == this.uuid) {
            for (Bike bike : bikes) {
                bike.setStatus(BikeStatus.IN_STORE);
            }
            return;
        }

        // returned to partner bike provider
        for (BikeProvider partner : partners) {
            if (bikeProviderUUID == partner.getUUID()) {
                DeliveryService deliveryService = DeliveryServiceFactory.getDeliveryService();

                for (Bike bike : partner.getBikes()) {
                    bike.setStatus(BikeStatus.IN_PARTNER_STORE);
                    deliveryService.scheduleDelivery(bike, this.location, partner.location, LocalDate.now());
                }
                return;
            }
        }

        assert (false);
    }
}