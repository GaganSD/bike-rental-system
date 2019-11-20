package uk.ac.ed.bikerental;

public class Location {
    private String postcode;
    private String address;

    public Location(String postcode, String address) {
        assert postcode.length() >= 6;
        this.postcode = postcode;
        this.address = address;
    }

    /**
     * This method is used to compare if two addresses are close enough to allow
     * delivery/collection of bikes
     * 
     * @param other Another Location to compare to
     * @return boolean True if first two characters of postcode are same
     */
    // check for small case and numbers
    public boolean isNearTo(Location other) {
        if (other.postcode.charAt(0) == this.postcode.charAt(0)
                && other.postcode.charAt(1) == this.postcode.charAt(1)) {
            return true;
        }
        return false;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getAddress() {
        return address;
    }

    // You can add your own methods here
}
