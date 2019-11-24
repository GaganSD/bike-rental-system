package uk.ac.ed.bikerental;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Objects;

public class Bike implements Deliverable {
    private String serialNo;
    private BikeStatus status;
    private BikeType bikeType;
    private Collection<Booking> bookings = new ArrayList<>();

    public Bike(String serialNo, BikeType bikeType) {
        this.serialNo = serialNo;
        this.bikeType = bikeType;
        this.status = BikeStatus.IN_STORE;
    }

    public BikeStatus getStatus() {
        return this.status;
    }

    public void setStatus(BikeStatus status) {
        this.status = status;
    }

    public BikeType getBikeType() {
        return this.bikeType;
    }

    public Collection<Booking> getBookings() {
        return this.bookings;
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public Boolean isAvailable(DateRange dateRange) {
        for (Booking booking : bookings) {
            if (dateRange.overlaps(booking.getDateRange()))
                return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return this.serialNo;
    }

    @Override
    public void onPickup() {
        this.status = BikeStatus.OUT_FOR_DELIVERY;
    }

    @Override
    public void onDropoff() {
        this.status = BikeStatus.IN_STORE;
    }

    @Override
    public boolean equals(Object obj) {
        // equals method for testing equality in tests
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Bike other = (Bike) obj;
        return serialNo.equals(other.serialNo);
    }
}