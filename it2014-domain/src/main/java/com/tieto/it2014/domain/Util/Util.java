package com.tieto.it2014.domain.Util;

import java.util.concurrent.TimeUnit;

/**
 * Created by mantas on 07/08/14.
 */
public class Util {

    public final static double AVERAGE_RADIUS_OF_EARTH = 6371;

    public static long calculateDuration(Long start, Long end) {
        return TimeUnit.MILLISECONDS.toSeconds(end-start);
    }

    public static double calculateDistance(double userLat, double userLng, double venueLat, double venueLng) {
        double latDistance = Math.toRadians(userLat - venueLat);
        double lngDistance = Math.toRadians(userLng - venueLng);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(userLat)) * Math.cos(Math.toRadians(venueLat))
                * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return (double) Math.round((AVERAGE_RADIUS_OF_EARTH * c) * 100) / 100;
    }
}
