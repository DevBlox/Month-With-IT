package com.tieto.it2014.ui.achievments;

import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.domain.user.entity.Workout;
import com.tieto.it2014.domain.user.query.AllFriendsQuery;
import com.tieto.it2014.domain.workout.query.WorkoutsQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

@Component
public class AchievementsChecker implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final int DISTANCE_KM_ONE_WORKOUT_LIMIT = 1;
    private static final int MEANING_OF_LIFE = 42;
    private static final int BEND_YOUR_KNEES_LIMIT = 10;
    private static final int DURATION_SEC_ONE_WORKOUT_LIMIT = 900;
    private static final int SPEED_KM_H_ONE_WORKOUT_LIMIT = 5;
    private static final int COMPETE_WITH_CHESTER = 10;
    private static final double DISTANCE_KM_ALL_WORKOUTS_LIMIT = 50;
    private static final double AROUND_THE_WORLD = 40000;
    private static final double SEASIDE = 300;
    private static final int DURATION_SEC_ALL_WORKOUS_LIMIT = 18000;
    private static final int OLD_RUNNER = 432000;
    private static final int FRIENDS_NUMBER = 5;
    private static final int FAMILY_RUNNER = 20;
    private static final int WORKOUTS_LIMIT = 10;
    private static final int USAIN_BOLT_LIMIT = 365;

    @Autowired
    private WorkoutsQuery workoutQuery;

    @Autowired
    private AllFriendsQuery allFriendsQuery;

    public boolean checksAchievementById(int id, String userImei) {
        switch (id) {
            case 1:
                return oneWorkoutDistanceAchievement(DISTANCE_KM_ONE_WORKOUT_LIMIT, userImei);
            case 2:
                return oneWorkoutDurationAchievement(DURATION_SEC_ONE_WORKOUT_LIMIT, userImei);
            case 3:
                return oneWorkoutSpeedAchievement(SPEED_KM_H_ONE_WORKOUT_LIMIT, userImei);
            case 4:
                return allWorkoutDistanceAchievement(DISTANCE_KM_ALL_WORKOUTS_LIMIT, userImei);
            case 5:
                return allWorkoutDurationAchievement(DURATION_SEC_ALL_WORKOUS_LIMIT, userImei);
            case 6:
                return haveMoreOrEqualFriends(FRIENDS_NUMBER, userImei);
            case 7:
                return madeMoreOrEqualWorkouts(WORKOUTS_LIMIT, userImei);
            case 8:
                return oneWorkoutDistanceAchievement(BEND_YOUR_KNEES_LIMIT, userImei);
            case 9:
                return oneWorkoutSpeedAchievement(COMPETE_WITH_CHESTER, userImei);
            case 10:
                return madeMoreOrEqualWorkouts(USAIN_BOLT_LIMIT, userImei);
            case 11:
                return allWorkoutDurationAchievement(OLD_RUNNER, userImei);
            case 12:
                return haveMoreOrEqualFriends(FAMILY_RUNNER, userImei);
            case 13:
                return allWorkoutDistanceAchievement(SEASIDE, userImei);
            case 14:
                return oneWorkoutDistanceAchievement(MEANING_OF_LIFE, userImei);
            case 15:
                return allWorkoutDistanceAchievement(AROUND_THE_WORLD, userImei);
            default:
                return false;
        }
    }

    public boolean oneWorkoutDistanceAchievement(int distance, String userImei) {
        List<Workout> workouts = workoutQuery.result(userImei, null);
        if (workouts.isEmpty()) {
            return false;
        }
        Calendar cal = Calendar.getInstance();
        for (Workout wo : workouts) {
            if (wo.getDistanceDouble() >= distance) {
                return true;
            }
            // galima atiduoti timestamp kuomet jis buvo pasiektas
            cal.setTimeInMillis(wo.getStartTimeTimestamp());
        }

        return false;
    }

    public boolean oneWorkoutSpeedAchievement(double speed, String userImei) {
        List<Workout> workouts = workoutQuery.result(userImei, null);
        if (workouts.isEmpty()) {
            return false;
        }
        for (Workout wo : workouts) {
            if ((wo.getDistanceDouble() / ((double) wo.getDurationInt() / 3600)) >= speed) {
                return true;
            }
        }

        return false;
    }

    public boolean oneWorkoutDurationAchievement(int durationMili, String userImei) {
        List<Workout> workouts = workoutQuery.result(userImei, null);
        if (workouts.isEmpty()) {
            return false;
        }
        for (Workout wo : workouts) {
            if (wo.getDurationInt() >= durationMili) {
                return true;
            }
        }

        return false;
    }

    public boolean allWorkoutDistanceAchievement(double distanceKm, String userImei) {
        List<Workout> workouts = workoutQuery.result(userImei, null);
        if (workouts.isEmpty()) {
            return false;
        }
        double totalDist = 0;
        for (Workout wo : workouts) {
            totalDist += wo.getDistanceDouble();
        }
        if (totalDist >= distanceKm) {
            return true;
        }
        return false;
    }

    public boolean allWorkoutDurationAchievement(int durationMili, String userImei) {
        List<Workout> workouts = workoutQuery.result(userImei, null);
        if (workouts.isEmpty()) {
            return false;
        }
        double totalDuration = 0;
        for (Workout wo : workouts) {
            totalDuration += wo.getDurationInt();
        }
        if (totalDuration >= durationMili) {
            return true;
        }
        return false;
    }

    public boolean haveMoreOrEqualFriends(int friendsNumber, String userImei) {
        List<User> friends = allFriendsQuery.result(userImei);
        if (friends.isEmpty()) {
            return false;
        }
        if (friends.size() >= friendsNumber) {
            return true;
        }
        return false;
    }

    public boolean madeMoreOrEqualWorkouts(int workoutCount, String userImei) {
        List<Workout> workouts = workoutQuery.result(userImei, null);
        if (workouts.isEmpty()) {
            return false;
        }
        if (workouts.size() >= workoutCount) {
            return true;
        }
        return false;
    }

}
