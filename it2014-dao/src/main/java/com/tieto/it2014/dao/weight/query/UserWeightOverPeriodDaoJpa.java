package com.tieto.it2014.dao.weight.query;

import com.tieto.it2014.dao.JpaUtils;
import com.tieto.it2014.dao.weight.WeightJpa;
import com.tieto.it2014.domain.weight.entity.Weight;
import com.tieto.it2014.domain.weight.query.UserWeightOverPeriod;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

import static com.tieto.it2014.domain.weight.WeightChartType.*;

@Component
public class UserWeightOverPeriodDaoJpa implements UserWeightOverPeriod.Dao {

    private static final long serialVersionUID = 1L;

    private long oneHour = 3600000;

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
        List<Weight> filtered = new ArrayList<>();
        Weight lastWeight;

        switch (type) {
            case BUTTON_TYPE_DAY:
                long hourStart;
                long hourFinish = start;

                List<Weight> hourList;
                while (hourFinish < finish) {
                    hourStart = hourFinish;
                    hourFinish += oneHour;

                    hourList = new ArrayList<>();
                    for (Weight weight : list) {
                        if (weight.timeStamp > hourStart && weight.timeStamp < hourFinish) {
                            hourList.add(weight);
                        }
                    }
                }

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
