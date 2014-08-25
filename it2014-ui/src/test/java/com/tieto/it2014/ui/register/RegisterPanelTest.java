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

    private static final String email = "audrius.siliunas@tietocamp.eu";
    private static final String registerFormName = "register";
    private static final String registerFormTesterName = "register:registerForm";
    private static final String inputUserNameFieldName = "inputUserName";
    private static final String inputEmailFieldName = "inputEmail";
    private static final String inputPasswordFieldName = "inputPassword";
    private static final String repeatPasswordFieldName = "repeatPassword";
    private static final String imeiIsRequiredMsg = "IMEI is required";
    private static final String repeatPasswordMsg = "Please repeat password";
    private static final String userName = "Audrius Siliunas";
    private static final String secondEmail = "adudu@valio.lt";
    private static final String password = "slaptaisaugu";

    @Autowired
    private GetUserByEmailQuery getUserByEmailQuery;

    @Before
    public void setUp() {
        wicketTester = createWicketTester();
    }

    @Test
    public void checksIfEmailsAreEqual() {
        User user = getUserByEmailQuery.result(email);
        assertThat(email, equalTo(user.email));
    }

    @Test
    public void showsErrorIfImeiFieldIsEmpty() {
        wicketTester.startComponentInPage(new RegisterPanel(registerFormName));
        FormTester formTester = wicketTester.newFormTester(registerFormTesterName);
        formTester.setValue(inputUserNameFieldName, "Artur Černiavskij");
        formTester.setValue(inputEmailFieldName, "artur.cerniavskij@tieto.com");
        formTester.setValue(inputPasswordFieldName, "pass@word");
        formTester.setValue(repeatPasswordFieldName, "pass@word");
        formTester.submit();
        wicketTester.assertErrorMessages(imeiIsRequiredMsg);
    }

    @Test
    public void showsErrorIfAllFieldsIsEmpty() {
        wicketTester.startComponentInPage(new RegisterPanel(registerFormName));
        FormTester formTester = wicketTester.newFormTester(registerFormTesterName);
        formTester.submit();
        wicketTester.assertErrorMessages("User name is required", "Password is required", repeatPasswordMsg, "E-mail is required", imeiIsRequiredMsg);
    }

    @Test
    public void showsErrorIfPasswordIsNotRepeated() {
        wicketTester.startComponentInPage(new RegisterPanel(registerFormName));
        FormTester formTester = wicketTester.newFormTester(registerFormTesterName);
        formTester.setValue(inputUserNameFieldName, userName);
        formTester.setValue(inputEmailFieldName, secondEmail);
        formTester.setValue(inputPasswordFieldName, password);
        formTester.setValue("imei", "4566");
        formTester.submit();
        wicketTester.assertErrorMessages(repeatPasswordMsg);
    }

    @Test
    public void showsErrorIfEmailIsInUse() {
        wicketTester.startComponentInPage(new RegisterPanel(registerFormName));
        FormTester formTester = wicketTester.newFormTester(registerFormTesterName);
        formTester.setValue(inputUserNameFieldName, userName);
        formTester.setValue(inputEmailFieldName, email);
        formTester.setValue(inputPasswordFieldName, password);
        formTester.setValue(repeatPasswordFieldName, password);
        formTester.setValue("imei", "4566");
        formTester.submit();
        wicketTester.assertErrorMessages("Email is already in use!");
    }

    @Test
    public void showsErrorIfImeiIsInUse() {
        wicketTester.startComponentInPage(new RegisterPanel(registerFormName));
        FormTester formTester = wicketTester.newFormTester(registerFormTesterName);
        formTester.setValue(inputUserNameFieldName, userName);
        formTester.setValue(inputEmailFieldName, "random@audrius.lt");
        formTester.setValue(inputPasswordFieldName, password);
        formTester.setValue(repeatPasswordFieldName, password);
        formTester.setValue("imei", "356871044631608");
        formTester.submit();
        wicketTester.assertErrorMessages("IMEI number is already in use!");
    }

    @Test
    public void showsErrorIfPasswordMissmatch() {
        wicketTester.startComponentInPage(new RegisterPanel(registerFormName));
        FormTester formTester = wicketTester.newFormTester(registerFormTesterName);
        formTester.setValue(inputUserNameFieldName, userName);
        formTester.setValue(inputEmailFieldName, secondEmail);
        formTester.setValue(inputPasswordFieldName, password);
        formTester.setValue(repeatPasswordFieldName, "ne4564");
        formTester.setValue("imei", "4566");
        formTester.submit();
        wicketTester.assertErrorMessages("Password does not match");
    }
}
