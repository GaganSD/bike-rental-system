package uk.ac.ed.bikerental;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Objects;

public class Bike implements Deliverable {
    private String serialNo;
    private BikeStatus status;
    private BikeType bikeType;
    private Collection<Booking> bookings = new ArrayList<>();

    /**
     * constructor for our Bike class
     * 
     * @param serialNo Unique Serial number for the bike
     * @param bikeType Type of the bike object. i.e. Mountain Bike, dirt bike
     */
    public Bike(String serialNo, BikeType bikeType) {
        this.serialNo = serialNo;
        this.bikeType = bikeType;
        this.status = BikeStatus.IN_STORE;
    }

    /**
     * getter for status attribute
     * 
     * @return Bike's status
     */
    public BikeStatus getStatus() {
        return this.status;
    }

    /**
     * setter for status attribute
     * 
     * @param status current status of the bike
     */
    public void setStatus(BikeStatus status) {
        this.status = status;
    }

    /**
     * getter for the BikeType attribute
     * 
     * @return Bike's bike type
     **/
    public BikeType getBikeType() {
        return this.bikeType;
    }

    /**
     * getter for the bookings attribute
     * 
     * @return collection of Booking i.e. bookings.
     **/
    public Collection<Booking> getBookings() {
        return this.bookings;
    }

    /**
     * Add a booking to the system.
     * 
     * @param booking Booking
     **/
    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    /**
     * Checks if the given date range is available.
     * 
     * @param dateRange of type DateRange.
     * @return boolean value.
     **/
    public Boolean isAvailable(DateRange dateRange) {
        for (Booking booking : bookings) {
            if (dateRange.overlaps(booking.getDateRange()))
                return false;
        }

        return true;
    }


    /**
     * Converts serial number to string
     * 
     * @return String form of the serial number.
     **/
    @Override
    public String toString() {
        return this.serialNo;
    }

    /**
     * Changes the status of the bike to OUT_FOR_DELIVERY
     * 
     **/
    @Override
    public void onPickup() {
        this.status = BikeStatus.OUT_FOR_DELIVERY;
    }

    /**
     * Changes the status of the bike to IN_STORE
     * 
     **/
    @Override
    public void onDropoff() {
        this.status = BikeStatus.IN_STORE;
    }


    /**
     * Changes the status of the bike to OUT_FOR_DELIVERY
     * @param obj 
     * @return boolean
     **/
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