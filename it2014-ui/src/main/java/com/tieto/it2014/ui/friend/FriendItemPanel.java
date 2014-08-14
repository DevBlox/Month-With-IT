package com.tieto.it2014.ui.friend;

import com.tieto.it2014.domain.user.command.DeleteFriendCommand;
import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.ui.HomePage;
import com.tieto.it2014.ui.session.UserSession;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class FriendItemPanel extends Panel {

    private static final long serialVersionUID = 1L;

    private final User friend;

    @SpringBean
    private DeleteFriendCommand deleteFriendCommand;

    public FriendItemPanel(String id, User friend) {
        super(id);
        this.friend = friend;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        PageParameters p = new PageParameters();
        p.add("friend_imei", friend.imei);

        BookmarkablePageLink l = new BookmarkablePageLink("friendLink", HomePage.class, p);

        l.add(new Label("friendLabel", friend.username));
        l.add(initDeleteButton("friendDelete"));

        add(l);
    }

    private Link initDeleteButton(String wicketId) {
        return new Link(wicketId) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {
                deleteFriendCommand.execute(UserSession.get().getUser().imei,
                        friend.imei);

                setResponsePage(HomePage.class);
            }

        };
    }

}
