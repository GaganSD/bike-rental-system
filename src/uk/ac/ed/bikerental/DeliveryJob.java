package uk.ac.ed.bikerental;

import java.time.LocalDate;

public class DeliveryJob {


    String address;
    String type;
    Integer deposit;
    LocalDate startTime;
    Integer duration;

    public DeliveryJob(String address, String type, Integer deposit, 
                LocalDate startTime, Integer duration) {

        this.address = address;
        this.type = type;
        this.deposit = deposit;
        this.startTime = startTime;
        this.duration = duration;
    }   
}