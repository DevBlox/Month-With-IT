package com.tieto.it2014.dao.workout.query;

import com.tieto.it2014.dao.workout.WorkoutEntity;
import com.tieto.it2014.dao.workout.WorkoutEntityRepository;
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

    @Autowired
    private WorkoutEntityRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<UserLoc> result() {
        List<UserLoc> users = new ArrayList<>();
        for (WorkoutEntity entity : repository.all()) {
            users.add(entity.toWorkout());
        }
        return users;
    }

}
