package com.tieto.it2014.domain.workout.query;

import com.tieto.it2014.domain.user.entity.UserLoc;
import java.io.Serializable;
import java.util.List;

import com.tieto.it2014.domain.user.entity.Workout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WorkoutsQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private Dao dao;

    private Long imei;
    
    public interface Dao extends Serializable {
        List<Workout> result(String imei);
    }

    public List<Workout> result(String imei) {
        return dao.result(imei);
    }

}
