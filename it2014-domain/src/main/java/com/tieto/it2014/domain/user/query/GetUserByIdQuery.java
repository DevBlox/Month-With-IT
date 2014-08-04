package com.tieto.it2014.domain.user.query;

import com.tieto.it2014.domain.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class GetUserByIdQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private Dao dao;

    public interface Dao extends Serializable {
        User resultOrNull(Long id);
    }

    public User resultOrNull(Long id) {
        return dao.resultOrNull(id);
    }

}