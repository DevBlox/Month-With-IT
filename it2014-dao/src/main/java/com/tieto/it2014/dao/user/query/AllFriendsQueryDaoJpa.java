package com.tieto.it2014.dao.user.query;

import com.tieto.it2014.dao.user.FriendJpa;
import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.domain.user.query.AllFriendsQuery;
import com.tieto.it2014.domain.user.query.GetUserByIdQuery;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AllFriendsQueryDaoJpa implements AllFriendsQuery.Dao {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private GetUserByIdQuery.Dao getUserById;

    @Override
    @Transactional(readOnly = true)
    public List<User> result(String id) {
        List<User> lst = new ArrayList<User>();
        TypedQuery<FriendJpa> query = em.createQuery(
                "SELECT u FROM FriendJpa u where u.userId = :id", FriendJpa.class);
        query.setParameter("id", id);
        for (FriendJpa ob : query.getResultList()) {
            lst.add(getUserById.resultOrNull(ob.getFriendId()));
        }
        return lst;
    }
}
