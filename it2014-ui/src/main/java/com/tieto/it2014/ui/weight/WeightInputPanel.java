package com.tieto.it2014.ui.weight;

import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.domain.weight.command.AddWeightCommand;
import com.tieto.it2014.domain.weight.entity.Weight;
import com.tieto.it2014.ui.session.UserSession;
import java.sql.Timestamp;
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
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.time.Time.*;
import static org.apache.wicket.util.time.Time.now;

public class WeightInputPanel extends Panel {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private AddWeightCommand addWeightCommand;

    private Form weightInputForm;
    private String enteredWeight;
    private TextField weightInputField;
    Timestamp currentTimestamp;
    User user = UserSession.get().getUser();
    String id = user.id;

    public WeightInputPanel(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        java.util.Date date = new java.util.Date();
        currentTimestamp = new Timestamp(date.getTime());
        weightInputForm = new Form("weightInputForm");
        weightInputForm.add(new FeedbackPanel("weightInputFeedback"));
        weightInputField = new TextField("weightInput", new PropertyModel(this, "enteredWeight"));
        weightInputField.setRequired(true);
        weightInputField.add(new AjaxEventBehavior("keyup") {

            @Override
            protected void onEvent(AjaxRequestTarget target) {
                String enteredValue = weightInputField.getInput();
                if (!enteredValue.contains(".")) {
                    return;
                }
                enteredValue = enteredValue.replace("\\", "\\\\'");
                enteredValue = enteredValue.replace("'", "\\'");
                int plusIndex = enteredValue.indexOf(".");
                String doubleDotChecker = enteredValue.substring(plusIndex + 1);
                if (doubleDotChecker.startsWith(".")) {
                    String enteredValueDoubleDot = enteredValue.substring(0, plusIndex + 1);
                    target.appendJavaScript(
                            " document.getElementById('weightInput').value = '" + enteredValueDoubleDot + "';"
                    );
                    return;
                }
                int enteredLength = enteredValue.length();
                if (enteredLength - 3 >= plusIndex) {
                    enteredValue = enteredValue.substring(0, plusIndex + 2);
                    target.appendJavaScript(
                            " document.getElementById('weightInput').value = '" + enteredValue + "';"
                    );
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
                if (enteredWeight.matches("[0-9]+.[0-9]+") || enteredWeight.matches("[0-9]+")) {
                    Float savingWeight = Float.parseFloat(enteredWeight);
                    Float zeroForCheck = Float.parseFloat("0");
                    if (savingWeight.equals(zeroForCheck)) {
                        weightInputForm.error("Your weight is probably more than 0. Please check again");
                        return; 
                    }
                    System.out.println("svoris" + savingWeight + "laikas: " + currentTimestamp + " Useris:" + id);
                    addWeightCommand.execute(new Weight(savingWeight, id, 0, currentTimestamp.getTime()));
                } else {
                    weightInputForm.error("Wrong weight format");
                }
            }

        };
    }

}
