package com.tieto.it2014.dao.workout;

import com.tieto.it2014.dao.user.*;
import java.util.List;

public interface WorkoutEntityRepository {

    List<WorkoutEntity> all();
    WorkoutEntity create(WorkoutEntity workout);
    WorkoutEntity update(WorkoutEntity workout);
    WorkoutEntity merge(WorkoutEntity workout);
    WorkoutEntity find(Long id);
    void delete(WorkoutEntity workout);
    void delete(Long id);

}
