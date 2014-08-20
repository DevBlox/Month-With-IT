package com.tieto.it2014.ui.weight;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.cycle.RequestCycle;

public class WeightInputPanel extends Panel {

    private static final long serialVersionUID = 1L;

    private Form weightInputForm;
    private String enteredWeight;
    private TextField weightInputField;

    public WeightInputPanel(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        weightInputForm = new Form("weightInputForm");
        weightInputForm.add(new FeedbackPanel("weightInputFeedback"));
        weightInputField = new TextField("weightInput");

        weightInputField.add(new AjaxEventBehavior("keyup") {

            @Override
            protected void onEvent(AjaxRequestTarget target) {
                System.out.println("paspausta:" + weightInputField.getInput());

                if (weightInputField.getInput().contains(".")) {
                    System.out.println("Radau taska");
                    String enteredValue = weightInputField.getInput();
                    int plusIndex = enteredValue.indexOf(".");
                    int pluslenght = enteredValue.length();

                    if (pluslenght - 3 >= plusIndex) {
                        System.out.println("virsyti skaiciai po kablelio");
                        enteredValue = enteredValue.substring(0, plusIndex + 2);
                        System.out.println("pataisyta reiksme" + enteredValue);
                        target.appendJavaScript("alert('SUSIMOVEI');"
//                                + " document.getElementById('weightInput').value = " + enteredValue + ";"
//                                + "        alert('SUSIMOVEI');"
                        );

                    }

                }

            }

        });

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
