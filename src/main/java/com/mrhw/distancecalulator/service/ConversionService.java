package com.mrhw.distancecalulator.service;

import com.mrhw.distancecalulator.model.LengthUnit;

import java.math.BigDecimal;

public interface ConversionService {
    BigDecimal resolveRate(LengthUnit from, LengthUnit to);
}
