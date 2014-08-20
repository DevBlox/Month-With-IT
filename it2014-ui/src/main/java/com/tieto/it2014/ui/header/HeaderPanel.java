package com.tieto.it2014.ui.header;

import com.tieto.it2014.dao.env.EnvironmentDao;
import com.tieto.it2014.ui.RegisterPage;
import com.tieto.it2014.ui.login.LoginPanel;
import com.tieto.it2014.ui.session.UserSession;
import com.tieto.it2014.ui.user.WeightPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

public class HeaderPanel extends Panel {

    private static final long serialVersionUID = 1L;

    private Link registerButton;
    private boolean setShowRegisterButton;
    private Form navbarForm;

    public HeaderPanel(String id) {
        super(id);
        setShowRegisterButton = true;
    }

    public void setShowRegisterButton(boolean show) {
        setShowRegisterButton = show;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        registerButton = initRegisterButton("registerPage");
        registerButton.setOutputMarkupId(true);
        registerButton.setVisible(setShowRegisterButton);
        add(registerButton);
        add(new LoginPanel("loginPanel"));
        add(new Label("environmentComment", EnvironmentDao.getComment()));

        navbarForm = initNavbar("navbar");
        add(navbarForm);
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        if (UserSession.get().hasUser() || setShowRegisterButton) {
            registerButton.setVisible(!UserSession.get().hasUser()
                    || !setShowRegisterButton);
        }

        navbarForm.setVisible(UserSession.get().hasUser());

    }

    private Link initRegisterButton(String wicketId) {
        return new Link(wicketId) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {
                setResponsePage(RegisterPage.class);
            }

        };
    }

    private Form initNavbar(String navbar) {
        Form form = new Form(navbar);

        form.add(new Link("weightPageLink") {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {
                setResponsePage(WeightPage.class);
            }

        });

        return form;
    }
}
