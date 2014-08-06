package com.tieto.it2014.domain.user.command;

import com.tieto.it2014.domain.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class SaveUserCommand implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private Dao dao;

    public interface Dao extends Serializable {
        Long execute(User user);
    }

    public void execute(User user) {
        dao.execute(user);
    }

}
