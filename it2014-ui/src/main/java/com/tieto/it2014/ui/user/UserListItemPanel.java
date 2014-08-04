package com.tieto.it2014.ui.user;

import com.tieto.it2014.dao.user.command.DeleteCommandDaoMem;
import com.tieto.it2014.domain.user.command.DeleteUserCommand;
import com.tieto.it2014.domain.user.entity.User;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class UserListItemPanel extends Panel {
    private static final long serialVersionUID = 1L;

    @SpringBean
    private DeleteUserCommand deleteUser;

    private final User user;

    public UserListItemPanel(String id, User user) {
        super(id);
        this.user = user;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new Label("name", new PropertyModel(user, "name")));
        add(new Label("yearOfBirth", new PropertyModel(user, "yearOfBirth")));
        add(initDeleteLink("delete"));
        add(initEditLink("edit"));
    }

    private Component initDeleteLink(String wicketId) {
        return new Link(wicketId) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {
                deleteUser.execute(user);
            }
        };
    }

    private Component initEditLink(String wicketId) {
        return new Link(wicketId) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {
                setResponsePage(EditUserPage.class, EditUserPage.parametersWith(user.id));
            }
        };
    }
}
