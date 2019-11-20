package uk.ac.ed.bikerental;

import java.util.ArrayList;
import java.util.Map;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

public class Customer {
    private String uuid;

    public Customer() {
        this.uuid = UUID.randomUUID().toString();
    }

    public String getUuid() {
        return this.uuid;
    }

    /**
     * Used to generate relevant quotes based on customer requirements
     * 
     * @param req stores customer reqs
     * @return relevant quotes to show customer
     */
    public Collection<Quote> getQuotes(Request request, Database db) {

        Collection<Quote> quotes = new ArrayList<>();
        Collection<BikeProvider> bikeProviders = db.getBikeProviders();

        for (BikeProvider bikeProvider : bikeProviders) {

            if (bikeProvider.getLocation().isNearTo(request.getLocation())) {

                Collection<Bike> bikes = bikeProvider.getBikes();
                Collection<Bike> availableBikes = new ArrayList<>();

                // to keep track of whether bike provider has all bikes required
                Map<BikeType, Integer> numOfBikesRequired = request.getNumOfBikes();

                for (Bike bike : bikes) {
                    if (bike.isAvailable(request.getDateRange())) {
                        BikeType bikeType = bike.getBikeType();

                        // how many bikes of this type does customer need
                        int bikesRequired = numOfBikesRequired.get(bikeType);
                        if (bikesRequired > 0) {
                            availableBikes.add(bike);

                            // decrement num of bikes required of this type
                            numOfBikesRequired.put(bikeType, bikesRequired - 1);
                        }
                    }
                }

                // check if all required bike types is set to zero i.e. reqs are met
                boolean hasAllRequiredBikes = Collections.frequency(numOfBikesRequired.values(),
                        0) == numOfBikesRequired.size();

                if (hasAllRequiredBikes) {
                    // TODO: Calculate totalPrice, totalDeposit
                    Quote quote = new Quote(bikeProvider, availableBikes, 0, 0);
                    quotes.add(quote);
                }
            }
        }

        return quotes;
    }
}