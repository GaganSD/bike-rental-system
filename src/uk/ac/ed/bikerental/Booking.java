package uk.ac.ed.bikerental;

import java.util.UUID;

public class Booking {
    private String uniqueID;
    private String summary;
    private int totalDeposit;
    private int totalPrice;
    private String CollectionMethod;
    private String returnMethod;
    private DateRange dateRange;

    public Booking(String bikeProivderUUID, CustomerBookingInfo bookingInfo, DateRange dateRange) {
        this.uniqueID = bikeProivderUUID + UUID.randomUUID().toString();
        this.dateRange = dateRange;
    }

    public String getUniqueID() {
        return this.uniqueID;
    }

    public DateRange getDateRange() {
        return this.dateRange;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Booking other = (Booking) obj;
        return uniqueID.equals(other.getUniqueID());
    }
}