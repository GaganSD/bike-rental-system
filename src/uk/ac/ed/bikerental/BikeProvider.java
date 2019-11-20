package uk.ac.ed.bikerental;

import java.util.Collection;
import java.util.Map;

public class BikeProvider {

    String name;
    String location;
    String phoneNumber;
    //String location;
    String openingHours;
    Collection<BikeProvider> partners;
    Collection<DeliveryDriver> drivers;
    Map<BikeType, Integer> rentalPrice;
    Map<BikeType, Map<String, Integer>> depositPolicy;

    public void registerOriginalBikeReturn(String serialNo) {
        //TODO: implement this method

        return;
    }

    public void registerPartnerBikeReturn(String serialNo) {
        //TODO: implement this method

        return;
    }



}