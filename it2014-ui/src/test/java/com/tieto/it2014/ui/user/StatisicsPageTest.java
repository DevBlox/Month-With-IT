package com.tieto.it2014.ui.user;

import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.ui.BaseWebTest;
import com.tieto.it2014.ui.error.ErrorPage404;
import com.tieto.it2014.ui.session.UserSession;
import org.apache.wicket.util.tester.WicketTester;
import static org.eclipse.jetty.util.log.Log.ignore;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@Ignore
public class StatisicsPageTest extends BaseWebTest {

    private WicketTester tester;
    private UserSession userSession;
    
    @Mock
    private User user;


    @Before
    public void setUp() {
        tester = createWicketTester();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void rendersSuccessfullyWithLoggedInUser() {
        userSession = (UserSession) tester.getSession();
        userSession.setUser(user);
        tester.startPage(StatisticsPage.class);
        tester.assertRenderedPage(StatisticsPage.class);
    }

    @Test
    public void rendersSuccessfullyWithNoLoggedInUser() {
        tester.startPage(StatisticsPage.class);
        tester.assertRenderedPage(ErrorPage404.class);
    }

}
