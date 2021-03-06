package com.mrhw.distancecalulator.model;

import java.math.BigDecimal;

public final class Constants {

    public static final BigDecimal ONE = BigDecimal.ONE;
    public static final BigDecimal FT_TO_METER = BigDecimal.valueOf(0.3048);
    public static final BigDecimal FT_TO_NM = BigDecimal.valueOf(0.000164578834);
    public static final BigDecimal METER_TO_FT = BigDecimal.valueOf(3.2808399);
    public static final BigDecimal METER_TO_NM = BigDecimal.valueOf(0.000539956803);
    public static final BigDecimal NM_TO_METER = BigDecimal.valueOf(1852);
    public static final BigDecimal NM_TO_FT = BigDecimal.valueOf(6076.11549);
    public static final BigDecimal SQM_TO_SQFT = BigDecimal.valueOf(10.764);
    public static final BigDecimal SQM_TO_SQNM = BigDecimal.valueOf(2.9155334959812285e-7);
    public static final BigDecimal SQFT_TO_SQM = BigDecimal.valueOf(0.09290304);
    public static final BigDecimal SQFT_TO_SQNM = BigDecimal.valueOf(2.7086192499848393e-8);
    public static final BigDecimal SQNM_TO_SQFT = BigDecimal.valueOf(36919179.4);
    public static final BigDecimal SQNM_TO_SQM = BigDecimal.valueOf(3429904);


    private Constants() {

    }

}
