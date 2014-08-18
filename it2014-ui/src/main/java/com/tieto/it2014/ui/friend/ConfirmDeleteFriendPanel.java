package com.tieto.it2014.ui.friend;

import com.tieto.it2014.domain.user.command.DeleteFriendCommand;
import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.ui.HomePage;
import com.tieto.it2014.ui.session.UserSession;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class ConfirmDeleteFriendPanel extends Panel {

    private static final long serialVersionUID = 1L;

    private final User friend;
    private final ModalWindow modalWindow;

    @SpringBean
    private DeleteFriendCommand deleteFriendCommand;

    public ConfirmDeleteFriendPanel(String id, User user, ModalWindow modal) {
        super(id);

        this.friend = user;
        this.modalWindow = modal;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(initConfirmButton("confirmButton"));
        add(initCancelButotn("cancelButton"));
        add(new Label("warningLabel", "Do you really want to delete your friend?"));
    }

    private Link initConfirmButton(String id) {
        return new Link(id) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {
                deleteFriendCommand.execute(UserSession.get().getUser().imei,
                        friend.imei);
                setResponsePage(HomePage.class);
            }

        };
    }

    private Link initCancelButotn(String cancelButton) {
        return new AjaxFallbackLink(cancelButton) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(AjaxRequestTarget target) {
                modalWindow.close(target);
            }

        };
    }

}
