package com.tieto.it2014.ui.friend;

import com.tieto.it2014.domain.DomainException;
import com.tieto.it2014.domain.user.command.AddFriendCommand;
import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.domain.user.query.GetUserByEmailQuery;
import com.tieto.it2014.ui.session.UserSession;
import org.apache.wicket.feedback.ContainerFeedbackMessageFilter;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class AddFriendFormPanel extends Panel {
    
    private static final long serialVersionUID = 1L;
    
    private Form addFriendForm;
    private User loggedUser;
    private String friendEmail;
    private TextField addFriendField;
    private Button addFriendButton;
    private User addedFriend;
    
    @SpringBean
    private GetUserByEmailQuery getUserByEmailQuery;
    
    @SpringBean
    private AddFriendCommand addFriend;

    public AddFriendFormPanel(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {

        super.onInitialize();
    
        addFriendForm = new Form("addFriendForm");
        addFriendField = new TextField("inputFriendEmail",
                new PropertyModel(this, "friendEmail"));
        
        addFriendButton = initaddFriendButton("addFriendButton");
        addFriendForm.add(addFriendField);
        addFriendForm.add(addFriendButton);
        addFriendForm.add(new FeedbackPanel("addFriendFeedback",
                new ContainerFeedbackMessageFilter(addFriendForm)));
        add(addFriendForm);
    }
    
    private Button initaddFriendButton(String wicketId) {
        return new Button(wicketId) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onSubmit() {
                addFriendAction();
            }

        };
    }
    
    private void addFriendAction() {
        try {
              addedFriend = getUserByEmailQuery.result(friendEmail);
              loggedUser = UserSession.get().getUser();
              if ((addedFriend != null) && (loggedUser !=null)) {
                  addFriend.execute(loggedUser.id, addedFriend.id);
              } else {
                  addFriendForm.error("Friend not found");
              }
        } catch (DomainException ex) {
            addFriendForm.error("Error");
        }
    }
    
}

