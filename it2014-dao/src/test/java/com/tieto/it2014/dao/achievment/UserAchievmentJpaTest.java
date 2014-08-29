package com.tieto.it2014.dao.achievment;

import com.tieto.it2014.dao.BaseDaoTest;
import com.tieto.it2014.domain.achievment.entity.UserAchievement;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class UserAchievmentJpaTest extends BaseDaoTest {

    @Test
    public void createsUserAchievmentJpaObject() {
        UserAchievmentJpa userAchievmentJpa = new UserAchievmentJpa();
        assertNotNull(userAchievmentJpa);
    }

    @Test
    public void createUserAchievmentFromAchievmentJpa() {
        UserAchievmentJpa userAchievmentJpa = new UserAchievmentJpa();
        UserAchievement userAchievement = userAchievmentJpa.toDomain();
        assertNotNull(userAchievement);
    }

}
