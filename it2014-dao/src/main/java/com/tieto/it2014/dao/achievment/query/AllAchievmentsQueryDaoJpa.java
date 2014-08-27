package com.tieto.it2014.dao.achievment.query;

import com.tieto.it2014.dao.JpaUtils;
import com.tieto.it2014.dao.achievment.AchievmentJpa;
import com.tieto.it2014.domain.achievment.entity.Achievment;
import com.tieto.it2014.domain.achievment.query.AllAchievmentsQuery;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AllAchievmentsQueryDaoJpa implements AllAchievmentsQuery.Dao {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    @Override
    public List<Achievment> result() {
        TypedQuery<AchievmentJpa> query = em.createQuery(
                "SELECT u FROM AchievmentJpa u", AchievmentJpa.class);
        return JpaUtils.toDomainList(query.getResultList());
    }

}
