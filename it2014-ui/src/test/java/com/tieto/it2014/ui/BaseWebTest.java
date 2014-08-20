package com.tieto.it2014.ui;

import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.ServletTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/ui-spring.xml"})
@TestExecutionListeners(listeners = {
    ServletTestExecutionListener.class,
    DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class})
public class BaseWebTest extends AbstractTransactionalJUnit4SpringContextTests {
    protected WicketTester createWicketTester() {
        return new WicketTester(new TestWicketApplication());
    }

    public class TestWicketApplication extends WicketApplication {
        @Override
        protected void initSpring() {
            getComponentInstantiationListeners().add(new SpringComponentInjector(this, applicationContext));
        }
    }

    @Test
    public void empty_test() {
    }
}
