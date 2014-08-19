package com.tieto.it2014.ui.workout;

import com.tieto.it2014.domain.user.entity.Workout;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;

class BasicWorkoutListItemPanel extends Panel {

    private static final long serialVersionUID = 1L;

    protected final Workout workout;

    public BasicWorkoutListItemPanel(String id, Workout workout) {
        super(id);
        this.workout = workout;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new Label("no", new PropertyModel(workout, "id")));
        add(new Label("start", new PropertyModel(workout, "startTime")));
        add(new Label("finish", new PropertyModel(workout, "finishTime")));
        add(new Label("distance", new PropertyModel(workout, "distance")));
        add(new Label("duration", new PropertyModel(workout, "duration")));
    }

}
