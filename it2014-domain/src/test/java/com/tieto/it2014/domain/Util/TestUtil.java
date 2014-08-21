package com.tieto.it2014.domain.Util;

import com.tieto.it2014.domain.user.entity.UserLoc;
import com.tieto.it2014.domain.user.entity.Workout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.junit.Test;

public class TestUtil {

    @Test
    public void test_0_seconds_to_duration_string() {
        assertThat(Util.getDurationString(0), equalTo("00 h. 00 min."));
    }

    @Test
    public void test_minus_seconds_to_duration_string() {
        assertThat(Util.getDurationString(-1), equalTo("00 h. 00 min."));
    }

    @Test
    public void test_positive_seconds_to_duration_string() {
        assertThat(Util.getDurationString(1), equalTo("00 h. 01 min."));
    }

    @Test
    public void rounds_correctly() {
        assertThat(Util.getDurationString(59), equalTo("00 h. 01 min."));
        assertThat(Util.getDurationString(60), equalTo("00 h. 01 min."));
        assertThat(Util.getDurationString(61), equalTo("00 h. 02 min."));
        assertThat(Util.getDurationString(3600 - 1), equalTo("01 h. 00 min."));
    }

    @Test
    public void calculates_workouts_correctly_with_same_timestamp() {
        List<UserLoc> userLoc = new ArrayList<>(Arrays.asList(
                new UserLoc("356871044631608", "Test", Long.parseLong("1407350762000"), 54.724767, 25.298105, 502.0),
                new UserLoc("356871044631608", "Test", Long.parseLong("1407350762000"), 54.722828, 25.298035, 500.0),
                new UserLoc("356871044631608", "Test", Long.parseLong("1407350762000"), 54.721865, 25.296, 501.0)
        ));

        List<Workout> workouts = Util.getRecentWorkouts(userLoc, 10);
        assertThat(workouts.isEmpty(), equalTo(true));
    }

    @Test
    public void calculates_single_workout() {
        List<UserLoc> userLoc = new ArrayList<>(Arrays.asList(
                new UserLoc("356871044631608", "Test", Long.parseLong("1407350762000"), 54.724767, 25.298105, 502.0),
                new UserLoc("356871044631608", "Test", Long.parseLong("1407350752000"), 54.722828, 25.298035, 500.0),
                new UserLoc("356871044631608", "Test", Long.parseLong("1407350742000"), 54.721865, 25.296, 501.0)
        ));

        List<Workout> workouts = Util.getRecentWorkouts(userLoc, null);

        assertThat(workouts.size(), equalTo(1));
    }

    // Point A Latitude: 54.71722152 Longitude: 25.29514074 Altitude: 120
    // Point B Latitude: 54.72031996 Longitude: 25.30243635 Altitude: 117
    // Actual Distance: 0.581 km. 
    @Test
    public void checks_if_distance_between_two_points_calculated_correctly() {
        Double errorRange = 0.01;
        Double actualdistance = 0.581;
        Double calculatedDistance = Util.calculateDistance(54.71722152, 25.29514074, 120, 54.72031996, 25.30243635, 117);
        assertThat((actualdistance <= calculatedDistance + errorRange)
                && (actualdistance >= calculatedDistance - errorRange), equalTo(true));
    }


    @Test
    public void calculates_distance_correctly() {
        List<UserLoc> userLoc = new ArrayList<>(Arrays.asList(
                new UserLoc("356871044631608", "Test", Long.parseLong("1407350762000"), 54.724767, 25.298105, 502.0),
                new UserLoc("356871044631608", "Test", Long.parseLong("1407350752000"), 54.722828, 25.298035, 500.0),
                new UserLoc("356871044631608", "Test", Long.parseLong("1407350742000"), 54.721865, 25.296, 501.0)
        ));
        List<Workout> workouts = Util.getRecentWorkouts(userLoc, 1);
        String calculatedDistance = workouts.get(0).getDistance();
        assertThat(calculatedDistance, equalTo("0.385"));
    }
}
