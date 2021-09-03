package com.mrhw.distancecalulator.service;

import com.mrhw.distancecalulator.model.Distance;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.mrhw.distancecalulator.model.LengthUnit.FT;
import static com.mrhw.distancecalulator.model.LengthUnit.M;
import static org.junit.jupiter.api.Assertions.*;

class DistanceServiceImplTest {

    private RateResolver rateResolver = new RateResolverImpl();

    private DistanceService service = new DistanceServiceImpl(rateResolver);

    private Distance Distance_10_ft = Distance.of(BigDecimal.TEN, FT);
    private Distance Distance_1_M = Distance.of(BigDecimal.valueOf(1), M);
    private Distance Distance_2_M = Distance.of(BigDecimal.valueOf(2), M);
    private Distance Distance_0_M = Distance.of(BigDecimal.ZERO, M);

    @Test
    public void test_add_distances_same_units() {
        Distance total = service.add(Distance_1_M, Distance_2_M);
        assertEquals(total.getAmount(), BigDecimal.valueOf(3).setScale(2));
    }

    @Test
    public void test_add_should_convert_distances_different_units() {
        Distance total = service.add(Distance_1_M, Distance_10_ft);
        assertEquals(total.getAmount(), BigDecimal.valueOf(13.28).setScale(2));
    }

    @Test
    public void test_subtract_distances_same_units() {
        Distance total = service.subtract(Distance_1_M, Distance_2_M);
        assertEquals(total.getAmount(), BigDecimal.valueOf(-1).setScale(2));
    }

    @Test
    public void test_subtract_convert_distances_different_units() {
        Distance total = service.subtract(Distance_1_M, Distance_10_ft);
        assertEquals(total.getAmount(), BigDecimal.valueOf(-6.72).setScale(2));
    }

    @Test
    public void test_multiply_distances_same_units() {
        Distance total = service.multiply(Distance_2_M, Distance_2_M);
        assertEquals(total.getAmount(), BigDecimal.valueOf(4).setScale(2));
    }

    @Test
    public void test_multiply_convert_distances_different_units() {
        Distance total = service.multiply(Distance_1_M, Distance_10_ft);
        assertEquals(total.getAmount(), BigDecimal.valueOf(32.80).setScale(2));
    }

    @Test
    public void test_divide_distances_same_units() {
        Distance total = service.divide(Distance_2_M, Distance_2_M);
        assertEquals(total.getAmount(), BigDecimal.valueOf(1).setScale(2));
    }

    @Test
    public void test_divide_by_zero() {
        assertThrows(ArithmeticException.class, () -> {
            Distance total = service.divide(Distance_1_M, Distance_0_M);
        });
    }

    @Test
    public void test_divide_convert_distances_different_units() {
        Distance total = service.divide(Distance_1_M, Distance_10_ft);
        assertEquals(total.getAmount(), BigDecimal.valueOf(0.33).setScale(2));
    }
}