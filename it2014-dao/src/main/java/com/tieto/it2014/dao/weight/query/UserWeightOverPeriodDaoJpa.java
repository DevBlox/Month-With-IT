package com.tieto.it2014.dao.weight.query;

import com.tieto.it2014.dao.JpaUtils;
import com.tieto.it2014.dao.weight.WeightJpa;
import com.tieto.it2014.domain.weight.entity.Weight;
import com.tieto.it2014.domain.weight.query.UserWeightOverPeriod;
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

        return JpaUtils.toDomainList(query.getResultList());
    }
}
