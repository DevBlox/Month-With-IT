package com.tieto.it2014.dao.user.command;

import com.tieto.it2014.dao.user.FriendJpa;
import com.tieto.it2014.domain.user.command.DeleteFriendCommand;
import com.tieto.it2014.domain.user.query.GetFriendByImeiQuery;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DeleteFriendCommandDaoJpa implements DeleteFriendCommand.Dao {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private GetFriendByImeiQuery.Dao getFriendByImeiQueryDao;

    @Override
    @Transactional
    public void execute(final String userId, final String friendId) {
        Integer id = getFriendByImeiQueryDao.result(userId, friendId);
        FriendJpa friend = em.find(FriendJpa.class, id);
        em.remove(friend);
    }

}
