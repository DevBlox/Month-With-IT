package com.tieto.it2014.domain.workout.query;

import com.tieto.it2014.domain.user.entity.Workout;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WorkoutsQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private Dao dao;

    public interface Dao extends Serializable {
        List<Workout> result();
    }

    public List<Workout> result() {
        return dao.result();
    }

}
