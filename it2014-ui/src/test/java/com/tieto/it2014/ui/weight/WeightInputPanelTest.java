package com.tieto.it2014.ui.weight;

import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.ui.BaseWebTest;
import com.tieto.it2014.ui.session.UserSession;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
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
        userSession.setUser(user);
    }

    @Test
    public void showsErrorIfWeightIsNotEntered() {

        wicketTester.startComponentInPage(new WeightInputPanel("weightInput"));
        FormTester formTester = wicketTester.newFormTester("weightInput:weightInputForm");
        formTester.submit();
        formTester.getForm().getFeedbackMessages();
        wicketTester.assertErrorMessages("Please enter your weight");
    }

}
