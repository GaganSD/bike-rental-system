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
    public CustomerBookingInfo bookingInfo1;
    public BikeProvider bikeProviderA;
    public Bike bikeA1;
    public Booking bookingA1_1;
    public BikeProvider bikeProviderB;
    public BikeProvider bikeProviderC;
    public Request request1;

    public BikeType hybrid = new BikeType("hybrid", new BigDecimal(100));
    public BikeType commute = new BikeType("commute", new BigDecimal(50));
    public BikeType mountain = new BikeType("mountain", new BigDecimal(200));

    public DeliveryService deliveryService;

    @BeforeEach
    void setUp() throws Exception {
        // Setup mock delivery service before each tests
        DeliveryServiceFactory.setupMockDeliveryService();
        deliveryService = DeliveryServiceFactory.getDeliveryService();

        // Put your test setup here
        database = new Database();

        // Mock data setup
        customer1 = new Customer();
        database.addCustomer(customer1);

        bookingInfo1 = new CustomerBookingInfo("name", "phone", new Location("EH1AAA", "address1"), "surname",
                DeliveryMethod.DELIVERY_DRIVER, DeliveryMethod.CUSTOMER);

        database.addBikeType(hybrid);
        database.addBikeType(commute);
        database.addBikeType(mountain);

        bikeProviderA = new BikeProvider(new Location("EH2AAA", "addressA"), 20.0);

        bikeProviderA.setRentalPrice(hybrid, new BigDecimal(20));
        bikeProviderA.setRentalPrice(commute, new BigDecimal(10));
        bikeProviderA.setRentalPrice(mountain, new BigDecimal(30));

        // BikeProviderA bikes
        // Hybrid booked during 26-27 Nov
        bikeA1 = new Bike("bikeA1", hybrid);
        bookingA1_1 = new Booking(bikeProviderA.getUUID(), bookingInfo1,
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

        bikeProviderB = new BikeProvider(new Location("EH2AAA", "addressA"), 10.0);

        bikeProviderB.setRentalPrice(hybrid, new BigDecimal(20));
        bikeProviderB.setRentalPrice(commute, new BigDecimal(10));

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
        bikeProviderB.addPartner(bikeProviderA);
        database.addBikeProvider(bikeProviderB);

        // In different location than the customer1
        bikeProviderC = new BikeProvider(new Location("GG2AAA", "addressA"), 5.0);

        bikeProviderB.setRentalPrice(hybrid, new BigDecimal(20));

        // BikeProviderC bikes
        // Hybrid booked during 26-27 Nov
        Bike bikeC1 = new Bike("bikeB1", hybrid);

        bikeProviderC.addBike(bikeC1);
        database.addBikeProvider(bikeProviderC);

        // 3 days
        DateRange dateRange = new DateRange(LocalDate.of(2019, 11, 28), LocalDate.of(2019, 11, 30));
        // same location as BikeProviderA, BikeProviderB
        Location location = new Location("EH1AAA", "address1");

        Map<String, Integer> numOfBikes = new HashMap<>();
        numOfBikes.put(hybrid.toString(), 1);
        numOfBikes.put(commute.toString(), 2);

        request1 = new Request(dateRange, location, numOfBikes);

    }

    @Test
    void getQuotesTest() {
        Collection<Quote> quotes = customer1.getQuotes(request1, database);

        assertEquals(quotes.size(), 1);

        for (Quote quote : quotes) {
            BikeProvider bikeProvider = quote.getBikeProvider();

            // Check that bike provider is near to customer location
            assert (bikeProvider.getLocation().isNearTo(request1.getLocation()));

            // Check if bike is not already booked
            for (Bike bike : bikeProvider.getBikes()) {
                for (Booking booking : bike.getBookings()) {
                    assert (!(booking.getDateRange().overlaps(request1.getDateRange())));
                }
            }

            // check if total deposit and total rental price are calculated correctly
            assertEquals(quote.getTotalPrice().stripTrailingZeros(), new BigDecimal(120).stripTrailingZeros());
            assertEquals(quote.getTotalDeposit().stripTrailingZeros(), new BigDecimal(40).stripTrailingZeros());
        }
    }

    @Test
    void bookQuoteTest() {
        Collection<Quote> quotes = customer1.getQuotes(request1, database);
        // get the first quote
        Quote quote = quotes.iterator().next();

        Booking booking = customer1.bookQuote(bookingInfo1, quote);

        // first 36 chars of booking unique id should be bike provider uuid String
        String bikeProviderUUID = booking.getUniqueID().substring(0, 36);
        assertEquals(bikeProviderUUID, quote.getBikeProvider().getUUID());

        // booking is added to customer object
        assertEquals(customer1.getBookings().size(), 1);

        // check if booking is added to every bike selected by the customer
        for (Bike bike : quote.getBikeProvider().getBikes()) {
            for (Bike selectedBike : quote.getSelectedBikes()) {
                assert (selectedBike.getBookings().contains(booking));
            }
        }

        // check if pickup is created for every bike if collection method is delivery
        if (bookingInfo1.getCollectionMethod() == DeliveryMethod.DELIVERY_DRIVER) {
            Collection<Deliverable> pickups = deliveryService.getPickupsOn(booking.getDateRange().getStart());

            for (Bike selectedBike : quote.getSelectedBikes()) {
                assert (pickups.contains(selectedBike));
            }
        }

        // check if pickup is created for every bike if return method is delivery
        if (bookingInfo1.getReturnMethod() == DeliveryMethod.DELIVERY_DRIVER) {
            Collection<Deliverable> pickups = deliveryService.getPickupsOn(booking.getDateRange().getEnd());

            for (Bike selectedBike : quote.getSelectedBikes()) {
                assert (pickups.contains(selectedBike));
            }
        }
    }

    @Nested
    @DisplayName("Testing use case when bike is returned to Bike Provider")
    class registerBikeReturnTest {

        @Nested
        @DisplayName("When returned to original provider")
        class returnToOriginalProviderTest {

            @BeforeEach
            void setUp() {
                // Returning bikeA1 to bikeProviderA
                bikeProviderA.registerBikeReturn(bookingA1_1.getUniqueID());
            }

            @Test
            @DisplayName("should change status of bike to IN_STORE")
            void testBikeStatus() {
                assertEquals(bikeA1.getStatus(), BikeStatus.IN_STORE);
            }
        }

        @Nested
        @DisplayName("When returned to partner of original provider")
        class returnToPartnerProviderTest {

            @BeforeEach
            void setUp() {
                // Returning bikeA1 to bikeProviderB which is partner of bikeProvider
                bikeProviderB.registerBikeReturn(bookingA1_1.getUniqueID());
            }

            @Test
            @DisplayName("should change status of bike to IN_PARTNER_STORE")
            void testBikeStatus() {
                assertEquals(bikeA1.getStatus(), BikeStatus.IN_PARTNER_STORE);
            }

            @Test
            @DisplayName("should create new pickup for the bike on the same day")
            void testDeliveryPickup() {
                Collection<Deliverable> pickups = deliveryService.getPickupsOn(LocalDate.now());
                assert (pickups.contains(bikeA1));
            }
        }
    }
}
