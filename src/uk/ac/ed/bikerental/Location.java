package uk.ac.ed.bikerental;

/**
 * Location is the class we use to represent the customer's and bike providers'
 * location
 */
public class Location {
    /**
     * private field that stores the postcode of the location
     */
    private String postcode;
    /**
     * private field that stores the address of the location
     */
    private String address;

    /**
     * constructor for our Location class
     * 
     * @param postcode the postcode of the location
     * @param address  the address of the location
     */
    public Location(String postcode, String address) {
        assert postcode.length() >= 6;
        this.postcode = postcode;
        this.address = address;
    }

    /**
     * getter for postcode field
     * 
     * @return postcode of the location
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * getter for address field
     * 
     * @return address of the location
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method is used to compare if two addresses are close enough to allow
     * delivery/collection of bikes
     * 
     * @param other Another Location to compare to
     * @return true if first two characters of postcode are same
     */
    public boolean isNearTo(Location other) {
        if (other.postcode.charAt(0) == this.postcode.charAt(0)
                && other.postcode.charAt(1) == this.postcode.charAt(1)) {
            return true;
        }
        return false;
    }
}
