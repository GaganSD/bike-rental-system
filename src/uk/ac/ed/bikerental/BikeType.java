package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Objects;

public class BikeType {
    private String name;
    private BigDecimal replacementValue;

    public BikeType(String name, BigDecimal replacementValue) {
        this.name = name;
        this.replacementValue = replacementValue;
    }

    public BigDecimal getReplacementValue() {
        return this.replacementValue;
    }

    /**
     * @param replacementValue the replacementValue to set
     */
    public void setReplacementValue(BigDecimal replacementValue) {
        this.replacementValue = replacementValue;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BikeType other = (BikeType) obj;
        return name.equals(other.name);
    }
}