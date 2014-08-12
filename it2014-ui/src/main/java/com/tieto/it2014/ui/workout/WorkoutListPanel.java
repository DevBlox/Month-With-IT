package com.tieto.it2014.ui.workout;

import com.tieto.it2014.domain.user.entity.Workout;
import java.util.List;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;

public class WorkoutListPanel extends BaseWorkoutPanel {

    private static final long serialVersionUID = 1L;

    public WorkoutListPanel(String id, IModel<List<Workout>> workoutModel) {
        super(id, workoutModel);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        ListView<Workout> view = new ListView<Workout>("workoutList", workoutModel) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem<Workout> item) {
                item.add(new BasicWorkoutListItemPanel("workout", item.getModelObject()));
            }
        };
        add(view);
    }

}
