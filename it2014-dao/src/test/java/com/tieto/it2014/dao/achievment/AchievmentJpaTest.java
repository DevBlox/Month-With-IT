package com.tieto.it2014.dao.achievment;

import com.tieto.it2014.dao.BaseDaoTest;
import com.tieto.it2014.domain.achievment.entity.Achievement;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by mantas on 29/08/14.
 */
public class AchievmentJpaTest extends BaseDaoTest {

    @Test
    public void createsAchievmentJpaObject() {
        AchievmentJpa achievmentJpa = new AchievmentJpa();
        assertNotNull(achievmentJpa);
    }

    @Test
    public void createAchievmentFromAchievmentJpa() {
        AchievmentJpa achievmentJpa = new AchievmentJpa();
        Achievement achievement = achievmentJpa.toDomain();
        assertNotNull(achievement);
    }

    @Test
    public void getterReturnsNullIfUnasigned() {
        AchievmentJpa achievmentJpa = new AchievmentJpa();
        assertNull(achievmentJpa.getName());
    }

}
