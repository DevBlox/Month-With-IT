package com.tieto.it2014.domain.user.query;

import com.tieto.it2014.domain.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
            return new ArrayList<>();
        } else {
            return dao.result(id);
        }
    }
}
