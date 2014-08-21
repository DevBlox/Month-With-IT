package com.tieto.it2014.domain.Util;

import com.tieto.it2014.domain.user.entity.UserLoc;
import com.tieto.it2014.domain.user.entity.Workout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

public class TestUtil {

    @Test
    public void test_0_seconds_to_duration_string() {
        assertEquals(Util.getDurationString(0), "00 h. 00 min.");
    }

    @Test
    public void test_minus_seconds_to_duration_string() {
        assertEquals(Util.getDurationString(-1), "00 h. 00 min.");
    }

    @Test
    public void test_positive_seconds_to_duration_string() {
        assertEquals(Util.getDurationString(1), "00 h. 01 min.");
    }

    @Test
    public void rounds_correctly() {
        assertEquals(Util.getDurationString(59), "00 h. 01 min.");
        assertEquals(Util.getDurationString(60), "00 h. 01 min.");
        assertEquals(Util.getDurationString(61), "00 h. 02 min.");
        assertEquals(Util.getDurationString(3600 - 1), "01 h. 00 min.");
    }

    @Test
    public void calculates_workouts_correctly_with_same_timestamp() {
        List<UserLoc> userLoc = new ArrayList<>(Arrays.asList(
                new UserLoc("356871044631608", "Test", Long.parseLong("1407350762000"), 54.724767, 25.298105, 502.0),
                new UserLoc("356871044631608", "Test", Long.parseLong("1407350762000"), 54.722828, 25.298035, 500.0),
                new UserLoc("356871044631608", "Test", Long.parseLong("1407350762000"), 54.721865, 25.296, 501.0)
        ));

        List<Workout> workouts = Util.getRecentWorkouts(userLoc, 10);
        assertEquals(workouts.isEmpty(), true);
    }

    @Test
    public void calculates_single_workout() {
        List<UserLoc> userLoc = new ArrayList<>(Arrays.asList(
                new UserLoc("356871044631608", "Test", Long.parseLong("1407350762000"), 54.724767, 25.298105, 502.0),
                new UserLoc("356871044631608", "Test", Long.parseLong("1407350752000"), 54.722828, 25.298035, 500.0),
                new UserLoc("356871044631608", "Test", Long.parseLong("1407350742000"), 54.721865, 25.296, 501.0)
        ));

        List<Workout> workouts = Util.getRecentWorkouts(userLoc, null);

        assertFalse((workouts.isEmpty()));
    }

    // Point A Latitude: 54.71722152 Longitude: 25.29514074 Altitude: 120
    // Point B Latitude: 54.72031996 Longitude: 25.30243635 Altitude: 117
    // Actual Distance: 0.581 km. 
    @Test
    public void checks_if_distance_between_two_points_calculated_correctly() {
        Double errorRange = 0.01;
        Double actualdistance = 0.581;
        Double calculatedDistance = Util.calculateDistance(54.71722152, 25.29514074, 120, 54.72031996, 25.30243635, 117);
        assertFalse(!(actualdistance <= calculatedDistance + errorRange)
                && (actualdistance >= calculatedDistance - errorRange));
    }

}
// 0.215 + 0.169
//    @Test
//    public void calculates_distance_correctly() {
//        List<UserLoc> userLoc = new ArrayList<>(Arrays.asList(
//                new UserLoc("321654987", "Test", (long) 0, 54.724767, 25.298105, 50.0),
//                new UserLoc("321654987", "Test", (long) 10000, 54.722828, 25.298035, 500.0),
//                new UserLoc("321654987", "Test", (long) 20000, 54.721865, 25.296, 5.0)
//        ));
//
//        List<Workout> workouts = Util.getRecentWorkouts(userLoc, 10);
//
//
//    }

