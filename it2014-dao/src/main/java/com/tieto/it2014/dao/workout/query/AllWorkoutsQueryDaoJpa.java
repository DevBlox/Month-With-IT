package com.tieto.it2014.dao.workout.query;

import com.tieto.it2014.dao.JpaUtils;
import com.tieto.it2014.dao.workout.WorkoutJpa;
import com.tieto.it2014.domain.Util.Util;
import com.tieto.it2014.domain.user.entity.UserLoc;
import com.tieto.it2014.domain.user.entity.Workout;
import com.tieto.it2014.domain.workout.query.WorkoutsQuery;

import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Component
public class AllWorkoutsQueryDaoJpa implements WorkoutsQuery.Dao {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public List<Workout> result(String imei, Integer limit) {
        TypedQuery<WorkoutJpa> query;
        List<UserLoc> users;
        
        if (null == imei) {

            query = em.createQuery(
                    "SELECT w FROM WorkoutJpa w", WorkoutJpa.class);
            users = JpaUtils.toDomainList(query.getResultList());

        } else {

            query = em.createQuery(
                    "SELECT w FROM WorkoutJpa w WHERE w.phoneNumber = :imei", WorkoutJpa.class)
                    .setParameter("imei", imei);
            users = JpaUtils.toDomainList(query.getResultList());

        }

        return Util.getRecentWorkouts(users, limit);
        
    }

}
