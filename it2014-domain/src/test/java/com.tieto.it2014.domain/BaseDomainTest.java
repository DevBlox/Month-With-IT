package com.tieto.it2014.domain;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:/domain-spring.xml",
        "classpath:/domain-test-spring.xml"
})
@TestExecutionListeners(listeners = {
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class})
public abstract class BaseDomainTest {

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

}
