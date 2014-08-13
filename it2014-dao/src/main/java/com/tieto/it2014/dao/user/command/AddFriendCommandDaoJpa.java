package com.tieto.it2014.dao.user.command;

import com.tieto.it2014.dao.user.FriendJpa;
import com.tieto.it2014.dao.user.UserJpa;
import com.tieto.it2014.domain.user.command.AddFriendCommand;
import com.tieto.it2014.domain.user.command.SaveUserCommand;
import com.tieto.it2014.domain.user.entity.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
