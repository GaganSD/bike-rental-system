package uk.ac.ed.bikerental;

import java.util.Collection;
import java.util.ArrayList;

public class Bike {
    private String serialNo;
    private BikeType bikeType;
    private Collection<Booking> bookings = new ArrayList<>();

    public Bike(String serialNo, BikeType bikeType) {
        this.serialNo = serialNo;
        this.bikeType = bikeType;
    }

    public BikeType getBikeType() {
        return this.bikeType;
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public Boolean isAvailable(DateRange dr) {
        return false;
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
        return Objects.equals(serialNo, other.serialNo);
    }

}