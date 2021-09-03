package com.mrhw.distancecalulator.service;

import com.mrhw.distancecalulator.model.LengthUnit;

import java.math.BigDecimal;

public interface RateResolver {
    BigDecimal resolve(LengthUnit from, LengthUnit to);
}
