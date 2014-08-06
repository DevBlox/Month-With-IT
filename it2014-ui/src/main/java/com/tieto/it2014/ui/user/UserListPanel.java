package com.tieto.it2014.ui.user;

import com.tieto.it2014.domain.user.entity.User;
import java.util.List;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public class UserListPanel extends Panel {

    private static final long serialVersionUID = 1L;

    private final IModel<List<User>> usersModel;

    public UserListPanel(String id, IModel<List<User>> usersModel) {
        super(id);
        this.usersModel = usersModel;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(initUserList("users", usersModel));
    }

    private ListView<User> initUserList(String wicketId, IModel<List<User>> usersModel) {
        return new ListView<User>(wicketId, usersModel) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem<User> item) {
                User user = item.getModelObject();
                item.add(new UserListItemPanel("user", user));
            }
        };
    }

}
