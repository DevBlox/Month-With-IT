package com.tieto.it2014.ui.friend;

import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.domain.user.query.AllFriendsQuery;
import com.tieto.it2014.ui.session.UserSession;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class FriendPanel extends Panel {

    private static final long serialVersionUID = 1L;

    public FriendPanel(String id) {
        super(id);
    }

    private ListView<User> labels;
    private List<User> array;

    @SpringBean
    private AllFriendsQuery allFriendsQuery;

    @Override
    protected void onInitialize() {
        super.onInitialize();

        if (UserSession.get().hasUser()) {
            array = allFriendsQuery.result(UserSession.get().getUser().imei);

        } else {
            array = new ArrayList<>();
        }

        if (array.isEmpty()) {
            array.add(new User(" ", "You have no friends", " ", " "));
        }

        labels = new ListView("friendListItem", array) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem item) {
                User friend = (User) item.getModelObject();
                item.add(new FriendItemPanel("friendItem", friend));
            }
        };
        add(labels);
    }

}
