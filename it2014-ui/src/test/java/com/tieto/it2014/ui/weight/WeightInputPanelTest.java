package com.tieto.it2014.ui.weight;

import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.ui.BaseWebTest;
import com.tieto.it2014.ui.session.UserSession;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class WeightInputPanelTest extends BaseWebTest {
    private WicketTester wicketTester;
    private UserSession userSession;
   
    @Mock
    private User user;
    
    @Before
    public void setUp() {
        wicketTester = createWicketTester();
        MockitoAnnotations.initMocks(this);
        userSession = (UserSession) wicketTester.getSession();
        System.out.println(userSession.get().getUser());
        userSession.setUser(user);
    }

//    @Test
//    public void shows_error_if_weight_is_zero() {
//
//        wicketTester.startComponentInPage(new WeightInputPanel("weightInput"));
//        FormTester formTester = wicketTester.newFormTester("weightInput:weightInputForm");
//        formTester.submit();
//        formTester.getForm().getFeedbackMessages();
//        wicketTester.assertErrorMessages("Please enter your weight");
//    }

}
