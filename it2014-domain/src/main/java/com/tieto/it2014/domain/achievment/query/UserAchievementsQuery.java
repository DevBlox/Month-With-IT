package com.tieto.it2014.domain.achievment.query;

import com.tieto.it2014.domain.achievment.entity.UserAchievement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class UserAchievementsQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private Dao dao;

    public interface Dao extends Serializable {

        List<UserAchievement> result(String imei);
    }

    public List<UserAchievement> result(String imei) {
        return dao.result(imei);
    }

}
