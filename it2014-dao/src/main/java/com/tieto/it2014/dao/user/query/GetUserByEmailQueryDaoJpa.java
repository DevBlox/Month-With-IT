package com.tieto.it2014.dao.user.query;

import com.tieto.it2014.dao.JpaUtils;
import com.tieto.it2014.dao.user.UserJpa;
import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.domain.user.query.GetUserByEmailQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Component
public class GetUserByEmailQueryDaoJpa implements GetUserByEmailQuery.Dao {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public User result(String email) {
        TypedQuery<UserJpa> query = em.createQuery(
                "SELECT u FROM UserJpa u where u.email = :email",
                UserJpa.class);
        query.setParameter("email", email);
        return JpaUtils.singleResultOrNull(query);
    }

}
