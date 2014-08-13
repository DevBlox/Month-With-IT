package com.tieto.it2014.domain.user.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class DeleteFriendCommand implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private Dao dao;

    public interface Dao extends Serializable {

        void execute(String userId, String friendId);
    }

    public void execute(String userId, String friendId) {
        dao.execute(userId, friendId);
    }

}
