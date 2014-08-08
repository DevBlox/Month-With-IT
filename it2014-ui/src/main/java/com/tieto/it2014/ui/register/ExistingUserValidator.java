/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tieto.it2014.ui.register;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.validation.IFormValidator;

/**
 *
 * @author pc3
 */
public class ExistingUserValidator implements IFormValidator {

    @Override
    public FormComponent<?>[] getDependentFormComponents() {
        return null;
    }

    @Override
    public void validate(Form<?> form) {
        
    }
    
}
