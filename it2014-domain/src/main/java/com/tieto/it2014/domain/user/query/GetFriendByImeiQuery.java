package com.tieto.it2014.domain.user.query;

import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetFriendByImeiQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private Dao dao;

    public interface Dao extends Serializable {

        Integer result(String userId, String friendId);
    }

    public Integer result(String userId, String friendId) {
        return dao.result(userId, friendId);
    }
}
