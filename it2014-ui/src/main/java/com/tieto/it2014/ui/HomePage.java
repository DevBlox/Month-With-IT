package com.tieto.it2014.ui;

import com.tieto.it2014.ui.friend.FriendPanel;
import com.tieto.it2014.ui.session.UserSession;
import com.tieto.it2014.ui.workout.UserWorkoutPanel;
import com.tieto.it2014.ui.workout.WorkoutTopListPanel;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.model.Model;

public class HomePage extends BasePage {

    private static final long serialVersionUID = 1L;

//    @Override
//    protected void onInitialize() {
//        super.onInitialize();
////        friendPanel = new FriendPanel("friendPanel");
////        workoutTopListPanel = ;
////        label = new Label("Heading", "Recent workouts");
////        add(friendPanel);
////        add(label);
////        add(workoutTopListPanel);
//    }
    @Override
    protected void onConfigure() {
        super.onConfigure();
        initContent(CONTENT_ID);
        initSidebar(SIDEBAR_ID);
//        friendPanel.setVisible(UserSession.get().hasUser());
//        workoutTopListPanel.setVisible(!UserSession.get().hasUser());
//        label.setVisible(!UserSession.get().hasUser());
    }

    @Override
    protected Component initContent(String wicketId) {
        return UserSession.get().getUser() == null ?
                new WorkoutTopListPanel(wicketId).add(new AttributeAppender("class", new Model("rightColFUll"))) :
                new UserWorkoutPanel(wicketId, UserSession.get().getUser().imei).add(new AttributeAppender("class", new Model("rightCol")));
    }

    @Override
    protected Component initSidebar(String wicketId) {
        return UserSession.get().getUser() == null ? new EmptyPanel(wicketId).setVisible(false) : new FriendPanel(wicketId).add(new AttributeAppender("class", new Model("leftCol"), " "));
    }
}
