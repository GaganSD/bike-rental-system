package uk.ac.ed.bikerental;

import java.time.LocalDate;
import java.util.Map;

public class Request {
    private DateRange dateRange;
    private Location location;
    private Map<String, Integer> numOfBikes;

    public Request(DateRange dateRange, Location location, Map<String, Integer> numOfBikes) {
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

    public Map<String, Integer> getNumOfBikes() {
        return this.numOfBikes;
    }

    // this method is not need according to coursework 3
    public void extendRange(Integer days) {
        return;
    }
}