package com.tieto.it2014.ui.register;

import com.tieto.it2014.domain.Util.Hash;
import com.tieto.it2014.domain.user.command.SaveUserCommand;
import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.domain.user.query.GetUserByEmailQuery;
import com.tieto.it2014.domain.user.query.GetUserByIdQuery;
import com.tieto.it2014.ui.HomePage;
import com.tieto.it2014.ui.session.UserSession;
import com.tieto.it2014.ui.validation.ExistingEmailValidator;
import com.tieto.it2014.ui.validation.ExistingImeiValidator;
import org.apache.wicket.Component;
import org.apache.wicket.feedback.ContainerFeedbackMessageFilter;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.EqualPasswordInputValidator;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.apache.wicket.validation.validator.StringValidator;

import static com.tieto.it2014.ui.utils.UIUtils.withInfoMsg;

public class RegisterPanel extends Panel {

    private static final long serialVersionUID = 1L;

    public RegisterPanel(String wicketid) {
        super(wicketid);
    }

    private User user;
    private Form form;

    @SpringBean
    private SaveUserCommand saveUser;

    @SpringBean
    private GetUserByEmailQuery getUserByEmailQuery;

    @SpringBean
    private GetUserByIdQuery getUserByIdQuery;

    @Override
    protected void onInitialize() {

        super.onInitialize();
        user = new User();
        form = new Form("registerForm");
        PasswordTextField password = new PasswordTextField("inputPassword",
                new PropertyModel(user, "password"));
        PasswordTextField repeatPassword = new PasswordTextField("repeatPassword",
                new PropertyModel(user, "password"));

        form.add(new TextField("inputUserName",
                new PropertyModel(user, "username"))
                .setRequired(true)
                .add(new StringValidator(1, 30))
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
                .add(new ExistingEmailValidator(getUserByEmailQuery))
        );
        form.add(new TextField("imei", new PropertyModel(user, "imei"))
                .setRequired(true)
                .add(new ExistingImeiValidator(getUserByIdQuery))
                .add(new StringValidator(0, 15))
        );
        form.add(initRegisterButton("registerButton"));
        form.add(initCancelButton("cancelButton"));
        form.add(new EqualPasswordInputValidator(password, repeatPassword));

        form.add(new FeedbackPanel("registerFeedback",
                new ContainerFeedbackMessageFilter(form)));

        add(form);
    }

    private Link initCancelButton(String id) {
        return new Link(id) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {
                setResponsePage(HomePage.class);
            }

        };
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
        user.password = Hash.sha256(user.password);
        saveUser.execute(user);
        UserSession.get().setUser(user);

        // Kad kitakart register langeliuose nebeliktu registravimosi duomenu!!
        user = null;
        user = new User();

        setResponsePage(withInfoMsg(new HomePage(), "Registration was successfull"));

    }

    @Override
    protected void onConfigure() {
        super.onConfigure();
        if (UserSession.get().isLoggedIn()) {
            setResponsePage(HomePage.class);
        }
    }
}
