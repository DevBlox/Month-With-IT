package com.tieto.it2014.ui.achievments;

import com.tieto.it2014.domain.user.entity.Workout;
import com.tieto.it2014.domain.workout.query.WorkoutsQuery;
import com.tieto.it2014.ui.session.UserSession;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AchievementsChecker implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final double distanceAchievement = 1;

    @Autowired
    private WorkoutsQuery workoutQuery;
    
    
    
    public boolean oneWorkoutDistanceAchievement() {
        String userImei = UserSession.get().getUser().imei;
        boolean isCompleted = false;
        List<Workout> workouts = workoutQuery.result(userImei, null);
        if (workouts.isEmpty()) {
            return isCompleted;
        }
        Calendar cal = Calendar.getInstance();
        for (Workout wo : workouts) {
            if (wo.getDistanceDouble() >= distanceAchievement) {
                isCompleted = true;
            }
            // galima atiduoti timestamp kuomet jis buvo pasiektas
            cal.setTimeInMillis(wo.getStartTimeTimestamp());
        }

        return isCompleted;
    }

//    private List<Workout> workouts() {
//
//        final Integer ALL_WORKOUTS = 100;
//        String userImei2 = UserSession.get().getUser().imei;
//        System.out.println(userImei2);
//
//        return workoutQuery.result(userImei2, userImei2, ALL_WORKOUTS);
//
//    }
}
