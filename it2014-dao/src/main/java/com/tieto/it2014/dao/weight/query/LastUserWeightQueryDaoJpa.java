package com.tieto.it2014.dao.weight.query;

import com.tieto.it2014.dao.JpaUtils;
import com.tieto.it2014.dao.weight.WeightJpa;
import com.tieto.it2014.domain.weight.entity.Weight;
import com.tieto.it2014.domain.weight.query.LastWeightQuery;
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
public class LastUserWeightQueryDaoJpa implements LastWeightQuery.Dao {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public Weight result(String imei) {
        TypedQuery<WeightJpa> query = em.createQuery(
                "SELECT w FROM WeightJpa w WHERE w.userId = :imei ORDER BY w.timeStamp DESC", WeightJpa.class)
                .setMaxResults(1)
                .setParameter("imei", imei);

        return query.getResultList().isEmpty() ? null : query.getResultList().get(0).toDomain();
    }
}
