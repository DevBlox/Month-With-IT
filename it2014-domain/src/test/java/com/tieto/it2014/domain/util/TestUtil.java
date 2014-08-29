package com.tieto.it2014.domain.util;

import com.tieto.it2014.domain.user.entity.UserLoc;
import com.tieto.it2014.domain.user.entity.Workout;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TestUtil {

    private static final String IMEI = "356871044631608";
    private static final String TIME_STAMP = "1407350762000";

    @Test
    public void test0SecondsToDurationString() {
        assertThat(Util.getDurationString(0), equalTo("00 h. 00 min."));
    }

    @Test
    public void testMinusSecondsToDurationString() {
        assertThat(Util.getDurationString(-1), equalTo("00 h. 00 min."));
    }

    @Test
    public void testPositiveSecondsToDurationString() {
        assertThat(Util.getDurationString(1), equalTo("00 h. 01 min."));
    }

    @Test
    public void roundsCorrectly() {
        assertThat(Util.getDurationString(59), equalTo("00 h. 01 min."));
        assertThat(Util.getDurationString(60), equalTo("00 h. 01 min."));
        assertThat(Util.getDurationString(61), equalTo("00 h. 02 min."));
        assertThat(Util.getDurationString(3600 - 1), equalTo("01 h. 00 min."));
    }

    @Test
    public void calculatesWorkoutsCorrectlyWithSameTimestamp() {

        List<UserLoc> userLoc = new ArrayList<>(Arrays.asList(
                new UserLoc(IMEI, "Test", Long.parseLong(TIME_STAMP), 54.724767, 25.298105, 502.0),
                new UserLoc(IMEI, "Test", Long.parseLong(TIME_STAMP), 54.722828, 25.298035, 500.0),
                new UserLoc(IMEI, "Test", Long.parseLong(TIME_STAMP), 54.721865, 25.296, 501.0)
        ));

        List<Workout> workouts = Util.getRecentWorkouts(userLoc, 10);
        assertThat(workouts.isEmpty(), equalTo(true));
    }

    @Test
    public void calculatesSingleWorkout() {
        List<UserLoc> userLoc = new ArrayList<>(Arrays.asList(
                new UserLoc(IMEI, "Test", Long.parseLong("1407350762000"), 54.724767, 25.298105, 502.0),
                new UserLoc(IMEI, "Test", Long.parseLong("1407350752000"), 54.722828, 25.298035, 500.0),
                new UserLoc(IMEI, "Test", Long.parseLong("1407350742000"), 54.721865, 25.296, 501.0)
        ));

        List<Workout> workouts = Util.getRecentWorkouts(userLoc, null);

        assertThat(workouts.size(), equalTo(1));
    }

    // Point A Latitude: 54.71722152 Longitude: 25.29514074 Altitude: 120
    // Point B Latitude: 54.72031996 Longitude: 25.30243635 Altitude: 117
    // Actual Distance: 0.581 km.
    @Test
    public void checksIfDistanceBetweenTwoPointsCalculatedCorrectly() {
        Double errorRange = 0.01;
        Double actualdistance = 0.581;
        Double calculatedDistance = Util.calculateDistance(54.71722152, 25.29514074, 120, 54.72031996, 25.30243635, 117);
        assertThat((actualdistance <= calculatedDistance + errorRange)
                && (actualdistance >= calculatedDistance - errorRange), equalTo(true));
    }

    @Test
    public void calculatesDistanceCorrectly() {
        List<UserLoc> userLoc = new ArrayList<>(Arrays.asList(
                new UserLoc(IMEI, "Test", Long.parseLong("1407350762000"), 54.724767, 25.298105, 502.0),
                new UserLoc(IMEI, "Test", Long.parseLong("1407350752000"), 54.722828, 25.298035, 500.0),
                new UserLoc(IMEI, "Test", Long.parseLong("1407350742000"), 54.721865, 25.296, 501.0)
        ));
        List<Workout> workouts = Util.getRecentWorkouts(userLoc, 1);
        String calculatedDistance = workouts.get(0).getDistance();
        assertThat(calculatedDistance, equalTo("0.385"));
    }
}
