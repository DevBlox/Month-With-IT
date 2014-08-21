package com.tieto.it2014.ui.user;

import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.ui.BaseWebTest;
import com.tieto.it2014.ui.error.ErrorPage404;
import com.tieto.it2014.ui.session.UserSession;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@Ignore
public class TopPageTest extends BaseWebTest {
    
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
    public void renders_successfully_with_no_logged_in_user() {
        tester.startPage(TopPage.class);
        tester.assertRenderedPage(ErrorPage404.class);
    }
    
    @Test
    public void renders_successfully_with_logged_in_user() {
        userSession = (UserSession) tester.getSession();
        userSession.setUser(user);
        tester.startPage(TopPage.class);
        tester.assertRenderedPage(TopPage.class);
    }
}
