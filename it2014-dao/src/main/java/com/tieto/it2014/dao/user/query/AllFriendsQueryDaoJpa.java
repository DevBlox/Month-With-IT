package com.tieto.it2014.dao.user.query;

import com.tieto.it2014.dao.user.UserJpa;
import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.domain.user.query.AllFriendsQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
public class AllFriendsQueryDaoJpa implements AllFriendsQuery.Dao {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public List<User> result() {
        String hql = "SELECT f from FriendJpa f INNER JOIN UserJpa u WHERE f.friendId = u.imei ORDER BY u.username";
        Query query = em.createQuery(hql, UserJpa.class);
        List results = query.getResultList();
        return null;
    }
}
