package uk.ac.ed.bikerental;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.function.BooleanSupplier;

/**
 * DateRange is the class we'll be using to represent the period during which a
 * bike can be booked
 */
public class DateRange {
    /**
     * private fields that store the start day and end day of the date range
     */
    private LocalDate start, end;

    /**
     * constructor for our DateRange class
     * 
     * @param start the start date
     * @param end   the end date
     */
    public DateRange(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    /**
     * getter for start attribute
     * 
     * @return the start date
     */
    public LocalDate getStart() {
        return this.start;
    }

    /**
     * getter for end attribute
     * 
     * @return the end date
     */
    public LocalDate getEnd() {
        return this.end;
    }

    /**
     * convert date range to years
     * 
     * @return no. of years between the start and end date
     */
    public long toYears() {
        return ChronoUnit.YEARS.between(this.getStart(), this.getEnd());
    }

    /**
     * convert date range to days
     * 
     * @return no. of days between the start and end date
     */
    public long toDays() {
        return ChronoUnit.DAYS.between(this.getStart(), this.getEnd());
    }

    /**
     * checks if this date range overlaps with another date range
     * 
     * @param other DateRange to be checked against
     * @return whther it overlaps or not
     */
    public Boolean overlaps(DateRange other) {
        if (other.getStart().equals(this.start)) {
            return true;
        } else if (other.getEnd().equals(this.end)) {
            return true;
        } else if ((other.getStart().isAfter(this.start)) && (other.getStart().isBefore(this.end))) {
            return true;
        } else if ((this.getStart().isAfter(other.start)) && (this.getStart().isBefore(other.end))) {
            return true;
        } else if ((other.getEnd().isAfter(this.start)) && (other.getEnd().isBefore(this.end))) {
            return true;
        } else if ((this.getEnd().isAfter(other.start)) && (this.getEnd().isBefore(other.end))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * hashes the object so it can be used in collections
     * 
     * @return hash of the object
     */
    @Override
    public int hashCode() {
        // hashCode method allowing use in collections
        return Objects.hash(end, start);
    }

    /**
     * compares if this date range is equal to another object
     * 
     * @param Object to compare against
     * @return true if both start and end are same for both objects
     */
    @Override
    public boolean equals(Object obj) {
        // equals method for testing equality in tests
        if (obj == this) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (getClass() != obj.getClass()) {
            return false;
        }

        DateRange other = (DateRange) obj;
        return Objects.equals(end, other.end) && Objects.equals(start, other.start);
    }
}
