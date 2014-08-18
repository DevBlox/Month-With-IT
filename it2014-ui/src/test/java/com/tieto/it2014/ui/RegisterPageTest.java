package com.tieto.it2014.ui;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
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
public class RegisterPageTest {

    @Autowired
    private WebApplication application;

    private WicketTester tester;

    @Before
    public void set_up() {
        tester = new WicketTester(application);
    }

    @Test
    public void renders_successfuly() {
        tester.startPage(RegisterPage.class);
        tester.assertRenderedPage(RegisterPage.class);
    }
}
