package uk.ac.ed.bikerental;

import java.math.BigDecimal;
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
    private Double depositRate;
    private String phoneNumber;
    private String openingHours;
    private Collection<Bike> bikes = new ArrayList<>();
    private Collection<BikeProvider> partners = new ArrayList<>();
    private Map<String, BigDecimal> bikeTypeRentalPrice = new HashMap<>();

    // constructor with only fields relevant to testing
    public BikeProvider(Location location, Double depositRate) {
        this.uuid = UUID.randomUUID().toString();
        this.location = location;
        this.depositRate = depositRate;
    }

    public String getUUID() {
        return this.uuid;
    }

    public Location getLocation() {
        return this.location;
    }

    public Double getDepositRate() {
        return this.depositRate;
    }

    public Collection<Bike> getBikes() {
        return this.bikes;
    }

    public void addBike(Bike bike) {
        bikes.add(bike);
    }

    public void addPartner(BikeProvider partner) {
        this.partners.add(partner);
        partner.partners.add(this);
    }

    public BigDecimal getRentalPrice(BikeType bikeType) {
        return bikeTypeRentalPrice.get(bikeType.toString());
    }

    public void setRentalPrice(BikeType bikeType, BigDecimal rentalPrice) {
        bikeTypeRentalPrice.put(bikeType.toString(), rentalPrice);
    }

    public void registerBikeReturn(String bookingUniqueID) {
        // get first 36 characters
        String bikeProviderUUID = bookingUniqueID.substring(0, 36);

        // returned to original bike provider
        if (bikeProviderUUID.equals(this.uuid)) {
            for (Bike bike : bikes) {
                for (Booking booking : bike.getBookings()) {
                    if (booking.getUniqueID().substring(36, 64) == bookingUniqueID.substring(36, 64)) {
                        bike.setStatus(BikeStatus.IN_STORE);
                    }
                }
            }
            return;
        }

        DeliveryService deliveryService = DeliveryServiceFactory.getDeliveryService();

        // returned to partner bike provider
        for (BikeProvider partner : partners) {
            if (bikeProviderUUID.equals(partner.getUUID())) {
                for (Bike bike : partner.getBikes()) {
                    for (Booking booking : bike.getBookings()) {
                        if (booking.getUniqueID().substring(36, 64).equals(bookingUniqueID.substring(36, 64))) {
                            bike.setStatus(BikeStatus.IN_PARTNER_STORE);
                            deliveryService.scheduleDelivery(bike, this.location, partner.location, LocalDate.now());

                        }
                    }
                }
                return;
            }
        }
    }
}