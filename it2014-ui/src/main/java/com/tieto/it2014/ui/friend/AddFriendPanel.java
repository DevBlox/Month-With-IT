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
        modalAddFriend.setCssClassName("myModalCss");
        modalAddFriend.setContent(new AddFriendFormPanel(modalAddFriend.getContentId(), modalAddFriend));
        modalAddFriend.setCookieName("modal15");
        modalAddFriend.showUnloadConfirmation(false);
        modalAddFriend.setInitialWidth(420);
        modalAddFriend.setMinimalWidth(400);
        modalAddFriend.setAutoSize(false);
        modalAddFriend.setInitialHeight(180);
        modalAddFriend.setResizable(false);
        modalAddFriend.setWidthUnit("px");
        modalAddFriend.setHeightUnit("px"); 

        add(new AjaxLink<Void>("showModalLink") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                modalAddFriend.show(target);
            }
        });
        add(modalAddFriend);
    }

}
