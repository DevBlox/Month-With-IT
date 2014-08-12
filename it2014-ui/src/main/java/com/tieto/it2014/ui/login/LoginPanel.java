package com.tieto.it2014.ui.login;

import com.tieto.it2014.domain.DomainException;
import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.domain.user.query.AllUsersQuery;
import com.tieto.it2014.domain.user.query.LoggedInUserQuery;
import com.tieto.it2014.ui.HomePage;
import com.tieto.it2014.ui.session.UserSession;
import com.tieto.it2014.ui.user.UserWorkoutsPage;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
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

    private String email;
    private String password;
    private Form form;
    private Link logoutButton;
    private Button loginButton;

    private User loggedInUser;

    @SpringBean
    private LoggedInUserQuery loggedInUserQuery;

    @SpringBean
    private AllUsersQuery allUsersQuery;

    public LoginPanel(String wicketid) {
        super(wicketid);
    }

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
        
        loginButton = (Button) initLoginButton("loginButton");
        loginButton.setOutputMarkupId(true);
        form.add(loginButton);
        
        logoutButton = (Link) initLogoutButton("logoutButton");
        logoutButton.setOutputMarkupId(true);
        form.add(logoutButton);
        add(form);
    }

    @Override
    protected void onConfigure() {
        super.onConfigure(); 
        logoutButton.setVisible(UserSession.get().hasUser());
        loginButton.setVisible(!UserSession.get().hasUser());
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
        try {
            loggedInUser = loggedInUserQuery.result(email, password);
            UserSession.get().setUser(loggedInUser);
            logoutButton.setVisible(true);
            setResponsePage(UserWorkoutsPage.class,
                    UserWorkoutsPage.parametersWith(loggedInUser.imei));
        } catch (DomainException ex) {
            form.error(ex.getMessage());
        }
    }

}
