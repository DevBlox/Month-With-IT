package com.tieto.it2014.ui;

import com.tieto.it2014.domain.user.query.GetUserByIdQuery;
import com.tieto.it2014.ui.validation.ExistingImeiValidator;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.tester.WicketTester;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidationError;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.ServletTestExecutionListener;



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
