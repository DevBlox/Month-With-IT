package com.tieto.it2014.dao.user.command;

import com.tieto.it2014.dao.user.FriendJpa;
import com.tieto.it2014.domain.user.command.AddFriendCommand;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class AddFriendCommandDaoJpa implements AddFriendCommand.Dao {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void execute(String myid, String friendID) {
        em.merge(new FriendJpa(myid, friendID));
    }

}
