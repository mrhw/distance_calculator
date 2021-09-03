package com.mrhw.distancecalulator.service;

import com.mrhw.distancecalulator.model.Constants;
import com.mrhw.distancecalulator.model.LengthUnit;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@NoArgsConstructor
public class RateResolverImpl implements RateResolver {
    @Override
    public BigDecimal resolve(final LengthUnit from, final LengthUnit to) {
        if (from == to) {
            return Constants.ONE;
        } else if (from.equals(LengthUnit.M)) {
            if (to.equals(LengthUnit.NM)) {
                return Constants.METER_TO_NM;
            }
            if (to.equals(LengthUnit.FT)) {
                return Constants.METER_TO_FT;
            }

        } else if (from.equals(LengthUnit.FT)) {
            if (to.equals(LengthUnit.M)) {
                return Constants.FT_TO_METER;
            }
            if (to.equals(LengthUnit.NM)) {
                return Constants.FT_TO_NM;
            }
        } else if (from.equals(LengthUnit.NM)) {
            if (to.equals(LengthUnit.M)) {
                return Constants.NM_TO_METER;
            }
            if (to.equals(LengthUnit.FT)) {
                return Constants.NM_TO_FT;
            }
        }
        throw new IllegalArgumentException("Unknown conversion");
    }
}
