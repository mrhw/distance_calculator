package com.mrhw.distancecalulator;


import com.mrhw.distancecalulator.model.Distance;
import com.mrhw.distancecalulator.model.DistanceProvider;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.mrhw.distancecalulator.model.LengthUnit.FT;
import static com.mrhw.distancecalulator.model.LengthUnit.M;
import static org.junit.jupiter.api.Assertions.*;

public class DistanceTest {

    private Distance Distance_10_ft = Distance.of(BigDecimal.TEN, FT);
    private Distance Distance_1_M = Distance.of(BigDecimal.valueOf(1), M);
    private Distance Distance_2_M = Distance.of(BigDecimal.valueOf(2), M);
    private Distance Distance_0_M = Distance.of(BigDecimal.ZERO, M);
    @Test
    public void test_factory_of_amount_unit() {
        Distance test = Distance.of(BigDecimal.valueOf(10), FT);
        assertEquals(FT, test.getLengthUnit());
        assertEquals(BigDecimal.valueOf(10).setScale(2), test.getAmount());
    }

    @Test
    public void test_factory_of_amount_null_unit() {
        assertThrows(IllegalArgumentException.class, ()->{
            Distance test = Distance.of(BigDecimal.valueOf(10), null);
        });
    }

    @Test
    public void test_factory_from_distance_provider() {
        Distance initial = new Distance(BigDecimal.TEN, M);
        Distance test = Distance.of(initial);
        assertEquals(test.getAmount(), initial.getAmount());
        assertEquals(test.getLengthUnit(), initial.getLengthUnit());
    }

    @Test
    public void test_factory_from_provider_null_provider() {
        assertThrows(NullPointerException.class, () -> {
            Distance.of((DistanceProvider)null);
        });
    }

    @Test
    public void test_plus_different_units() {
       assertThrows(IllegalArgumentException.class, () -> {
           Distance_0_M.plus(Distance_10_ft);
       });
    }

    @Test
    public void test_plus_same_units() {
        Distance expected = Distance_0_M.plus(Distance_2_M);
        assertEquals(expected.getAmount(), BigDecimal.valueOf(2).setScale(2));
    }

    @Test
    public void test_minus_different_units() {
        assertThrows(IllegalArgumentException.class, () -> {
            Distance_0_M.minus(Distance_10_ft);
        });
    }

    @Test
    public void test_minus_same_units() {
        Distance expected = Distance_0_M.minus(Distance_2_M);
        assertEquals(expected.getAmount(), BigDecimal.valueOf(2).setScale(2).negate());
    }

    @Test
    public void test_multiply_different_units() {
        assertThrows(IllegalArgumentException.class, () -> {
            Distance_0_M.multiplied(Distance_10_ft);
        });
    }

    @Test
    public void test_multiply_same_units() {
        Distance expected = Distance_0_M.multiplied(Distance_2_M);
        assertEquals(expected.getAmount(), BigDecimal.valueOf(0).setScale(2));
    }

    @Test
    public void test_divide_different_units() {
        assertThrows(IllegalArgumentException.class, () -> {
            Distance_0_M.divide(Distance_10_ft);
        });
    }

    @Test
    public void test_divide_same_units() {
        Distance expected = Distance_0_M.divide(Distance_2_M);
        assertEquals(expected.getAmount(), BigDecimal.valueOf(0).setScale(2));
    }

    @Test
    public void test_divide_same_units_not_zero() {
        Distance expected = Distance_1_M.divide(Distance_2_M);
        assertEquals(expected.getAmount(), BigDecimal.valueOf(0.5).setScale(2));
    }

    @Test
    public void test_divide_same_units_by_zero() {
        assertThrows(ArithmeticException.class, () -> {
            Distance expected = Distance_2_M.divide(Distance_0_M);
        });
    }

    @Test
    public void test_convert_same_units() {
        Distance converted = Distance_1_M.convertTo(M, BigDecimal.valueOf(0.5));
        assertEquals(converted.getAmount(), Distance_1_M.getAmount());
        assertEquals(converted.getLengthUnit(), Distance_1_M.getLengthUnit());
    }

    @Test
    public void test_convert_different_units() {
        Distance converted = Distance_2_M.convertTo(FT, BigDecimal.valueOf(2));
        assertNotEquals(converted.getAmount(), Distance_1_M.getAmount());
        assertEquals(converted.getAmount(), BigDecimal.valueOf(4).setScale(2));
        assertEquals(converted.getLengthUnit(), FT);
    }






}
