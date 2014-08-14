package com.tieto.it2014.ui.user;

import com.tieto.it2014.ui.friend.AddFriendFormPanel;
import org.apache.wicket.PageReference;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class AddFriendModalPage extends WebPage {

    final ModalWindow window;
    final PageReference modalWindowPage;
    
    public AddFriendModalPage(final PageReference modalWindowPage,
            final ModalWindow window) {
        this.window = window;
        this.modalWindowPage = modalWindowPage;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        
        add(new AddFriendFormPanel("addFriendFormPanel", window));
    }
    
    
}
