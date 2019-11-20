package uk.ac.ed.bikerental;

import java.time.LocalDate;
import java.util.Map;

public class Request {

    LocalDate collectionDate;
    LocalDate returnDate;
    String location;
    Map<BikeType, Integer> numOfBikes;

    public void extendRange(Integer days) {
        // TODO: implement this method



        return;
    }
}