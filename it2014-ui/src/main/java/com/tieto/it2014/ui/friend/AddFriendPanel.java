package com.tieto.it2014.ui.friend;

import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.ui.user.AddFriendModalPage;
import org.apache.wicket.Page;
import org.apache.wicket.PageReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;

public class AddFriendPanel extends Panel {

    private static final long serialVersionUID = 1L;

    private final User friend;
    private PageReference pageReference;
    private ModalWindow modal;

    public AddFriendPanel(String id, User friend, PageReference pageReference) {
        super(id);
        this.friend = friend;
        this.pageReference = pageReference;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        
        // Create the modal window.
        add(modal = new ModalWindow("modal"));
        modal.setCookieName("modal-1");

        modal.setPageCreator(new ModalWindow.PageCreator() {
            public Page createPage() {
                // Use this constructor to pass a reference of this page.
                return new AddFriendModalPage(pageReference,
                        modal);
            }
        });
        modal.setWindowClosedCallback(new ModalWindow.WindowClosedCallback() {
            @Override
            public void onClose(AjaxRequestTarget target) {
                // The variable passValue might be changed by the modal window.
                // We need this to update the view of this page.
            }
        });
        modal.setCloseButtonCallback(new ModalWindow.CloseButtonCallback() {
            public boolean onCloseButtonClicked(AjaxRequestTarget target) {
                // Change the passValue variable when modal window is closed.
                    modal.close(target);
                return true;
            }
        });

        // Add the link that opens the modal window.
        add(new AjaxLink<Void>("showModalLink") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                modal.show(target);
            }
        });
    }

}
