package com.tieto.it2014.ui.user;

import com.tieto.it2014.domain.user.command.CreateUserCommand;
import org.apache.wicket.PageReference;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class AddFriendModalPage extends WebPage {

    @SpringBean
    private CreateUserCommand createUser;

    public AddFriendModalPage(final PageReference modalWindowPage,
            final ModalWindow window) {

    }
}
