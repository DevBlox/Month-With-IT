package com.tieto.it2014.dao.achievment.query;

import com.tieto.it2014.dao.JpaUtils;
import com.tieto.it2014.dao.achievment.AchievmentJpa;
import com.tieto.it2014.domain.achievment.entity.Achievement;
import com.tieto.it2014.domain.achievment.query.AllAchievementsQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class AllAchievmentsQueryDaoJpa implements AllAchievementsQuery.Dao {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    @Override
    public List<Achievement> result() {
        TypedQuery<AchievmentJpa> query = em.createQuery(
                "SELECT u FROM AchievmentJpa u", AchievmentJpa.class);
        return JpaUtils.toDomainList(query.getResultList());
    }

}
