package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

public class Customer {
    private String uuid;
    private Collection<Booking> bookings = new ArrayList<>();

    public Customer() {
        this.uuid = UUID.randomUUID().toString();
    }

    public String getUuid() {
        return this.uuid;
    }

    public Collection<Booking> getBookings() {
        return this.bookings;
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
                Map<String, Integer> numOfBikesRequired = new HashMap<>(request.getNumOfBikes());

                BigDecimal totalPrice = new BigDecimal(0);
                BigDecimal totalDeposit = new BigDecimal(0);

                long totalDays = request.getDateRange().toDays() + 1;

                for (Bike bike : bikes) {
                    if (bike.isAvailable(request.getDateRange())) {
                        BikeType bikeType = bike.getBikeType();

                        // how many bikes of this type does customer need
                        int bikesRequired = numOfBikesRequired.getOrDefault(bikeType.toString(), 0);

                        if (bikesRequired > 0) {
                            availableBikes.add(bike);

                            // add to totalPrice and totalDeposit
                            BigDecimal dailyRentalPrice = bikeProvider.getRentalPrice(bikeType);
                            BigDecimal totalRentalPrice = dailyRentalPrice.multiply(new BigDecimal(totalDays));

                            totalPrice = totalPrice.add(totalRentalPrice);

                            // use BigDecimal.valueOf() for constructing from doubles
                            BigDecimal deposit = bikeType.getReplacementValue()
                                    .multiply(BigDecimal.valueOf(bikeProvider.getDepositRate() / 100));
                            totalDeposit = totalDeposit.add(deposit);

                            // decrement num of bikes required of this type
                            numOfBikesRequired.put(bikeType.toString(), bikesRequired - 1);
                        }
                    }
                }

                // check if all required bike types is set to zero i.e. reqs are met
                boolean hasAllRequiredBikes = Collections.frequency(numOfBikesRequired.values(),
                        0) == numOfBikesRequired.size();
                if (hasAllRequiredBikes) {
                    Quote quote = new Quote(bikeProvider, availableBikes, request.getDateRange(), totalPrice,
                            totalDeposit);
                    quotes.add(quote);
                }
            }
        }

        return quotes;
    }

    public Booking bookQuote(CustomerBookingInfo bookingInfo, Quote quote) {
        BikeProvider bikeProvider = quote.getBikeProvider();

        Booking newBooking = new Booking(bikeProvider.getUUID(), bookingInfo, quote.getDateRange());

        DeliveryService deliveryService = DeliveryServiceFactory.getDeliveryService();

        for (Bike bike : bikeProvider.getBikes()) {
            for (Bike selectedBike : quote.getSelectedBikes()) {
                if (bike.equals(selectedBike)) {
                    bike.addBooking(newBooking);

                    Location bikeProviderLocation = bikeProvider.getLocation();
                    Location customerLocation = bookingInfo.getLocation();

                    if (bookingInfo.getCollectionMethod() == DeliveryMethod.DELIVERY_DRIVER) {
                        deliveryService.scheduleDelivery(bike, bikeProviderLocation, customerLocation,
                                quote.getDateRange().getStart());
                    }

                    if (bookingInfo.getReturnMethod() == DeliveryMethod.DELIVERY_DRIVER) {
                        deliveryService.scheduleDelivery(bike, customerLocation, bikeProviderLocation,
                                newBooking.getDateRange().getStart());
                    }
                }
            }
        }

        this.bookings.add(newBooking);
        return newBooking;
    }
}