package com.tieto.it2014.ui;

import com.tieto.it2014.domain.user.query.GetUserByEmailQuery;
import com.tieto.it2014.domain.user.query.GetUserByIdQuery;
import com.tieto.it2014.ui.validation.ExistingEmailValidator;
import com.tieto.it2014.ui.validation.ExistingImeiValidator;
import org.apache.wicket.model.IModel;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidationError;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class RegisterPanelTest {

    @Mock
    private GetUserByIdQuery getUserByIdQuery;

    @Mock
    private GetUserByEmailQuery getUserByEmailQuery;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void checksIfImeiIsNotInUse() {
        Mockito.when(
                getUserByIdQuery.resultOrNull(Mockito.anyString()))
                .thenReturn(null);
        ExistingImeiValidator imeiValidator = new ExistingImeiValidator(getUserByIdQuery);
        IValidatable<String> validatableUserImei = new IValidatable<String>() {

            @Override
            public String getValue() {
                return "";
            }

            @Override
            public void error(IValidationError error) {
            }

            @Override
            public boolean isValid() {
                return true;
            }

            @Override
            public IModel<String> getModel() {
                return null;
            }
        };
        imeiValidator.validate(validatableUserImei);
    }

    @Test
    public void checksIfEmailIsNotInUse() {
        Mockito.when(
                getUserByEmailQuery.result(Mockito.anyString()))
                .thenReturn(null);
        ExistingEmailValidator emailValidator = new ExistingEmailValidator(getUserByEmailQuery);
        IValidatable<String> validatableUserEmail = new IValidatable<String>() {

            @Override
            public String getValue() {
                return "";
            }

            @Override
            public void error(IValidationError error) {
            }

            @Override
            public boolean isValid() {
                return true;
            }

            @Override
            public IModel<String> getModel() {
                return null;
            }
        };
        emailValidator.validate(validatableUserEmail);
    }

}
