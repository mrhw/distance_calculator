package com.mrhw.distancecalulator.service;

import com.mrhw.distancecalulator.model.Distance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DistanceServiceImpl implements DistanceService {

    private RateResolver rateResolver;

    @Autowired
    public DistanceServiceImpl(RateResolver conversionService) {
        this.rateResolver = conversionService;
    }

    @Override
    public Distance add(Distance distance1, Distance distance2) {
        try {
            return distance1.plus(distance2);
        } catch (IllegalArgumentException e) {
            Distance converted = getConverted(distance1, distance2);
            return converted.plus(distance2);
        }
    }

    private Distance getConverted(Distance distance1, Distance distance2) {
        return distance1.convertTo(distance2.getLengthUnit(), rateResolver.resolve(distance1.getLengthUnit(), distance2.getLengthUnit()));
    }

    @Override
    public Distance subtract(Distance distance1, Distance distance2) {
        try {
            return distance1.minus(distance2);
        } catch (IllegalArgumentException e) {
            Distance converted = getConverted(distance1, distance2);
            return converted.minus(distance2);
        }
    }

    @Override
    public Distance multiply(Distance distance1, Distance distance2) {
        try {
            return distance1.multiplied(distance2);
        } catch (IllegalArgumentException e) {
            Distance converted = getConverted(distance1, distance2);
            return converted.multiplied(distance2);
        }
    }

    @Override
    public Distance divide(Distance distance1, Distance distance2) {
        try {
            return distance1.divide(distance2);
        } catch (IllegalArgumentException e) {
            Distance converted = getConverted(distance1, distance2);
            return converted.divide(distance2);
        }
    }
}
