package com.tieto.it2014.ui.friend;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.tieto.it2014.domain.user.command.AddFriendCommand;
import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.domain.user.query.AllFriendsQuery;
import com.tieto.it2014.domain.user.query.GetUserByEmailQuery;
import com.tieto.it2014.ui.session.UserSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class FriendPanel extends Panel {

    private static final long serialVersionUID = 1L;
    private IModel<String> imei;

    private ListView<User> labels;
    private List<User> array;

    @SpringBean
    private AllFriendsQuery allFriendsQuery;

    @SpringBean
    private GetUserByEmailQuery getUserByEmailQuery;

    @SpringBean
    private AddFriendCommand addFriend;
    public FriendPanel(String id, IModel<String> imei) {
        super(id);
        this.imei = imei;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        if (UserSession.get().hasUser()) {
            array = allFriendsQuery.result(UserSession.get().getUser().imei);

        } else {
            array = new ArrayList<>();
        }

        labels = new ListView("friendListItem", array) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem item) {
                User friend = (User) item.getModelObject();
                if(Objects.equals(friend.imei, imei.getObject())) {
                    //item.add(new FriendItemPanel("friendItem", friend).add(new AttributeAppender("class", new Model("visited"), " ")));
                }
                item.add(new FriendItemPanel("friendItem", friend));
            }
        };
        add(labels);

        AddFriendPanel addFriendPanel = new AddFriendPanel("addFriendPanel", null, null);
        add(addFriendPanel);
    }

}
