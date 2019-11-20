package uk.ac.ed.bikerental;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class SystemTests {
    // You can add attributes here
    public Database database;
    public Customer customer1;
    public BikeProvider bikeProvider1;

    @BeforeEach
    void setUp() throws Exception {
        // Setup mock delivery service before each tests
        DeliveryServiceFactory.setupMockDeliveryService();

        // Put your test setup here
        database = new Database();

        customer1 = new Customer();
        database.addCustomer(customer1);

        BikeType hybrid = new BikeType("hybrid");
        BikeType commute = new BikeType("commute");
        BikeType mountain = new BikeType("mountain");

        database.addBikeType(hybrid);
        database.addBikeType(commute);
        database.addBikeType(mountain);

        // included in test case result
        Bike bikeA1 = new Bike("bikeA1", hybrid);
        Booking bookingA1_1 = new Booking(new DateRange(LocalDate.of(2019, 11, 26), LocalDate.of(2019, 11, 27)));
        Booking bookingA1_2 = new Booking(new DateRange(LocalDate.of(2019, 12, 1), LocalDate.of(2019, 12, 2)));
        bikeA1.addBooking(bookingA1_1);
        bikeA1.addBooking(bookingA1_2);

        // included in test case result
        Bike bikeA2 = new Bike("bikeA2", commute);
        Booking bookingA2_1 = new Booking(new DateRange(LocalDate.of(2019, 11, 26), LocalDate.of(2019, 11, 27)));
        Booking bookingA2_2 = new Booking(new DateRange(LocalDate.of(2019, 12, 1), LocalDate.of(2019, 12, 2)));
        bikeA2.addBooking(bookingA2_1);
        bikeA2.addBooking(bookingA2_2);

        // not included in test case result because booked during dateRange
        Bike bikeA3 = new Bike("bikeA3", commute);
        Booking bookingA3_1 = new Booking(new DateRange(LocalDate.of(2019, 11, 28), LocalDate.of(2019, 11, 29)));
        bikeA3.addBooking(bookingA3_1);

        // not included in test case result because of bike type
        Bike bikeA4 = new Bike("bikeA4", mountain);
        Booking bookingA4_1 = new Booking(new DateRange(LocalDate.of(2019, 11, 26), LocalDate.of(2019, 11, 27)));
        bikeA4.addBooking(bookingA4_1);

        // included in test case result
        Location locationA = new Location("EH1AAA", "bikeProvider1");
        Collection<Bike> bikesA = new ArrayList<>();
        bikesA.add(bikeA1);
        bikesA.add(bikeA2);
        bikesA.add(bikeA3);
        bikesA.add(bikeA4);
        BikeProvider bikeProviderA = new BikeProvider(locationA, bikesA);

        database.addBikeProvider(bikeProviderA);
    }

    // TODO: Write system tests covering the three main use cases

    @Test
    void findQuoteTest() {
        DateRange dateRange = new DateRange(LocalDate.of(2019, 11, 28), LocalDate.of(2019, 11, 30));
        Location location = new Location("EH1AAA", "address1");
        Map<BikeType, Integer> numOfBikes = new HashMap<>();
        numOfBikes.put(new BikeType("hybrid"), 1);
        numOfBikes.put(new BikeType("hybrid"), 2);

        // 1 hybrid, 2 commute bikes from 28-30 Nov 2019
        Request request = new Request(dateRange, location, numOfBikes);

        Collection<Quote> quotes = customer1.getQuotes(request, database);
    }
}
