/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tieto.it2014.ui.validation;

import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.domain.user.query.AllUsersQuery;
import java.io.Serializable;
import java.util.List;
import org.apache.wicket.validation.IErrorMessageSource;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidationError;
import org.apache.wicket.validation.IValidator;

/**
 *
 * @author pc3
 */
public class ExistingUserValidator implements IValidator<String> {

    private final List<User> users;
    private String username;
    private boolean found = false;

    public ExistingUserValidator(AllUsersQuery query) {
        users = query.result();
    }

    @Override
    public void validate(IValidatable<String> validatable) {
        username = validatable.getValue();

        for (User user : users) {
            if (user.username.equals(username)) {
                found = true;
            }
        }
        if (found) {
            validatable.error(new IValidationError() {
                @Override
                public Serializable getErrorMessage(IErrorMessageSource messageSource) {
                    return "Username is already in use!";
                }
            });
        }
    }
}
