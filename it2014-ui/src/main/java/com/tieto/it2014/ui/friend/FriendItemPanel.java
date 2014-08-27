package com.tieto.it2014.ui.friend;

import com.tieto.it2014.domain.user.entity.User;
import org.apache.wicket.Page;
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
    private final Class<? extends Page> friendDetailsPage;

    public FriendItemPanel(String id, User friend, Class<? extends Page> friendDetailsPage) {
        super(id);
        this.friend = friend;
        this.friendDetailsPage = friendDetailsPage;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        PageParameters parameters = new PageParameters();
        parameters.add("userImei", friend.imei);

        BookmarkablePageLink link = new BookmarkablePageLink(
                "friendLink", friendDetailsPage, parameters);

        link.add(new Label("friendLabel", friend.username));
        add(initDeleteButton("friendDelete"));

        modalWindow = new ModalWindow("confirmModalWindow");

        modalWindow.setCssClassName("myModalCss");
        modalWindow.showUnloadConfirmation(false);
        modalWindow.setResizable(false);
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
        add(link);
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
