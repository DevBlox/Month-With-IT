package com.tieto.it2014.ui.workout.details;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by mantas on 25/08/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/ui-spring.xml"})
public class GMapPanelTest {

    @Test
    public void emptyTest() {
        assertTrue(true);
    }

}
