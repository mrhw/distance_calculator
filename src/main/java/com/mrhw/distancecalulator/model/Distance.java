package com.mrhw.distancecalulator.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Distance implements DistanceProvider, Comparable<DistanceProvider>, Serializable {
    private final BigDecimal amount;
    private final LengthUnit lengthUnit;

    public Distance(BigDecimal amount, LengthUnit lengthUnit) {
        this.amount = amount.setScale(2, RoundingMode.HALF_UP);
        this.lengthUnit = lengthUnit;
    }

    public static Distance of(BigDecimal amount, LengthUnit lengthUnit) {
        DistanceUtils.checkNotNull(amount, "Amount cannot be null.");
        DistanceUtils.checkNotNull(lengthUnit, "Length unit cannot be null.");
        return new Distance(amount, lengthUnit);
    }

    public LengthUnit getLengthUnit() {
        return lengthUnit;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    private Distance checkLengthUnitEqual(DistanceProvider provider) {
        Distance distance = of(provider);
        if (isSameLenghtUnit(distance) == false) {
            throw new IllegalArgumentException("Units mismatch");
        }
        return distance;
    }

    private boolean isSameLenghtUnit(DistanceProvider distance) {
        return (this.getLengthUnit().equals(of(distance).getLengthUnit()));
    }

    private Distance of(DistanceProvider provider) {
        Distance distance = provider.toDistance();
        return distance;
    }

    public Distance convertTo(LengthUnit lengthUnit, BigDecimal ratio) {
        if (this.lengthUnit == lengthUnit) {
            return this;
        }
        BigDecimal newDistanceAmount = amount.multiply(ratio);
        return Distance.of(newDistanceAmount, lengthUnit);
    }

    @Override
    public Distance toDistance() {
        return this;
    }

    public Distance plus(DistanceProvider distanceToAdd) {
        Distance toAdd = checkLengthUnitEqual(distanceToAdd);
        return plus(toAdd.getAmount());
    }

    public Distance minus(DistanceProvider distanceToSubtrack) {
        Distance toRemove = checkLengthUnitEqual(distanceToSubtrack);
        return minus(toRemove.getAmount());
    }

    public Distance multiplied(DistanceProvider distanceMultiplier) {
        Distance multiplier = checkLengthUnitEqual(distanceMultiplier);
        return multiplied(multiplier.getAmount());
    }

    public Distance divide(DistanceProvider divideBy) {
        Distance divide = checkLengthUnitEqual(divideBy);
        return divide(divide.getAmount());
    }

    public Distance plus(BigDecimal amountToAdd) {
        BigDecimal newAmount = amount.add(amountToAdd);
        return Distance.of(newAmount, lengthUnit);
    }

    public Distance minus(BigDecimal amountToSubtrack) {
        BigDecimal newAmount = amount.subtract(amountToSubtrack);
        return Distance.of(newAmount, lengthUnit);
    }

    public Distance multiplied(BigDecimal multiplier) {
        BigDecimal newAmount = amount.multiply(multiplier);
        return Distance.of(newAmount, lengthUnit);
    }

    public Distance divide(BigDecimal divideBy) {
        BigDecimal newAmount = amount.divide(divideBy);
        return Distance.of(newAmount, lengthUnit);
    }

    @Override
    public int compareTo(DistanceProvider other) {
        Distance otherDistance = of(other);
        if (lengthUnit.equals(otherDistance.getLengthUnit()) == false) {
            throw new IllegalArgumentException("Units mismatch");
        }
        return amount.compareTo(otherDistance.getAmount());
    }
}
