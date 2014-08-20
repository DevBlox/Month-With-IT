package com.tieto.it2014.ui.register;

import com.tieto.it2014.ui.BaseWebTest;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

public class RegisterPanelTest extends BaseWebTest {
    private WicketTester wicketTester;

    @Before
    public void setUp() {
        wicketTester = createWicketTester();
    }

    @Test
    public void shows_error_if_imei_field_is_empty() {
        wicketTester.startComponentInPage(new RegisterPanel("register"));
        FormTester formTester = wicketTester.newFormTester("register:registerForm");
        formTester.setValue("inputUserName", "Artur Černiavskij");
        formTester.setValue("inputEmail", "artur.cerniavskij@tieto.com");
        formTester.setValue("inputPassword", "pass@word");
        formTester.setValue("repeatPassword", "pass@word");
        formTester.submit();
        wicketTester.assertErrorMessages("IMEI is required");
    }
}
