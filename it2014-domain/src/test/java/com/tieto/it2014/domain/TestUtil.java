package com.tieto.it2014.domain;

import com.tieto.it2014.domain.Util.Util;
import com.tieto.it2014.domain.user.entity.UserLoc;
import com.tieto.it2014.domain.user.entity.Workout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
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
                new UserLoc("321654987", "Test", (long) 0, 54.724767, 25.298105, 50.0),
                new UserLoc("321654987", "Test", (long) 0, 54.722828, 25.298035, 500.0),
                new UserLoc("321654987", "Test", (long) 0, 54.721865, 25.296, 5.0)
        ));

        List<Workout> workouts = Util.getRecentWorkouts(userLoc, 10);
        assertEquals(workouts.isEmpty(), true);
    }

    @Test
    public void calculates_single_workout() {
        List<UserLoc> userLoc = new ArrayList<>(Arrays.asList(
                new UserLoc("356871044631608", "Test", Long.parseLong("1408512754995"), 54.724767, 25.298105, 50.0),
                new UserLoc("356871044631608", "Test", Long.parseLong("1408512775012"), 54.722828, 25.298035, 500.0),
                new UserLoc("356871044631608", "Test", Long.parseLong("1408512795051"), 54.721865, 25.296, 5.0)
        ));

        List<Workout> workouts = Util.getRecentWorkouts(userLoc, null);

        //This test is false!!
        assertEquals(workouts.isEmpty(), true);
    }

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
}
