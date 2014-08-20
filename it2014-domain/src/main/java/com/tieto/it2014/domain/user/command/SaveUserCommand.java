package com.tieto.it2014.domain.user.command;

import com.tieto.it2014.domain.user.entity.User;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveUserCommand implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private Dao dao;

    public interface Dao extends Serializable {
        void execute(User user);
    }

    public void execute(User user) {
        dao.execute(user);
    }

}
