package com.mrhw.distancecalulator.service;

import com.mrhw.distancecalulator.model.Distance;

public interface DistanceService {
    Distance add(Distance distance1, Distance distance2);
    Distance subtract(Distance distance1, Distance distance2);
    Distance multiply(Distance distance1, Distance distance2);
    Distance divide(Distance distance1, Distance distance2);
}
