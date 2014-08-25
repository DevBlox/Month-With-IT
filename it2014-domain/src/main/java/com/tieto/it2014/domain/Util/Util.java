package com.tieto.it2014.domain.Util;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.tieto.it2014.domain.user.entity.UserLoc;
import com.tieto.it2014.domain.user.entity.Workout;
import com.tieto.it2014.domain.weight.entity.Weight;
import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Util {

    private final static double AVERAGE_RADIUS_OF_EARTH = 6371;

    public static List<Workout> getRecentWorkouts(List<UserLoc> userLocs, Integer workoutLimit) {
        List<Workout> workouts = Lists.newArrayList();
        final long now = new Timestamp(new Date().getTime()).getTime();

        // 1. Filter only valid user locs.
        List<UserLoc> filteredUserLocs = Lists.newArrayList(Collections2.filter(userLocs, new Predicate<UserLoc>() {
            @Override
            public boolean apply(UserLoc t) {
                return StringUtils.isNotBlank(t.id) && t.timeStamp <= now;
            }
        }));

        // 2. Reversing locks (from oldest, to newest).
        Collections.reverse(filteredUserLocs);

        for (final UserLoc loc : filteredUserLocs) {
            Optional<Workout> out = Iterables.tryFind(workouts, new Predicate<Workout>() {
                @Override
                public boolean apply(Workout t) {
                    return t.getImei().equals(loc.id) && Math.abs(loc.timeStamp - t.getLastLoc().timeStamp) < 300000;
                }
            });
            if (out.isPresent()) {
                out.get().addLoc(loc);
            } else {
                workouts.add(new Workout(loc));
            }
        }

        // 3. Sort result on finish time + distance.
        Collections.sort(workouts, new Comparator<Workout>() {
            @Override
            public int compare(Workout o1, Workout o2) {
                if (o1.getFinishTime().equals(o2.getFinishTime())) {
                    return o2.getDistance().compareTo(o1.getDistance());
                }
                return o2.getFinishTime().compareTo(o1.getFinishTime());
            }
        });

        // 4. Removing bad results (like 0 duration workouts).
        workouts = Lists.newArrayList(Collections2.filter(workouts, new Predicate<Workout>() {
            @Override
            public boolean apply(Workout t) {
                return !StringUtils.trim(t.getDuration()).equalsIgnoreCase("00 h. 00 min.");
            }
        }));

        // 5. Assign id to every item.
        int i = 0;
        for (Workout wo : workouts) {
            wo.setId(++i);
        }

        // 6. Limiting the size of the result items.
        if (workoutLimit != null) {
            return Lists.newArrayList(Iterables.limit(workouts, workoutLimit));
        } else {
            return workouts;
        }
    }

    public static int calculateDuration(Long start, Long end) {
        return (int) TimeUnit.MILLISECONDS.toSeconds(end - start);
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

        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        if (seconds % 60 > 0) {
            minutes++;
        }

        if (minutes >= 60) {
            hours++;
            minutes -= 60;
        }

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

    public static String formatDoubleToString(Double doub) {
        return new DecimalFormat("0.000").format(doub);
    }

    public static String theMonth(int month) {
        String[] monthNames = {"JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER"};
        return monthNames[month];
    }

    public static double truncate(double number, int precision) {
        double prec = Math.pow(10, precision);
        int integerPart = (int) number;
        double fractionalPart = number - integerPart;
        fractionalPart *= prec;
        int fractPart = (int) fractionalPart;
        fractionalPart = (double) (integerPart) + (double) (fractPart) / prec;
        return fractionalPart;
    }

    /**
     * Loop through all the list and leave the last weight input in day
     *
     * @param list
     * @return
     */
    public static List<Weight> getFilteredOnePerDayList(List<Weight> list) {
        for (int i = 0; i < list.size(); i++) {
            if ((list.size() - 1) > i) {
                if (extractDayFromTimestamp(list.get(i).timeStamp) == extractDayFromTimestamp(list.get(i + 1).timeStamp)) {
                    list.remove(i);
                    i--;
                }
            }
        }

        return list;
    }

    /**
     * Get day of the month from given timestamp
     *
     * @param timestamp
     * @return
     */
    public static int extractDayFromTimestamp(Long timestamp) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(timestamp));
        int day = cal.get(Calendar.DAY_OF_MONTH);

        return day;
    }

    public static Calendar convertToGmt(Calendar cal) {

        Date date = cal.getTime();
        TimeZone tz = cal.getTimeZone();

        //Returns the number of milliseconds since January 1, 1970, 00:00:00 GMT
        long msFromEpochGmt = date.getTime();

        //gives you the current offset in ms from GMT at the current date
        int offsetFromUTC = tz.getOffset(msFromEpochGmt);

        //create a new calendar in GMT timezone, set to this date and add the offset
        Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        gmtCal.setTime(date);
        gmtCal.add(Calendar.MILLISECOND, offsetFromUTC);

        return gmtCal;
    }

    public static Long convertToGmtLong(long timestamp) {
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(timestamp);
        Date date = cal.getTime();
        TimeZone tz = cal.getTimeZone();

        //Returns the number of milliseconds since January 1, 1970, 00:00:00 GMT
        long msFromEpochGmt = date.getTime();

        //gives you the current offset in ms from GMT at the current date
        int offsetFromUTC = tz.getOffset(msFromEpochGmt);

        //create a new calendar in GMT timezone, set to this date and add the offset
        Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        gmtCal.setTime(date);
        gmtCal.add(Calendar.MILLISECOND, offsetFromUTC);
        Date trimedDate = gmtCal.getTime();
        Long trimedTimestamp = trimedDate.getTime();

        return trimedTimestamp;
    }
}
