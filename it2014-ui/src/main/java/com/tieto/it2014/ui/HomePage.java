package com.tieto.it2014.ui;

import com.tieto.it2014.ui.friend.FriendPanel;
import com.tieto.it2014.ui.header.HeaderPanel;
import com.tieto.it2014.ui.session.UserSession;
import com.tieto.it2014.ui.workout.UserWorkoutPanel;
import com.tieto.it2014.ui.workout.WorkoutTopListPanel;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

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
//        friendPanel.setVisible(UserSession.get().hasUser());
//        workoutTopListPanel.setVisible(!UserSession.get().hasUser());
//        label.setVisible(!UserSession.get().hasUser());
    }

    @Override
    protected Component initContent(String wicketId) {
        return UserSession.get().getUser() == null ? new WorkoutTopListPanel(wicketId) : new UserWorkoutPanel(wicketId, UserSession.get().getUser().imei);
    }
}
