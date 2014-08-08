package com.tieto.it2014.ui.register;

import com.tieto.it2014.domain.user.command.CreateUserCommand;
import com.tieto.it2014.domain.user.command.SaveUserCommand;
import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.ui.HomePage;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.apache.wicket.validation.validator.StringValidator;

public class RegisterPanel extends Panel {

    private static final long serialVersionUID = 1L;

    
    
    public RegisterPanel(String wicketid) {
        super(wicketid);
    }
    
    private User user;

    @SpringBean
    private CreateUserCommand createUser;
    
    @SpringBean
    private SaveUserCommand saveUser;

    @Override
    protected void onInitialize() {
        
        super.onInitialize();
        user = createUser.getUser();
        Form form = new Form("registerForm");
        form.add(new FeedbackPanel("registerFeedback"));
        form.add(new TextField("inputUserName", new PropertyModel(user, "username"))
                .setRequired(true)
                .add(new StringValidator(3, 30))
        );
        form.add(new TextField("inputPassword", new PropertyModel(user, "password"))
                .setRequired(true)
                .add(new StringValidator(5, 30))
        );
        form.add(new TextField("inputEmail", new PropertyModel(user, "email"))
                .setRequired(true)
                .add(EmailAddressValidator.getInstance())
        );
        form.add(new TextField("imei", new PropertyModel(user, "imei"))
            .setRequired(true));
        form.add(initRegisterButton("registerButton"));
        add(form);
    }
    
    private Component initRegisterButton(String wicketId) {
        return new Button(wicketId) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onSubmit() {
                saveUser.execute(user);
                setResponsePage(HomePage.class);
            }
        };
    }
}
