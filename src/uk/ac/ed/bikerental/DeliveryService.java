package uk.ac.ed.bikerental;

import java.util.Collection;
import java.time.LocalDate;

interface DeliveryService {
    public void scheduleDelivery(Deliverable deliverable, Location pickupLocation, 
        Location dropoffLocation, LocalDate pickupDate);

    public Collection<Deliverable> getPickupsOn(LocalDate date);
}
