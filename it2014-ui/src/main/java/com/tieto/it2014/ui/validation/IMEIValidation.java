/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tieto.it2014.ui.validation;

import java.io.Serializable;
import java.util.regex.Pattern;
import org.apache.wicket.validation.IErrorMessageSource;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidationError;
import org.apache.wicket.validation.IValidator;

/**
 *
 * @author pc3
 */
public class IMEIValidation implements IValidator<String> {

    private final String pattern_string = "\\d{15}";
    private final Pattern pattern;
    private String password;

    public IMEIValidation() {
        pattern = Pattern.compile(pattern_string);
    }

    @Override
    public void validate(IValidatable<String> validatable) {
        password = validatable.getValue();

        if (!pattern.matcher(password).matches()) {
            validatable.error(new IValidationError() {

                @Override
                public Serializable getErrorMessage(IErrorMessageSource messageSource) {
                    return "IMEI number is only composed out of digits!";
                }

            });
        }
    }

}
