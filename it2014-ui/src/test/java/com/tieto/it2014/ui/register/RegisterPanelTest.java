package com.tieto.it2014.ui.register;

import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.domain.user.query.GetUserByEmailQuery;
import com.tieto.it2014.ui.BaseWebTest;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class RegisterPanelTest extends BaseWebTest {

    private WicketTester wicketTester;

    private static final String EMAIL = "audrius.siliunas@tietocamp.eu";
    private static final String REGISTER_FORM_NAME = "register";
    private static final String REGISTER_FORM_TESTER_NAME = "register:registerForm";
    private static final String INPUT_USERNAME_FIELD_NAME = "inputUserName";
    private static final String INPUT_EMAIL_FIELD_NAME = "inputEmail";
    private static final String INPUT_PASSWORD_FIELD_NAME = "inputPassword";
    private static final String REPEAT_PASSWORD_FIELD_NAME = "repeatPassword";
    private static final String IMEI_IS_REQUIRED_MSG = "IMEI is required";
    private static final String REPEAT_PASSWORD_MSG = "Please repeat password";
    private static final String USERNAME = "Audrius Siliunas";
    private static final String SECOND_EMAIL = "adudu@valio.lt";
    private static final String PASSWORD = "slaptaisaugu";

    @Autowired
    private GetUserByEmailQuery getUserByEmailQuery;

    @Before
    public void setUp() {
        wicketTester = createWicketTester();
    }

    @Test
    public void checksIfEmailsAreEqual() {

        User user = getUserByEmailQuery.result(EMAIL);
        assertThat(EMAIL, equalTo(user.email));
    }

    @Test
    public void showsErrorIfImeiFieldIsEmpty() {
        wicketTester.startComponentInPage(new RegisterPanel(REGISTER_FORM_NAME));
        FormTester formTester = wicketTester.newFormTester(REGISTER_FORM_TESTER_NAME);
        formTester.setValue(INPUT_USERNAME_FIELD_NAME, "Artur Černiavskij");
        formTester.setValue(INPUT_EMAIL_FIELD_NAME, "artur.cerniavskij@tieto.com");
        formTester.setValue(INPUT_PASSWORD_FIELD_NAME, "pass@word");
        formTester.setValue(REPEAT_PASSWORD_FIELD_NAME, "pass@word");
        formTester.submit();
        wicketTester.assertErrorMessages(IMEI_IS_REQUIRED_MSG);
    }

    @Test
    public void showsErrorIfAllFieldsIsEmpty() {
        wicketTester.startComponentInPage(new RegisterPanel(REGISTER_FORM_NAME));
        FormTester formTester = wicketTester.newFormTester(REGISTER_FORM_TESTER_NAME);
        formTester.submit();
        wicketTester.assertErrorMessages("User name is required", "Password is required", REPEAT_PASSWORD_MSG, "E-mail is required", IMEI_IS_REQUIRED_MSG);
    }

    @Test
    public void showsErrorIfPasswordIsNotRepeated() {
        wicketTester.startComponentInPage(new RegisterPanel(REGISTER_FORM_NAME));
        FormTester formTester = wicketTester.newFormTester(REGISTER_FORM_TESTER_NAME);
        formTester.setValue(INPUT_USERNAME_FIELD_NAME, USERNAME);
        formTester.setValue(INPUT_EMAIL_FIELD_NAME, SECOND_EMAIL);
        formTester.setValue(INPUT_PASSWORD_FIELD_NAME, PASSWORD);
        formTester.setValue("imei", "4566");
        formTester.submit();
        wicketTester.assertErrorMessages(REPEAT_PASSWORD_MSG);
    }

    @Test
    public void showsErrorIfEmailIsInUse() {
        wicketTester.startComponentInPage(new RegisterPanel(REGISTER_FORM_NAME));
        FormTester formTester = wicketTester.newFormTester(REGISTER_FORM_TESTER_NAME);
        formTester.setValue(INPUT_USERNAME_FIELD_NAME, USERNAME);
        formTester.setValue(INPUT_EMAIL_FIELD_NAME, EMAIL);
        formTester.setValue(INPUT_PASSWORD_FIELD_NAME, PASSWORD);
        formTester.setValue(REPEAT_PASSWORD_FIELD_NAME, PASSWORD);
        formTester.setValue("imei", "4566");
        formTester.submit();
        wicketTester.assertErrorMessages("Email is already in use!");
    }

    @Test
    public void showsErrorIfImeiIsInUse() {
        wicketTester.startComponentInPage(new RegisterPanel(REGISTER_FORM_NAME));
        FormTester formTester = wicketTester.newFormTester(REGISTER_FORM_TESTER_NAME);
        formTester.setValue(INPUT_USERNAME_FIELD_NAME, USERNAME);
        formTester.setValue(INPUT_EMAIL_FIELD_NAME, "random@audrius.lt");
        formTester.setValue(INPUT_PASSWORD_FIELD_NAME, PASSWORD);
        formTester.setValue(REPEAT_PASSWORD_FIELD_NAME, PASSWORD);
        formTester.setValue("imei", "356871044631608");
        formTester.submit();
        wicketTester.assertErrorMessages("IMEI number is already in use!");
    }

    @Test
    public void showsErrorIfPasswordMissmatch() {
        wicketTester.startComponentInPage(new RegisterPanel(REGISTER_FORM_NAME));
        FormTester formTester = wicketTester.newFormTester(REGISTER_FORM_TESTER_NAME);
        formTester.setValue(INPUT_USERNAME_FIELD_NAME, USERNAME);
        formTester.setValue(INPUT_EMAIL_FIELD_NAME, SECOND_EMAIL);
        formTester.setValue(INPUT_PASSWORD_FIELD_NAME, PASSWORD);
        formTester.setValue(REPEAT_PASSWORD_FIELD_NAME, "ne4564");
        formTester.setValue("imei", "4566");
        formTester.submit();
        wicketTester.assertErrorMessages("Password does not match");
    }
}
