package com.tieto.it2014.ui.friend;

import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.domain.user.query.AllFriendsQuery;
import com.tieto.it2014.ui.session.UserSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class FriendPanel extends Panel {

    private static final long serialVersionUID = 1L;
    private final IModel<String> imei;

    private List<User> friendList;

    @SpringBean
    private AllFriendsQuery allFriendsQuery;

    public FriendPanel(String id, IModel<String> imei) {
        super(id);
        this.imei = imei;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        if (UserSession.get().hasUser()) {
            friendList = allFriendsQuery.result(UserSession.get().getUser().imei);

        } else {
            friendList = new ArrayList<>();
        }

        add(new ListView<User>("friendListItem", friendList) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem<User> item) {
                User friend = item.getModelObject();
                boolean found = false;
                if (Objects.equals(friend.imei, imei.getObject())) {
                    found = true;
                }
                item.add(new FriendItemPanel("friendItem", friend));
                if (found) {
                    item.add(new AttributeAppender("class", Model.of("visited"), " "));
                }
            }
        });

        add(new AddFriendPanel("addFriendPanel"));
    }

}
