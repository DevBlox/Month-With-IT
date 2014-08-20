package com.tieto.it2014.ui.header;

import com.tieto.it2014.dao.env.EnvironmentDao;
import com.tieto.it2014.ui.RegisterPage;
import com.tieto.it2014.ui.login.LoginPanel;
import com.tieto.it2014.ui.session.UserSession;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

public class HeaderPanel extends Panel {

    private static final long serialVersionUID = 1L;

    private Link registerButton;
    private boolean setShowRegisterButton;
    private NavbarPanel navbarPanel;

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

        navbarPanel = new NavbarPanel("navbar");
        add(navbarPanel);
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        if (UserSession.get().hasUser() || setShowRegisterButton) {
            registerButton.setVisible(!UserSession.get().hasUser()
                    || !setShowRegisterButton);
        }

        navbarPanel.setVisible(UserSession.get().hasUser());

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
}
