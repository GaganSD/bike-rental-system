package uk.ac.ed.bikerental;

public enum Status {
    in_use_by_customer(), out_for_delivery_by_partner(), on_track_for_delivery(), in_store(),
    bike_is_out_for_delivery();

    Status() {
    }
}