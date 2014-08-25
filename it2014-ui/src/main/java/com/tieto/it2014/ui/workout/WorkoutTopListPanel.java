package com.tieto.it2014.ui.workout;

import com.tieto.it2014.domain.user.entity.Workout;
import com.tieto.it2014.domain.workout.query.WorkoutsQuery;
import com.tieto.it2014.ui.session.UserSession;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.List;

public class WorkoutTopListPanel extends Panel {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private WorkoutsQuery workoutQuery;
    IModel<List<Workout>> workoutModel = initWorkoutListModel();

    public WorkoutTopListPanel(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new Label("Heading", "Recent Workouts"));
        RepeatingView view = new RepeatingView("oneWorkout");
        for (Workout wk : workoutModel.getObject()) {
            view.add(new WorkoutListItemPanel(view.newChildId(), wk));
        }
        add(view);
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();
        this.setVisible(!UserSession.get().hasUser());
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
