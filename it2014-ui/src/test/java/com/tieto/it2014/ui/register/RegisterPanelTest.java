package com.tieto.it2014.ui.register;

import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.domain.user.query.GetUserByEmailQuery;
import com.tieto.it2014.ui.BaseWebTest;
import org.apache.wicket.spring.injection.annot.SpringBean;
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

    @Test
    public void shows_error_if_all_fields_is_empty() {
        wicketTester.startComponentInPage(new RegisterPanel("register"));
        FormTester formTester = wicketTester.newFormTester("register:registerForm");
        formTester.submit();
        wicketTester.assertErrorMessages("User name is required", "Password is required", "Please repeat password", "E-mail is required", "IMEI is required");
    }

    @Test
    public void shows_error_if_password_is_not_repeated() {
        wicketTester.startComponentInPage(new RegisterPanel("register"));
        FormTester formTester = wicketTester.newFormTester("register:registerForm");
        formTester.setValue("inputUserName", "Audrius Siliunas");
        formTester.setValue("inputEmail", "adudu@valio.lt");
        formTester.setValue("inputPassword", "slaptaisaugu");
        formTester.setValue("imei", "4566");
        formTester.submit();
        wicketTester.assertErrorMessages("Please repeat password");
    }

    @Test
    public void shows_error_if_email_is_inUse() {
        wicketTester.startComponentInPage(new RegisterPanel("register"));
        FormTester formTester = wicketTester.newFormTester("register:registerForm");
        formTester.setValue("inputUserName", "Audrius Siliunas");
        formTester.setValue("inputEmail", "audrius.siliunas@tietocamp.eu");
        formTester.setValue("inputPassword", "slaptaisaugu");
        formTester.setValue("repeatPassword", "slaptaisaugu");
        formTester.setValue("imei", "4566");
        formTester.submit();
        wicketTester.assertErrorMessages("Email is already in use!");
    }

    @Test
    public void shows_error_if_imei_is_inUse() {
        wicketTester.startComponentInPage(new RegisterPanel("register"));
        FormTester formTester = wicketTester.newFormTester("register:registerForm");
        formTester.setValue("inputUserName", "Audrius Siliunas");
        formTester.setValue("inputEmail", "random@audrius.lt");
        formTester.setValue("inputPassword", "slaptaisaugu");
        formTester.setValue("repeatPassword", "slaptaisaugu");
        formTester.setValue("imei", "356871044631608");
        formTester.submit();
        wicketTester.assertErrorMessages("IMEI number is already in use!");
    }

    @Test
    public void shows_error_if_password_missmatch() {
        wicketTester.startComponentInPage(new RegisterPanel("register"));
        FormTester formTester = wicketTester.newFormTester("register:registerForm");
        formTester.setValue("inputUserName", "Audrius Siliunas");
        formTester.setValue("inputEmail", "adudu@valio.lt");
        formTester.setValue("inputPassword", "slaptaisaugu");
        formTester.setValue("repeatPassword", "ne4564");
        formTester.setValue("imei", "4566");
        formTester.submit();
        wicketTester.assertErrorMessages("Password does not match");
    }

}
