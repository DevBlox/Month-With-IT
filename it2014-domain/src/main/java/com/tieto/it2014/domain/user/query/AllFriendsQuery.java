package com.tieto.it2014.domain.user.query;

import com.tieto.it2014.domain.user.entity.User;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AllFriendsQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private Dao dao;

    public interface Dao extends Serializable {

        List<User> result(String id);
    }

    public List<User> result(String id) {
        if (id == null) {
            return null;
        } else {
            return dao.result(id);
        }
    }
}
