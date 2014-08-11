package com.tieto.it2014.ui.login;

import com.tieto.it2014.domain.Util.Hash;
import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.domain.user.query.AllUsersQuery;
import com.tieto.it2014.domain.user.query.GetUserByUsernameQuery;
import com.tieto.it2014.ui.HomePage;
import com.tieto.it2014.ui.session.UserSession;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.StringValidator;

public class LoginPanel extends Panel {

    private static final long serialVersionUID = 1L;

    public LoginPanel(String wicketid) {
        super(wicketid);
    }

    private String email;
    private String password;
    private Form form;
    private User user;

    @SpringBean
    private AllUsersQuery allUsersQuery;

    @SpringBean
    private GetUserByUsernameQuery getUserByUsernameQuery;

    @Override
    protected void onInitialize() {

        super.onInitialize();
        form = new Form("loginForm");
        form.add(new TextField("inputEmail", new PropertyModel(this, "email"))
                .setRequired(true));
        form.add(new FeedbackPanel("loginFeedback"));
        form.add(new PasswordTextField("inputPassword", new PropertyModel(this, "password"))
                .setRequired(true)
                .add(new StringValidator(5, 30))
        );
        form.add(initLoginButton("loginButton"));
        form.add(initLogoutButton("logoutButton"));
        add(form);
    }

    private Component initLoginButton(String wicketId) {
        return new Button(wicketId) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onSubmit() {
                buttonAction();
            }

        };
    }

    private Component initLogoutButton(String wicketId) {
        return new Link(wicketId) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {
                UserSession.get().invalidate();
                setResponsePage(HomePage.class);
            }

        };
    }

    private void buttonAction() {

        user = getUserByUsernameQuery.result(email);

        password = Hash.sha256(password);

        if (user.password.equals(password)) {
            UserSession.get().setUser(user);
        }
    }

}
