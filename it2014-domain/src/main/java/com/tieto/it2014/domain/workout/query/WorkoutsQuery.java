package com.tieto.it2014.domain.workout.query;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.domain.user.entity.Workout;
import com.tieto.it2014.domain.user.query.AllFriendsQuery;
import java.io.Serializable;
import java.security.AccessControlException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WorkoutsQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private Dao dao;

    @Autowired
    private AllFriendsQuery.Dao friendsDao;

    public interface Dao extends Serializable {

        List<Workout> result(String imei, Integer limit);
    }

    public List<Workout> result(String userImei, Integer limit) {
        return dao.result(userImei, limit);
    }

    public List<Workout> result(String userImei, final String friendImei, Integer limit) {
        if (userImei.equals(friendImei)) {
            return result(userImei, limit);
        }

        List<User> friends = friendsDao.result(userImei);
        Optional<User> friend = Iterables.tryFind(friends, new Predicate<User>() {
            @Override
            public boolean apply(User t) {
                return friendImei.equals(t.getImei());
            }
        });
        if (friend.isPresent()) {
            return dao.result(friendImei, limit);
        }
        throw new AccessControlException("Sorry, access denied!");
    }

}
