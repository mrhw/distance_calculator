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

    public static Distance of(final BigDecimal amount, final LengthUnit lengthUnit) {
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

    private Distance checkLengthUnitEqual(final DistanceProvider provider) {
        Distance distance = of(provider);
        if (isSameLengthUnit(distance) == false) {
            throw new IllegalArgumentException("Units mismatch");
        }
        return distance;
    }

    private boolean isSameLengthUnit(final DistanceProvider distance) {
        return (this.getLengthUnit().equals(of(distance).getLengthUnit()));
    }

    public static Distance of(final DistanceProvider provider) {
        final Distance distance = provider.toDistance();
        return distance;
    }

    public Distance convertTo(final LengthUnit lengthUnit, final BigDecimal ratio) {
        if (this.lengthUnit == lengthUnit) {
            return this;
        }
        final BigDecimal newDistanceAmount = amount.multiply(ratio);
        return Distance.of(newDistanceAmount, lengthUnit);
    }

    @Override
    public Distance toDistance() {
        return this;
    }

    public Distance plus(final DistanceProvider distanceToAdd) {
        final Distance toAdd = checkLengthUnitEqual(distanceToAdd);
        return plus(toAdd.getAmount());
    }

    public Distance minus(final DistanceProvider distanceToSubtrack) {
        final Distance toRemove = checkLengthUnitEqual(distanceToSubtrack);
        return minus(toRemove.getAmount());
    }

    public Distance multiplied(final DistanceProvider distanceMultiplier) {
        final Distance multiplier = checkLengthUnitEqual(distanceMultiplier);
        return multiplied(multiplier.getAmount());
    }

    public Distance divide(final DistanceProvider divideBy) {
        final Distance divide = checkLengthUnitEqual(divideBy);
        return divide(divide.getAmount());
    }

    public Distance plus(final BigDecimal amountToAdd) {
        final BigDecimal newAmount = amount.add(amountToAdd);
        return Distance.of(newAmount, lengthUnit);
    }

    public Distance minus(final BigDecimal amountToSubtrack) {
        final BigDecimal newAmount = amount.subtract(amountToSubtrack);
        return Distance.of(newAmount, lengthUnit);
    }

    public Distance multiplied(final BigDecimal multiplier) {
        final BigDecimal newAmount = amount.multiply(multiplier);
        return Distance.of(newAmount, lengthUnit);
    }

    public Distance divide(final BigDecimal divideBy) {
        final BigDecimal newAmount = amount.divide(divideBy);
        return Distance.of(newAmount, lengthUnit);
    }

    @Override
    public int compareTo(final DistanceProvider other) {
        final Distance otherDistance = of(other);
        if (lengthUnit.equals(otherDistance.getLengthUnit()) == false) {
            throw new IllegalArgumentException("Units mismatch");
        }
        return amount.compareTo(otherDistance.getAmount());
    }
}
