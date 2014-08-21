package com.tieto.it2014.ui;

import com.tieto.it2014.domain.user.query.GetUserByIdQuery;
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
    private GetUserByIdQuery query;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void abc() {
        Mockito.when(
                query.resultOrNull(Mockito.anyString()))
                .thenReturn(null);
        ExistingImeiValidator v = new ExistingImeiValidator(query);
        IValidatable<String> a = new IValidatable<String>() {

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
        v.validate(a);
    }

}
