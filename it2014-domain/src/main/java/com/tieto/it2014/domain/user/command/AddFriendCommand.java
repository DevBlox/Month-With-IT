package com.tieto.it2014.domain.user.command;

import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
