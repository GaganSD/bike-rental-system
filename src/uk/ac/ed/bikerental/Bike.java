package uk.ac.ed.bikerental;

public class Bike {

    public String serialNum;

    public BikeType myBikeType;
    
    // public enum 
    
    public String getType() {
        
        
        return myBikeType.name;
    }

    public Boolean isAvailable(DateRange dr) {


        return false;
    }










    
}