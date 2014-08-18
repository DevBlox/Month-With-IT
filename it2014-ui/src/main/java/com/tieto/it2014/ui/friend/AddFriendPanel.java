package com.tieto.it2014.ui.friend;

import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.ui.user.AddFriendModalPage;
import org.apache.wicket.Page;
import org.apache.wicket.PageReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.panel.Panel;

public class AddFriendPanel extends Panel {

    private static final long serialVersionUID = 1L;
    private ModalWindow modalAddFriend;

    public AddFriendPanel(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        modalAddFriend = new ModalWindow("modal");
        modalAddFriend.setContent(new AddFriendFormPanel(modalAddFriend.getContentId(), modalAddFriend));
        modalAddFriend.setCookieName("modal-2");
        modalAddFriend.showUnloadConfirmation(false);

        add(new AjaxLink<Void>("showModalLink") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                modalAddFriend.show(target);
            }
        });
        add(modalAddFriend);
    }

}
