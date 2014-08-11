package com.tieto.it2014.dao.workout.query;

import com.tieto.it2014.dao.workout.WorkoutEntity;
import com.tieto.it2014.dao.workout.WorkoutEntityRepository;
import com.tieto.it2014.domain.Util.Util;
import com.tieto.it2014.domain.user.entity.UserLoc;
import com.tieto.it2014.domain.user.entity.Workout;
import com.tieto.it2014.domain.workout.query.WorkoutsQuery;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AllWorkoutsQueryDaoMem implements WorkoutsQuery.Dao {

    private static final long serialVersionUID = 1L;

    @Autowired
    private WorkoutEntityRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Workout> result(String imei) {
        List<UserLoc> users = new ArrayList<>();
        
        if (null == imei) {
            List<WorkoutEntity> we = repository.all();
            for(WorkoutEntity wel : repository.all()) users.add(wel.toUserLock());
        } else {
            List<WorkoutEntity> we = repository.byUser(imei);
            for(WorkoutEntity wel : repository.byUser(imei)) users.add(wel.toUserLock());
        }
        
        return Util.getRecentWorkouts(users, 100);
    }

}
