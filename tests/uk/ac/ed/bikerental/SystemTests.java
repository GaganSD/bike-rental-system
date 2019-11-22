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
    public BikeProvider bikeProviderA;

    public BikeType hybrid = new BikeType("hybrid");
    public BikeType commute = new BikeType("commute");
    public BikeType mountain = new BikeType("mountain");

    @BeforeEach
    void setUp() throws Exception {
        // Setup mock delivery service before each tests
        DeliveryServiceFactory.setupMockDeliveryService();

        // Put your test setup here
        database = new Database();

        // Mock data setup
        customer1 = new Customer();
        database.addCustomer(customer1);

        CustomerBookingInfo bookingInfo1 = new CustomerBookingInfo("name", "phone", new Location("EH1AAA", "address1"),
                "surname", DeliveryMethod.DELIVERY_DRIVER, DeliveryMethod.CUSTOMER);

        database.addBikeType(hybrid);
        database.addBikeType(commute);
        database.addBikeType(mountain);

        BikeProvider bikeProviderA = new BikeProvider(new Location("EH2AAA", "addressA"));

        // BikeProviderA bikes
        // Hybrid booked during 26-27 Nov
        Bike bikeA1 = new Bike("bikeA1", hybrid);
        Booking bookingA1_1 = new Booking(bikeProviderA.getUUID(), bookingInfo1,
                new DateRange(LocalDate.of(2019, 11, 26), LocalDate.of(2019, 11, 27)));
        bikeA1.addBooking(bookingA1_1);

        // Commute booked during 1-2 Dec
        Bike bikeA2 = new Bike("bikeA2", commute);
        Booking bookingA2_1 = new Booking(bikeProviderA.getUUID(), bookingInfo1,
                new DateRange(LocalDate.of(2019, 12, 1), LocalDate.of(2019, 12, 2)));
        bikeA2.addBooking(bookingA2_1);

        // Commute booked during 3-4 Dec
        Bike bikeA3 = new Bike("bikeA3", commute);
        Booking bookingA3_1 = new Booking(bikeProviderA.getUUID(), bookingInfo1,
                new DateRange(LocalDate.of(2019, 12, 3), LocalDate.of(2019, 12, 3)));
        bikeA3.addBooking(bookingA3_1);

        // Mountain
        Bike bikeA4 = new Bike("bikeA4", mountain);

        bikeProviderA.addBike(bikeA1);
        bikeProviderA.addBike(bikeA2);
        bikeProviderA.addBike(bikeA3);
        bikeProviderA.addBike(bikeA4);
        database.addBikeProvider(bikeProviderA);

        BikeProvider bikeProviderB = new BikeProvider(new Location("EH2AAA", "addressA"));

        // BikeProviderB bikes
        // Hybrid booked during 26-27 Nov
        Bike bikeB1 = new Bike("bikeB1", hybrid);
        Booking bookingB1_1 = new Booking(bikeProviderB.getUUID(), bookingInfo1,
                new DateRange(LocalDate.of(2019, 11, 26), LocalDate.of(2019, 11, 27)));
        bikeB1.addBooking(bookingB1_1);

        // Commute booked during 1-2 Dec
        Bike bikeB2 = new Bike("bikeB2", commute);
        Booking bookingB2_1 = new Booking(bikeProviderB.getUUID(), bookingInfo1,
                new DateRange(LocalDate.of(2019, 12, 1), LocalDate.of(2019, 12, 2)));
        bikeB2.addBooking(bookingB2_1);

        bikeProviderB.addBike(bikeB1);
        bikeProviderB.addBike(bikeB2);
        database.addBikeProvider(bikeProviderB);

        // In different location than the customer
        BikeProvider bikeProviderC = new BikeProvider(new Location("GG2AAA", "addressA"));

        // BikeProviderC bikes
        // Hybrid booked during 26-27 Nov
        Bike bikeC1 = new Bike("bikeB1", hybrid);

        bikeProviderC.addBike(bikeC1);
        database.addBikeProvider(bikeProviderC);
    }

    @Test
    void getQuotesTest() {
        DateRange dateRange = new DateRange(LocalDate.of(2019, 11, 28), LocalDate.of(2019, 11, 30));
        Location location = new Location("EH1AAA", "address1");

        Map<String, Integer> numOfBikes = new HashMap<>();
        numOfBikes.put(hybrid.toString(), 1);
        numOfBikes.put(commute.toString(), 2);

        Request request = new Request(dateRange, location, numOfBikes);

        Collection<Quote> quotes = customer1.getQuotes(request, database);

        assert (quotes.size() == 1);
    }
}
