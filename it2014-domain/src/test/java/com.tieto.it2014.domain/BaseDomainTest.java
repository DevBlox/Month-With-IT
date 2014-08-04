package com.tieto.it2014.domain;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:/domain-spring.xml",
        "classpath:/domain-test-spring.xml"
})
public abstract class BaseDomainTest {

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

}
