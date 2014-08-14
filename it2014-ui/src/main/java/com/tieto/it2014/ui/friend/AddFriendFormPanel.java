package com.tieto.it2014.ui.friend;

import com.tieto.it2014.domain.DomainException;
import com.tieto.it2014.domain.user.command.AddFriendCommand;
import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.domain.user.query.AllFriendsQuery;
import com.tieto.it2014.domain.user.query.GetUserByEmailQuery;
import com.tieto.it2014.ui.HomePage;
import com.tieto.it2014.ui.session.UserSession;
import static java.awt.SystemColor.window;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.Component;
import org.apache.wicket.PageReference;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.feedback.ContainerFeedbackMessageFilter;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
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
    private Component addFriendButton;
    private User addedFriend;
    private List<User> array;
    final ModalWindow window;
    private AjaxRequestTarget target;
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
        AjaxSubmitLink button = new AjaxSubmitLink(wicketId) {

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                String p = ((TextField)form.get("inputFriendEmail")).getValue();
                if (addFriendAction(p)) {
                    //window.close(target);
                    window.close(target);
                    String javascript = "window.top.location = '/';";
                    target.appendJavaScript(javascript);
                 } else {
                    target.add(addFriendForm);
                }
                
                //target.addChildren(getPage(), FeedbackPanel.class);
                //setResponsePage(HomePage.class);
            }
            
            
            
        };
        
        return button;
//        return new Button(wicketId) {
//            private static final long serialVersionUID = 1L;
//
//            @Override
//            public void onSubmit() {
//                addFriendAction();
//            }
//
//        };
    }

    private boolean addFriendAction(String xxx) {
        try {
            if (friendIsInList(xxx)) {
                addFriendForm.error("Friend is already in List");
            } else {
                addedFriend = getUserByEmailQuery.result(xxx);
                loggedUser = UserSession.get().getUser();

                if ((addedFriend != null) && (loggedUser != null)) {
                    if (addedFriend.email.equals(loggedUser.email)) {
                        addFriendForm.error("Dude, you can not be friend of yourself ");
                    } else {
                    addFriend.execute(loggedUser.id, addedFriend.id);
                    addFriendForm.error("Friend has been added");
                      //  setResponsePage(HomePage.class);
                    //window.close(null);
                    return true;
                    }
                } else {
                    addFriendForm.error("Friend not found");
                }
            }

        } catch (DomainException ex) {
            addFriendForm.error("Error");
        }
        return false;
    }

    private Boolean friendIsInList(String friendEmail) {

        Boolean friendExist = false;

        if (UserSession.get().hasUser()) {
            array = allFriendsQuery.result(UserSession.get().getUser().imei);
        } else {
            array = new ArrayList<>();
        }
        if (array.isEmpty()) {
            //   addFriendForm.error("Friend not found");
        } else {
            for (int i = 0; i < array.size(); i++) {
                if (friendEmail.equals(array.get(i).email)) {
                    friendExist = true;
                    break;
                }
            }
        }
        return friendExist;
    }

}
