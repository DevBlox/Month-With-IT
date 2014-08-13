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

    Button btn;
    final ModalWindow window;
    
    public AddFriendModalPage(final PageReference modalWindowPage,
            final ModalWindow window) {
        this.window = window;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
         btn = new Button("cancelButton");
         btn.add(new AjaxEventBehavior("onclick") {
             protected void onEvent(AjaxRequestTarget target) {
                 window.close(target);
             }
         });
        add(new AddFriendFormPanel("addFriendFormPanel", window));
        add(btn);
    }
    
    
}
