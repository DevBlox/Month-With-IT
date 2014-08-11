package com.tieto.it2014.domain.Util;

import com.tieto.it2014.domain.user.entity.UserLoc;
import com.tieto.it2014.domain.user.entity.Workout;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Util {

    private final static double AVERAGE_RADIUS_OF_EARTH = 6371;

    public static List<Workout> getRecentWorkouts(List<UserLoc> userLocs, int maxWorkoutNumber) {
        Boolean started = true;
        Boolean EOFLine = false;
        Boolean fullList = false;
        Boolean ret = false;
        int workoutId = 0;
        Long startTime = 0L;
        Long endTime = 0L;
        int sec = 0;
        int totalSec = 0;
        Double totalDist = 0.0;
        int lastIter = 0;

        List<Workout> woList = new ArrayList<>();
        for (int i = 0; i <= userLocs.size()-2 ; i++) {
            if(userLocs.get(i).id == null || userLocs.get(i).id.isEmpty()) continue;

            UserLoc lc1 = userLocs.get(i);
            if (started) {
                started = false;
                endTime = lc1.timeStamp;
            }

            UserLoc lc2 = findNext(userLocs, lc1);

            sec = calculateDuration(lc2.timeStamp, lc1.timeStamp);

            if (sec < 5*60 && (EOFLine = !lc2.equals(userLocs.get(userLocs.size()-1)))) {

                totalDist += calculateDistance(lc1.latitude, lc1.longtitude, lc1.altitude, lc2.latitude, lc2.longtitude, lc2.altitude);
                totalSec += sec;

                if (userLocs.indexOf(lc2)-userLocs.indexOf(lc1) > 1 && !ret) {
                    ret = true;
                    lastIter = userLocs.indexOf(lc1);
                    i = userLocs.indexOf(lc2)-1;
                }
            
            } else {

                if (EOFLine) {
                    startTime = lc1.timeStamp;
                } else {
                    startTime = lc2.timeStamp;
                    if (lc1.id.compareTo(lc2.id) == 0) {
                        totalDist += calculateDistance(lc1.latitude, lc1.longtitude, lc1.altitude, lc2.latitude, lc2.longtitude, lc2.altitude);
                        totalSec += sec;
                        if (totalSec != 0 && !(workoutId > maxWorkoutNumber)) {
                            woList.add(new Workout(++workoutId,
                                lc1.id,
                                new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(startTime),
                                new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(endTime),
                                format(totalDist),
                                totalSec)
                            );
                            woList = swapIfLonger(woList, woList.get(woList.size() - 2), woList.get(woList.size() - 1));
                        }
                        break;
                    }
                }

                started = true;

                if (totalSec != 0 && !(fullList = workoutId > maxWorkoutNumber)) {
                    
                    woList.add(new Workout(++workoutId, 
                        lc1.id, 
                        new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(startTime), 
                        new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(endTime),
                        format(totalDist),
                        totalSec)
                    );

                    if (woList.size() > 1) {
                        woList = swapIfLonger(woList, woList.get(woList.size()-2), woList.get(woList.size()-1));
                    }

                    if(ret) {
                        i = lastIter;
                        ret = false;
                    }

                } else if (fullList) {
                    break;
                }

                totalSec = 0;
                totalDist = 0.0;
            }
        }
        return woList;
    }

    private static List<Workout> swapIfLonger(List<Workout> woList, Workout w1, Workout w2) {
        if (w1.getFinishTime().compareTo(w2.getFinishTime()) == 0 && w1.duration < w2.duration) {
            int i1 = woList.indexOf(w1);
            int i2 = woList.indexOf(w2);
            woList.set(i1, w2);
            woList.set(i2, w1);
        }
        return woList;
    }

    public static int calculateDuration(Long start, Long end) {
        return (int)TimeUnit.MILLISECONDS.toSeconds(end-start);
    }

    public static UserLoc findNext(List<UserLoc> userLocs, UserLoc loc) {
        for (int i = userLocs.indexOf(loc)+1; i < userLocs.size() && calculateDuration(userLocs.get(i).timeStamp, loc.timeStamp) < 300; i++) {
            if (userLocs.get(i).id.compareTo(loc.id) == 0) {
                return userLocs.get(i);
            }
        }
        return userLocs.get(userLocs.indexOf(loc)+1);
    }

    public static double calculateDistance(double userLat, double userLng, double userAlt, double venueLat, double venueLng, double venueAlt) {
        double latDistance = Math.toRadians(userLat - venueLat);
        double lngDistance = Math.toRadians(userLng - venueLng);
        double height = userAlt - venueAlt;
        double distance;

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(userLat)) * Math.cos(Math.toRadians(venueLat))
                * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        distance = AVERAGE_RADIUS_OF_EARTH * c;
        distance = Math.sqrt(Math.pow(distance * 1000, 2) + Math.pow(height, 2));
        
        return (double) Math.round(distance * 100) / 100000;
    }

    public static String getDurationString(int seconds) {

        Double hrs = (double) seconds / 3600;
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        if (seconds % 60 > 0) minutes++;

        return twoDigitString(hours) + " h. " + twoDigitString(minutes) + " min.";
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

    private static String format(Double doub) {
        return new DecimalFormat("0.000").format(doub);
    }

}
