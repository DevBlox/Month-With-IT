package com.tieto.it2014.dao.achievment.command;

import com.tieto.it2014.dao.achievment.UserAchievmentNoDateJpa;
import com.tieto.it2014.domain.achievment.command.AddAchievementCommand;
import com.tieto.it2014.domain.achievment.entity.UserAchievementNoDate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class AddAchievementNoDateCommandDaoJpa implements AddAchievementCommand.Dao {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void execute(UserAchievementNoDate userAchievementNoDate) {
        em.merge(new UserAchievmentNoDateJpa(userAchievementNoDate));
    }

}
