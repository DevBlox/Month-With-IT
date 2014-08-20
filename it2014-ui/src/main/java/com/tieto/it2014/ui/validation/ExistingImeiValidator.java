/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tieto.it2014.ui.validation;

import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.domain.user.query.GetUserByIdQuery;
import java.io.Serializable;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.IErrorMessageSource;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidationError;
import org.apache.wicket.validation.IValidator;

/**
 *
 * @author pc3
 */
public class ExistingImeiValidator implements IValidator<String> {

    private static final long serialVersionUID = 1L;

    //@SpringBean
    private final GetUserByIdQuery getUserByIdQuery;

    private User user;
    private String imei;

    public ExistingImeiValidator(GetUserByIdQuery query) {
        getUserByIdQuery = query;
    }

    @Override
    public void validate(IValidatable<String> validatable) {

        imei = validatable.getValue();
        user = getUserByIdQuery.resultOrNull(imei);

        if (user != null) {
            validatable.error(new IValidationError() {
                private static final long serialVersionUID = 1L;

                @Override
                public Serializable getErrorMessage(IErrorMessageSource messageSource) {
                    return "IMEI number is already in use!";
                }
            });
        }
    }
}
