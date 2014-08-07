package com.tieto.it2014.dao.workout.query;

import com.tieto.it2014.dao.workout.WorkoutEntity;
import com.tieto.it2014.dao.workout.WorkoutEntityRepository;
import com.tieto.it2014.domain.Util.Util;
import com.tieto.it2014.domain.user.entity.UserLoc;
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

    @Autowired
    private WorkoutEntityRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<UserLoc> result() {
        List<UserLoc> users = new ArrayList<>();
        List<WorkoutEntity> we = repository.all();
        for (int i = 0; i < we.size()-2 ; i++) {
            WorkoutEntity lc1 = we.get(i);
            WorkoutEntity lc2 = we.get(i+1);
            sec = (int)Util.calculateDuration(lc2.getTimeStamp(), lc1.getTimeStamp());
            if (sec < 300 && lc2.getPhoneNumber().equals(lc1.getPhoneNumber()) && !lc2.equals(we.get(we.size()-1))) {
                totalSec += sec;
                k++;
            } else {
                System.out.println("Total time: " + totalSec + ", WorkoutID: " + ++workoutId + " " + lc1.getPhoneNumber() + ", TS: " + k);
                totalSec = 0;
                k = 0;
            }
            users.add(lc1.toWorkout());
        }
        users.add(we.get(we.size() - 1).toWorkout());
        return users;
    }

}
