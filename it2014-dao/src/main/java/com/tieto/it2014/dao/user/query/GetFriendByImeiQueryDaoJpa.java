package com.tieto.it2014.dao.user.query;

import com.tieto.it2014.dao.user.FriendJpa;
import com.tieto.it2014.domain.user.query.GetFriendByImeiQuery;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class GetFriendByImeiQueryDaoJpa implements GetFriendByImeiQuery.Dao {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public Integer result(String userId, String friendId) {
        TypedQuery<FriendJpa> query = em.createQuery(
                "SELECT u FROM FriendJpa u where u.userId = :userId and u.friendId = :friendId",
                FriendJpa.class);
        query.setParameter("userId", userId);
        query.setParameter("friendId", friendId);

        return query.getResultList().get(0).getId();
    }
}
