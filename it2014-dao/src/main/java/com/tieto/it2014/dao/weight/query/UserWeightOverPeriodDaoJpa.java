package com.tieto.it2014.dao.weight.query;

import com.tieto.it2014.dao.JpaUtils;
import com.tieto.it2014.dao.weight.WeightJpa;
import static com.tieto.it2014.domain.weight.WeightChartType.BUTTON_TYPE_DAY;
import static com.tieto.it2014.domain.weight.WeightChartType.BUTTON_TYPE_MONTH;
import static com.tieto.it2014.domain.weight.WeightChartType.BUTTON_TYPE_QUARTER;
import static com.tieto.it2014.domain.weight.WeightChartType.BUTTON_TYPE_TIME_DEPENDING;
import static com.tieto.it2014.domain.weight.WeightChartType.BUTTON_TYPE_YEAR;
import com.tieto.it2014.domain.weight.entity.Weight;
import com.tieto.it2014.domain.weight.query.UserWeightOverPeriod;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserWeightOverPeriodDaoJpa implements UserWeightOverPeriod.Dao {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public List<Weight> result(long start, long finish, String imei, int type) {
        TypedQuery<WeightJpa> query = em.createQuery(
                "SELECT w FROM WeightJpa w WHERE w.timeStamp > :start AND w.timeStamp < :finish AND w.userId = :userId",
                WeightJpa.class)
                .setParameter("start", start)
                .setParameter("finish", finish)
                .setParameter("userId", imei);

        List<Weight> list = JpaUtils.toDomainList(query.getResultList());

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, Calendar.getInstance().getActualMinimum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, Calendar.getInstance().getActualMinimum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, Calendar.getInstance().getActualMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, Calendar.getInstance().getActualMinimum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, Calendar.getInstance().getActualMinimum(Calendar.MILLISECOND));

        switch (type) {
            case BUTTON_TYPE_DAY:
                int day_min = Calendar.getInstance().getActualMinimum(Calendar.DAY_OF_MONTH);
                int day_max = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);

                System.out.println("Minimum: " + day_min);
                System.out.println("Maximum: " + day_max);

                break;

            case BUTTON_TYPE_MONTH:

                break;

            case BUTTON_TYPE_QUARTER:

                break;

            case BUTTON_TYPE_TIME_DEPENDING:

                break;

            case BUTTON_TYPE_YEAR:

                break;
        }

        return list;
    }
}
