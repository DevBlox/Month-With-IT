package com.tieto.it2014.dao.achievment;

import com.tieto.it2014.dao.BaseDaoTest;
import com.tieto.it2014.domain.achievment.entity.UserAchievementNoDate;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class UserAchievmentNoDateJpaTest extends BaseDaoTest {

    @Test
    public void createsAchievmentJpaObject() {
        UserAchievmentNoDateJpa userAchievmentNoDateJpa = new UserAchievmentNoDateJpa();
        assertNotNull(userAchievmentNoDateJpa);
    }

    @Test
    public void createAchievmentFromAchievmentJpa() {
        UserAchievmentNoDateJpa userAchievmentNoDateJpa = new UserAchievmentNoDateJpa();
        UserAchievementNoDate userAchievementNoDate = userAchievmentNoDateJpa.toDomain();
        assertNotNull(userAchievementNoDate);
    }

    @Test
    public void gettersReturnsNullIfUnasigned() {
        UserAchievmentNoDateJpa userAchievmentNoDateJpa = new UserAchievmentNoDateJpa();
        assertEquals(0, userAchievmentNoDateJpa.getAchievementId());
        assertNull(userAchievmentNoDateJpa.getDate());
        assertEquals(0, userAchievmentNoDateJpa.getId());
        assertNull(userAchievmentNoDateJpa.getImei());
    }

    @Test
    public void settersSetsData() {
        UserAchievmentNoDateJpa userAchievmentNoDateJpa = new UserAchievmentNoDateJpa();
        userAchievmentNoDateJpa.setDate(10L);
        userAchievmentNoDateJpa.setImei("10");
        assertEquals(10L, (long)userAchievmentNoDateJpa.getDate());
        assertEquals("10", userAchievmentNoDateJpa.getImei());
    }

    @Test
    public void runsConstructorWithParams() {
        UserAchievementNoDate userAchievementNoDate = new UserAchievementNoDate(1, 1, 10L, "1", true, false);
        UserAchievmentNoDateJpa userAchievmentNoDateJpa = new UserAchievmentNoDateJpa(userAchievementNoDate);
        assertNotNull(userAchievmentNoDateJpa);
    }

}
