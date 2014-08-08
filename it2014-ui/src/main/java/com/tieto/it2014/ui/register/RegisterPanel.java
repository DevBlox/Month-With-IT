package com.tieto.it2014.ui.register;

import com.tieto.it2014.domain.user.command.CreateUserCommand;
import com.tieto.it2014.domain.user.command.SaveUserCommand;
import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.domain.user.query.AllUsersQuery;
import com.tieto.it2014.ui.HomePage;
import java.util.List;
import java.util.Objects;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.EqualPasswordInputValidator;
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
    private Form form;

    @SpringBean
    private CreateUserCommand createUser;

    @SpringBean
    private SaveUserCommand saveUser;

    @SpringBean
    private AllUsersQuery allUsersQuery;

    @Override
    protected void onInitialize() {

        super.onInitialize();
        user = createUser.getUser();
        form = new Form("registerForm");
        PasswordTextField password = new PasswordTextField("inputPassword", new PropertyModel(user, "password"));
        PasswordTextField repeatPassword = new PasswordTextField("repeatPassword", new PropertyModel(user, "password"));

        form.add(new FeedbackPanel("registerFeedback"));
        form.add(new TextField("inputUserName", new PropertyModel(user, "username"))
                .setRequired(true)
                .add(new StringValidator(3, 30))
        );
        form.add(password
                .setRequired(true)
                .add(new StringValidator(5, 30))
        );
        form.add(repeatPassword
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
        form.add(new EqualPasswordInputValidator(password, repeatPassword));
        add(form);
    }

    private Component initRegisterButton(String wicketId) {
        return new Button(wicketId) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onSubmit() {
                actionRegisterUser();
            }

        };
    }

    private void actionRegisterUser() {
        List<User> userList = allUsersQuery.result();
        for (User usr : userList) {
            if (Objects.equals(usr.imei, user.imei)) {
                form.error("This imei is already in use!");
            }
        }
        if (!form.hasErrorMessage()) {
            saveUser.execute(user);
            setResponsePage(HomePage.class);
        }
    }
}
