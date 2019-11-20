package uk.ac.ed.bikerental;

import java.time.LocalDate;
import java.util.Map;

public class Request {
    private DateRange dateRange;
    private Location location;
    private Map<BikeType, Integer> numOfBikes;

    public Request(DateRange dateRange, Location location, Map<BikeType, Integer> numOfBikes) {
        this.dateRange = dateRange;
        this.location = location;
        this.numOfBikes = numOfBikes;
    }

    public DateRange getDateRange() {
        return this.dateRange;
    }

    public Location getLocation() {
        return this.location;
    }

    public Map<BikeType, Integer> getNumOfBikes() {
        return this.numOfBikes;
    }

    public void extendRange(Integer days) {
        // TODO: implement this method

        return;
    }
}