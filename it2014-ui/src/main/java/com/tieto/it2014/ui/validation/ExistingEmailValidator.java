/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tieto.it2014.ui.validation;

import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.domain.user.query.GetUserByEmailQuery;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.IErrorMessageSource;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidationError;
import org.apache.wicket.validation.IValidator;

import java.io.Serializable;

/**
 *
 * @author pc3
 */
public class ExistingEmailValidator implements IValidator<String> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private final GetUserByEmailQuery getUserByEmailQuery;

    private User user;
    private String email;

    public ExistingEmailValidator(GetUserByEmailQuery query) {
        getUserByEmailQuery = query;
    }

    @Override
    public void validate(IValidatable<String> validatable) {

        email = validatable.getValue();
        user = getUserByEmailQuery.result(email);

        if (user != null) {
            validatable.error(new IValidationError() {
                private static final long serialVersionUID = 1L;

                @Override
                public Serializable getErrorMessage(IErrorMessageSource messageSource) {
                    return "Email is already in use!";
                }
            });
        }
    }
}
