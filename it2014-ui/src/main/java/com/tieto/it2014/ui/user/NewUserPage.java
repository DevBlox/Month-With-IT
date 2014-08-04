package com.tieto.it2014.ui.user;

import com.tieto.it2014.domain.user.command.CreateUserCommand;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class NewUserPage extends WebPage {
    private static final long serialVersionUID = 1L;

    @SpringBean
    private CreateUserCommand createUser;

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new EditUserPanel("editUser", createUser.execute()));
    }
}
