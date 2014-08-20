package com.tieto.it2014.ui.weight;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;

public class WeightInputPanel extends Panel {
    
    private static final long serialVersionUID = 1L;
     
    private Form weightInputForm;
    private String enteredWeight;
    
    public WeightInputPanel(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
    
        weightInputForm = new Form("weightInputForm");
        TextField weightInputField = new TextField("weightInput", new PropertyModel(this, enteredWeight));
        weightInputForm.add(weightInputField);
        weightInputForm.add(initRegisterButton("buttonWeight"));
        add(weightInputForm);
        
    
    }
    
     private Component initRegisterButton(String wicketId) {
        return new Button(wicketId) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onSubmit() {
                System.out.println("svoris" + enteredWeight);
            }

        };
    }
    
    

}
