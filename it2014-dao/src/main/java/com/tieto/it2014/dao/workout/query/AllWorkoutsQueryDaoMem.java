package com.tieto.it2014.dao.workout.query;

import com.tieto.it2014.dao.user.query.*;
import com.tieto.it2014.dao.user.UserEntityRepository;
import com.tieto.it2014.dao.user.UserEntity;
import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.domain.user.query.AllUsersQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class AllWorkoutsQueryDaoMem implements AllUsersQuery.Dao {

    private static final long serialVersionUID = 1L;

    @Autowired
    private UserEntityRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<User> result() {
        List<User> users = new ArrayList<>();
        for (UserEntity entity : repository.all()) {
            users.add(entity.toUser());
        }
        return users;
    }

}
