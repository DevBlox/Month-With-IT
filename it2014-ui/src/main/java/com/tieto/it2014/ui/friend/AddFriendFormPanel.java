package com.tieto.it2014.ui.friend;

import com.tieto.it2014.domain.DomainException;
import com.tieto.it2014.domain.user.command.AddFriendCommand;
import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.domain.user.query.AllFriendsQuery;
import com.tieto.it2014.domain.user.query.GetUserByEmailQuery;
import com.tieto.it2014.ui.HomePage;
import com.tieto.it2014.ui.session.UserSession;
import java.util.List;
import java.util.Objects;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.feedback.ContainerFeedbackMessageFilter;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.jboss.logging.Logger;

public class AddFriendFormPanel extends Panel {

    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger.getLogger(AddFriendFormPanel.class);

    private Form addFriendForm;
    private String friendEmail;
    private TextField addFriendField;
    private Component addFriendButton;
    final ModalWindow window;
    private Button btn;

    @SpringBean
    private GetUserByEmailQuery getUserByEmailQuery;

    @SpringBean
    private AddFriendCommand addFriend;

    @SpringBean
    private AllFriendsQuery allFriendsQuery;

    public AddFriendFormPanel(String id, ModalWindow window) {
        super(id);
        this.window = window;
    }

    @Override
    protected void onInitialize() {

        super.onInitialize();

        btn = new Button("cancelButton");
        btn.add(new AjaxEventBehavior("onclick") {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onEvent(AjaxRequestTarget target) {
                window.close(target);
            }
        });

        addFriendForm = new Form("addFriendForm");
        addFriendField = (TextField) new TextField("inputFriendEmail",
                new PropertyModel(this, "friendEmail"))
                .setRequired(true);

        addFriendButton = initaddFriendButton("addFriendButton");
        addFriendForm.add(addFriendField);
        addFriendForm.add(addFriendButton);
        addFriendForm.add(new FeedbackPanel("addFriendFeedback",
                new ContainerFeedbackMessageFilter(addFriendForm)));
        addFriendForm.setOutputMarkupId(true);
        addFriendForm.add(btn);
        add(addFriendForm);
    }

    private Component initaddFriendButton(String wicketId) {
        return new AjaxSubmitLink(wicketId) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                if (addFriendAction(friendEmail)) {
                    //    addFriendForm.clearInput();
                    window.close(target);
                    setResponsePage(HomePage.class);
                } else {
                    target.add(addFriendForm);
                }
            }
        };
    }

    private boolean addFriendAction(String email) {
        try {
            if (friendIsInList(email)) {
                throw new DomainException("Friend is already in List");
            }
            User addedFriend = getUserByEmailQuery.result(email);
            if (addedFriend == null) {
                throw new DomainException("Friend not found");
            }
            User loggedUser = UserSession.get().getUser();
            if (Objects.equals(addedFriend.email, loggedUser.email)) {
                throw new DomainException("Dude, you can not be friend of yourself ");
            }
            addFriend.execute(loggedUser.imei, addedFriend.imei);
        } catch (DomainException ex) {
            addFriendForm.error(ex.getMessage());
            LOGGER.error(ex.getMessage(), ex);
            return false;
        }
        return true;
    }

    private boolean friendIsInList(String friendEmail) {
        if (!UserSession.get().hasUser()) {
            return false;
        }
        String imei = UserSession.get().getUser().imei;
        List<User> friendList = allFriendsQuery.result(imei);
        for (User friend : friendList) {
            if (Objects.equals(friend.email, friendEmail)) {
                return true;
            }
        }
        return false;
    }

}
