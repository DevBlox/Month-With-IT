package com.tieto.it2014.dao.user.query;

import com.tieto.it2014.dao.JpaUtils;
import com.tieto.it2014.dao.user.UserJpa;
import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.domain.user.query.AllUsersQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class AllUsersQueryDaoJpa implements AllUsersQuery.Dao {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public List<User> result() {
        TypedQuery<UserJpa> query = em.createQuery(
                "SELECT u FROM UserJpa u", UserJpa.class);
        return JpaUtils.toDomainList(query.getResultList());
    }

}
