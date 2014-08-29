package com.tieto.it2014.domain.achievment.command;

import com.tieto.it2014.domain.achievment.entity.UserAchievementNoDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class AddAchievementCommand implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private Dao dao;

    public interface Dao extends Serializable {

        void execute(UserAchievementNoDate userAchievementNoDate);
    }

    public void execute(UserAchievementNoDate userAchievementNoDate) {
        dao.execute(userAchievementNoDate);
    }

}
