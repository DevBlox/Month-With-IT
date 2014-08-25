package com.tieto.it2014.domain.user.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class AddFriendCommand implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private Dao dao;

    public interface Dao extends Serializable {

        void execute(String myid, String friendId);
    }

    public void execute(String myid, String friendId) {
        dao.execute(myid, friendId);
    }

}
