package com.tieto.it2014.ui.header;

import com.tieto.it2014.ui.RegisterPage;
import com.tieto.it2014.ui.login.LoginPanel;
import com.tieto.it2014.ui.session.UserSession;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

public class HeaderPanel extends Panel {

    private static final long serialVersionUID = 1L;

    public HeaderPanel(String id) {
        super(id);
    }

    private Link registerButton;

    @Override
    protected void onInitialize() {
        super.onInitialize();
        registerButton = initRegisterButton("registerPage");
        registerButton.setOutputMarkupId(true);
        add(registerButton);
        // TODO if logged show UserInfoPanel
        // else show LoginPanel
        add(new LoginPanel("loginPanel"));
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();
        registerButton.setVisible(!UserSession.get().hasUser());
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
