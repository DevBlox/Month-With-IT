package com.tieto.it2014.dao.workout.query;

import com.tieto.it2014.dao.workout.WorkoutEntity;
import com.tieto.it2014.dao.workout.WorkoutEntityRepository;
import com.tieto.it2014.domain.Util.Util;
import com.tieto.it2014.domain.user.entity.UserLoc;
import com.tieto.it2014.domain.user.entity.Workout;
import com.tieto.it2014.domain.workout.query.WorkoutsQuery;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AllWorkoutsQueryDaoMem implements WorkoutsQuery.Dao {

    private static final long serialVersionUID = 1L;
    int workoutId = 0;
    int sec = 0;
    int totalSec = 0;
    int k = 0;
    Long start;
    Long end;
    Boolean st = true;

    @Autowired
    private WorkoutEntityRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Workout> result() {
        List<Workout> users = new ArrayList<>();
        List<WorkoutEntity> we = repository.all();
        for (int i = 0; i < we.size()-2 ; i++) {
            WorkoutEntity lc1 = we.get(i);
            WorkoutEntity lc2 = we.get(i+1);
            if (st) {
                end = lc1.getTimeStamp();
                st = false;
            }
            sec = (int)Util.calculateDuration(lc2.getTimeStamp(), lc1.getTimeStamp());
            if (sec < 300 && lc2.getPhoneNumber().equals(lc1.getPhoneNumber()) && !lc2.equals(we.get(we.size()-1))) {
                totalSec += sec;
                k++;
            } else {
                start = lc2.getTimeStamp();
                st = true;
                System.out.println("Total time: " + totalSec + ", WorkoutID: " + ++workoutId + " " + start + " " + end);
                totalSec = 0;
                k = 0;
            }
        }
        users.add(new Workout(1L, "123456789", 10L, 20L, 30L, 40L, "User"));
        users.add(new Workout(2L, "123456789", 10L, 20L, 30L, 40L, "User2"));
        users.add(new Workout(3L, "123456789", 10L, 20L, 30L, 40L, "User3"));
        return users;
    }

}
