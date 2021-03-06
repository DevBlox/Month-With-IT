package com.tieto.it2014.dao.user.query;

import com.tieto.it2014.dao.user.UserJpa;
import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.domain.user.query.GetUserByIdQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static com.tieto.it2014.dao.JpaUtils.toDomainEntity;

@Component
public class GetUserByIdQueryDaoJpa implements GetUserByIdQuery.Dao {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public User resultOrNull(String id) {
        return id == null ? null : toDomainEntity(em.find(UserJpa.class, id));
    }

}
