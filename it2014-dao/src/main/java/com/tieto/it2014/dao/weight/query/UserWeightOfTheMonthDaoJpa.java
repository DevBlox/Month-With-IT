package com.tieto.it2014.dao.weight.query;

import com.tieto.it2014.dao.JpaUtils;
import com.tieto.it2014.dao.weight.WeightJpa;
import com.tieto.it2014.domain.util.Util;
import com.tieto.it2014.domain.weight.entity.Weight;
import com.tieto.it2014.domain.weight.query.UserWeightOfTheMonth;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Calendar;
import java.util.List;

/**
 * Created by mantas on 21/08/14.
 */
@Component
public class UserWeightOfTheMonthDaoJpa implements UserWeightOfTheMonth.Dao {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public List<Weight> result(String imei) {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, Calendar.getInstance().get(Calendar.MONTH));
        cal.set(Calendar.DAY_OF_MONTH, Calendar.getInstance().getActualMinimum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, Calendar.getInstance().getActualMinimum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, Calendar.getInstance().getActualMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, Calendar.getInstance().getActualMinimum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, Calendar.getInstance().getActualMinimum(Calendar.MILLISECOND));

        long time = cal.getTimeInMillis();

        TypedQuery< WeightJpa> query = em.createQuery(
                "SELECT w FROM WeightJpa w WHERE w.userId = :imei AND w.timeStamp > :time ORDER BY w.timeStamp ASC", WeightJpa.class)
                .setParameter("imei", imei).setParameter("time", time);

        return Util.getFilteredOnePerDayList(JpaUtils.toDomainList(query.getResultList()));
    }
}
