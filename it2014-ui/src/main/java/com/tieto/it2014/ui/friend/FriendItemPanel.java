package com.tieto.it2014.ui.friend;

import com.tieto.it2014.domain.user.entity.User;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

public class FriendItemPanel extends Panel {

    private static final long serialVersionUID = 1L;

    private final User friend;

    public FriendItemPanel(String id, User friend) {
        super(id);
        this.friend = friend;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new Label("friendLabel", friend.username));
        add(initDeleteButton("friendDelete"));
    }

    private Link initDeleteButton(String wicketId) {
        return new Link(wicketId) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {
                System.out.println(friend.imei + " " + friend.username);
            }

        };
    }

}
