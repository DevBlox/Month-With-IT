package com.tieto.it2014.dao.achievment.query;

import com.tieto.it2014.dao.JpaUtils;
import com.tieto.it2014.dao.achievment.UserAchievmentJpa;
import com.tieto.it2014.domain.achievment.entity.UserAchievement;
import com.tieto.it2014.domain.achievment.query.UserAchievementsQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class UserAchievmentsQueryDaoJpa implements UserAchievementsQuery.Dao {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    @Override
    public List<UserAchievement> result(String imei) {
        TypedQuery<UserAchievmentJpa> query = em.createQuery(
                "SELECT u FROM UserAchievmentJpa u where u.imei = :imei", UserAchievmentJpa.class);
        query.setParameter("imei", imei);
        return JpaUtils.toDomainList(query.getResultList());
    }

}
