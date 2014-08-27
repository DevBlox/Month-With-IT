package com.tieto.it2014.dao.workout.query;

import com.tieto.it2014.dao.JpaUtils;
import com.tieto.it2014.dao.workout.WorkoutJpa;
import com.tieto.it2014.domain.util.Util;
import com.tieto.it2014.domain.user.entity.UserLoc;
import com.tieto.it2014.domain.user.entity.Workout;
import com.tieto.it2014.domain.workout.query.CurrentMonthWorkoutsQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Calendar;
import java.util.List;

/**
 * Created by mantas on 20/08/14.
 */
@Component
public class CurrentMonthWorkoutsQueryDaoJpa implements CurrentMonthWorkoutsQuery.Dao {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public List<Workout> result(String imei) {

        Calendar cal=Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH,Calendar.getInstance().getActualMinimum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, Calendar.getInstance().getActualMinimum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, Calendar.getInstance().getActualMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, Calendar.getInstance().getActualMinimum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, Calendar.getInstance().getActualMinimum(Calendar.MILLISECOND));

        TypedQuery<WorkoutJpa> query = em.createQuery(
                "SELECT w FROM WorkoutJpa w WHERE w.phoneNumber = :imei AND w.timeStamp > :timeStamp", WorkoutJpa.class)
                .setParameter("imei", imei)
                .setParameter("timeStamp", cal.getTime().getTime());
        List<UserLoc> locations = JpaUtils.toDomainList(query.getResultList());

        return Util.getRecentWorkouts(locations, null);
    }
}
