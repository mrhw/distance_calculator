package com.mrhw.distancecalulator.model;

public class DistanceUtils {

    private DistanceUtils() {
    }

    public static void checkNotNull(Object object, String message) {
        if(object == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
