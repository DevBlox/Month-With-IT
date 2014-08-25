package com.tieto.it2014.ui.register;

import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.domain.user.query.GetUserByEmailQuery;
import com.tieto.it2014.ui.BaseWebTest;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RegisterPanelTest extends BaseWebTest {
    private WicketTester wicketTester;
    @Autowired
    private GetUserByEmailQuery getUserByEmailQuery;

    @Before
    public void setUp() {
        wicketTester = createWicketTester();
    }

    @Test
    public void checksIfEmailsAreEqual() {
        String email = "audrius.siliunas@tietocamp.eu";
        User user = getUserByEmailQuery.result(email);
        assertThat(email, equalTo(user.email));
    }

    @Test
    public void showsErrorIfImeiFieldIsEmpty() {
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
    public void showsErrorIfAllFieldsIsEmpty() {
        wicketTester.startComponentInPage(new RegisterPanel("register"));
        FormTester formTester = wicketTester.newFormTester("register:registerForm");
        formTester.submit();
        wicketTester.assertErrorMessages("User name is required", "Password is required", "Please repeat password", "E-mail is required", "IMEI is required");
    }

    @Test
    public void showsErrorIfPasswordIsNotRepeated() {
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
    public void showsErrorIfEmailIsInUse() {
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
    public void showsErrorIfImeiIsInUse() {
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
    public void showsErrorIfPasswordMissmatch() {
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
