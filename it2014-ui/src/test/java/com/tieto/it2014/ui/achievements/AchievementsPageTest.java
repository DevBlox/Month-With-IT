package com.tieto.it2014.ui.achievements;

import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.ui.BaseWebTest;
import com.tieto.it2014.ui.session.UserSession;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class AchievementsPageTest extends BaseWebTest {

    private WicketTester tester;
    private UserSession session;

    @Mock
    private User user;

    @Before
    public void setUp() {
        tester = createWicketTester();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void renders_successfuly_with_no_parameters_no_logged_user() {
        tester.startPage(AchievmentsPage.class);
        tester.assertRenderedPage(AchievmentsPage.class);
    }

    @Test
    public void renders_successfuly_with_parameters_no_logged_user() {
        PageParameters param = new PageParameters();
        param.add("userId", new Model<>("314686543"));

        tester.startPage(AchievmentsPage.class, param);
        tester.assertRenderedPage(AchievmentsPage.class);
    }

    @Test
    public void renders_successfuly_with_no_parameters_with_logged_user() {
        session = (UserSession) tester.getSession();
        session.setUser(user);

        tester.startPage(AchievmentsPage.class);
        tester.assertRenderedPage(AchievmentsPage.class);
    }

    @Test
    public void renders_successfuly_with_parameters_with_logged_user() {
        session = (UserSession) tester.getSession();
        session.setUser(user);

        PageParameters param = new PageParameters();
        param.add("userId", new Model<>("3649845656"));

        tester.startPage(AchievmentsPage.class, param);
        tester.assertRenderedPage(AchievmentsPage.class);
    }

}
