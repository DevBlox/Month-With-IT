package com.tieto.it2014.domain.workout.query;

import com.tieto.it2014.domain.user.entity.Workout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mantas on 20/08/14.
 */
@Component
public class CurrentMonthWorkoutsQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private Dao dao;

    public interface Dao extends Serializable {
        List<Workout> result(String imei);
    }

    public List<Workout> result(String userImei) {
        return dao.result(userImei);
    }
}
