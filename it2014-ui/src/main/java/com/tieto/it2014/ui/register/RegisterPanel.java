package com.tieto.it2014.ui.register;

import com.tieto.it2014.domain.user.command.CreateUserCommand;
import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.ui.HomePage;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class RegisterPanel extends Panel {

    private static final long serialVersionUID = 1L;

    
    
    public RegisterPanel(String wicketid) {
        super(wicketid);
    }
    
    private User user ;

    @SpringBean
    private CreateUserCommand createUser;

    @Override
    protected void onInitialize() {
        
        super.onInitialize();
        user = new User();
        Form form = new Form("loginForm");
//        form.add(new FeedbackPanel("loginFeedback"));
        form.add(new TextField("inputUserName", new PropertyModel(user, "username"))
                .setRequired(true));
        form.add(new TextField("inputPassword", new PropertyModel(user, "password"))
                .setRequired(true));
        form.add(new TextField("inputEmail", new PropertyModel(user, "email")));
        form.add(new TextField("imei", new PropertyModel(user, "imei")));
        form.add(initRegisterButton("registerButton"));
        add(form);
    }
//
    private Component initRegisterButton(String wicketId) {
        return new Button(wicketId) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onSubmit() {
                createUser.execute(user);
                setResponsePage(HomePage.class);
            }
        };
    }
//
//    private Component initCancelButton(String wicketId) {
//        return new Link(wicketId) {
//            private static final long serialVersionUID = 1L;
//
//            @Override
//            public void onClick() {
//                setResponsePage(HomePage.class);
//            }
//        };
//    }
}
