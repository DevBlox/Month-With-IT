package com.tieto.it2014.dao.weight.query;

import com.tieto.it2014.dao.JpaUtils;
import com.tieto.it2014.dao.weight.WeightJpa;
import com.tieto.it2014.domain.weight.entity.Weight;
import com.tieto.it2014.domain.weight.query.WeightQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by mantas on 20/08/14.
 */
@Component
public class AllUserWeightQueryDaoJpa implements WeightQuery.Dao {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public List<Weight> result(String imei) {
        TypedQuery< WeightJpa> query = em.createQuery(
                "SELECT w FROM WeightJpa w WHERE w.userId = :imei ORDER BY w.timeStamp ASC", WeightJpa.class)
                .setParameter("imei", imei);

        return JpaUtils.toDomainList(query.getResultList());
    }
}
