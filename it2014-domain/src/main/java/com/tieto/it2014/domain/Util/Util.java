package com.tieto.it2014.domain.Util;

import com.tieto.it2014.domain.user.entity.UserLoc;
import com.tieto.it2014.domain.user.entity.Workout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Util {

    public final static double AVERAGE_RADIUS_OF_EARTH = 6371;
    static int workoutId = 0;
    static int sec = 0;
    static int totalSec = 0;
    static int k = 0;
    static Long start;
    static Long end;
    static Boolean st = true;
    static UserLoc lc1;
    static UserLoc lc2;
    static Double dist = 0.0;

    public static List<Workout> getRecentWorkouts(List<UserLoc> userLocs, int maxWorkoutNumber) {
        List<Workout> wo = new ArrayList<>();
        for (int i = 0; i <= userLocs.size()-2 ; i++) {
            lc1 = userLocs.get(i);
            lc2 = userLocs.get(i+1);
            String imei1 = lc1.id == null || lc1.id.compareTo("") == 0 ? "(IMEI is not provided)" : lc1.id;
            String imei2 = lc2.id == null || lc2.id.compareTo("") == 0 ? "(IMEI is not provided)" : lc2.id;
            if (st) {
                end = lc1.timeStamp;
                st = false;
            }
            sec = (int)Util.calculateDuration(lc2.timeStamp, lc1.timeStamp);
            if (sec < 5*60 && imei1.compareTo(imei2) == 0 && !lc2.equals(userLocs.get(userLocs.size()-1))) {
                dist += Util.calculateDistance(lc1.Latitude, lc1.Longtitude, lc2.Latitude, lc2.Longtitude);
                totalSec += sec;
                k++;
            } else {
                if (!lc2.equals(userLocs.get(userLocs.size()-1))) {
                    start = lc1.timeStamp;
                } else {
                    start = lc2.timeStamp;
                }
                st = true;
                if (totalSec != 0 && workoutId < maxWorkoutNumber) wo.add(new Workout(++workoutId, imei1, new SimpleDateFormat("yyyy/dd/MM HH:mm:ss").format(start), new SimpleDateFormat("yyyy/dd/MM HH:mm:ss").format(end), dist, Util.getDurationString(totalSec), "User"));
                totalSec = 0;
                k = 0;
                dist = 0D;
            }
        }
        workoutId=0;
        return wo;
    }

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

    public static String getDurationString(int seconds) {

        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        seconds = seconds % 60;

        return twoDigitString(hours) + " : " + twoDigitString(minutes) + " : " + twoDigitString(seconds);
    }

    private static String twoDigitString(int number) {

        if (number == 0) {
            return "00";
        }

        if (number / 10 == 0) {
            return "0" + number;
        }

        return String.valueOf(number);
    }
}
