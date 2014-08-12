package com.tieto.it2014.ui;

import com.tieto.it2014.domain.user.entity.Workout;
import com.tieto.it2014.domain.workout.query.WorkoutsQuery;
import com.tieto.it2014.ui.friend.FriendPanel;
import com.tieto.it2014.ui.header.HeaderPanel;
import com.tieto.it2014.ui.session.UserSession;
import com.tieto.it2014.ui.workout.WorkoutTopListPanel;
import java.util.List;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class HomePage extends WebPage {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private WorkoutsQuery workoutQuery;

    private FriendPanel friendPanel;
    private WorkoutTopListPanel workoutTopListPanel;
    private HeaderPanel headerPanel;
    private Label label;

    @Override
    protected void onInitialize() {
        super.onInitialize();

        IModel<List<Workout>> workoutModel = initWorkoutListModel();

        friendPanel = new FriendPanel("friendPanel");
        workoutTopListPanel = new WorkoutTopListPanel("topList", workoutModel);
        label = new Label("Heading", "Recent workouts");
        add(friendPanel);
        add(label);
        add(workoutTopListPanel);
        add(new HeaderPanel("headerPanel"));
    }

    @Override
    protected void onConfigure() {
        friendPanel.setVisible(UserSession.get().hasUser());
        workoutTopListPanel.setVisible(!UserSession.get().hasUser());
        label.setVisible(!UserSession.get().hasUser());
    }

    private IModel<List<Workout>> initWorkoutListModel() {
        return new LoadableDetachableModel<List<Workout>>() {
            private static final long serialVersionUID = 1L;

            @Override
            protected List<Workout> load() {
                return workoutQuery.result(null, 100);
            }
        };
    }
}
