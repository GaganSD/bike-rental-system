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

    public Booking(DateRange dateRange) {
        this.uniqueID = UUID.randomUUID().toString();
    }
}