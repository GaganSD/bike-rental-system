package uk.ac.ed.bikerental;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class Database {
    private Collection<Customer> customers = new ArrayList<>();
    private Collection<BikeProvider> bikeProviders = new ArrayList<>();
    private Collection<BikeType> bikeTypes = new HashSet<>();

    public Collection<Customer> getCustomers() {
        return this.customers;
    }

    public Collection<BikeProvider> getBikeProviders() {
        return this.bikeProviders;
    }

    public Collection<BikeType> getBikeTypes() {
        return this.bikeTypes;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void addBikeProvider(BikeProvider bikeProvider) {
        bikeProviders.add(bikeProvider);
    }

    public void addBikeType(BikeType bikeType) {
        bikeTypes.add(bikeType);
    }
}