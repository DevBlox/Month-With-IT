package com.tieto.it2014.ui.friend;

import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.ui.HomePage;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class FriendItemPanel extends Panel {

    private static final long serialVersionUID = 1L;

    private final User friend;
    private ModalWindow modalWindow;

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

        modalWindow = new ModalWindow("confirmModalWindow");

        modalWindow.showUnloadConfirmation(false);
        modalWindow.setResizable(true);
        modalWindow.setAutoSize(false);

        modalWindow.setWidthUnit("px");
        modalWindow.setHeightUnit("px");
        modalWindow.setInitialHeight(200);
        modalWindow.setInitialWidth(500);
        modalWindow.setMinimalHeight(200);
        modalWindow.setMinimalWidth(500);
        modalWindow.setContent(new ConfirmDeleteFriendPanel(modalWindow.getContentId(),
                friend, modalWindow));

        add(modalWindow);
        add(l);
    }

    private Link initDeleteButton(String wicketId) {
        return new AjaxFallbackLink(wicketId) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(AjaxRequestTarget target) {
                modalWindow.show(target);
            }

        };
    }

}
