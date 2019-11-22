package uk.ac.ed.bikerental;

public class CustomerBookingInfo {
    private String firstName;
    private String phoneNum;
    private Location location;
    private String surname;
    private DeliveryMethod collectionMethod;
    private DeliveryMethod returnMethod;

    public CustomerBookingInfo(String firstName, String phoneNum, Location location, String surname,
            DeliveryMethod collectionMethod, DeliveryMethod returnMethod) {

        this.firstName = firstName;
        this.phoneNum = phoneNum;
        this.location = location;
        this.surname = surname;
        this.collectionMethod = collectionMethod;
    }

    public Location getLocation() {
        return this.location;
    }

    public DeliveryMethod getCollectionMethod() {
        return this.collectionMethod;
    }

    public DeliveryMethod getReturnMethod() {
        return this.returnMethod;
    }
}