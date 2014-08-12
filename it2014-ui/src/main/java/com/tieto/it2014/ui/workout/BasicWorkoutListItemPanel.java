package com.tieto.it2014.ui.workout;

import com.tieto.it2014.domain.user.entity.Workout;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;

class BasicWorkoutListItemPanel extends Panel {

    private static final long serialVersionUID = 1L;

    private final Workout userLoc;

    public BasicWorkoutListItemPanel(String id, Workout userLoc) {
        super(id);
        this.userLoc = userLoc;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new Label("no", new PropertyModel(userLoc, "id")));
        add(new Label("start", new PropertyModel(userLoc, "startTime")));
        add(new Label("finish", new PropertyModel(userLoc, "finishTime")));
        add(new Label("distance", new PropertyModel(userLoc, "distance")));
        add(new Label("duration", new PropertyModel(userLoc, "duration")));
    }

}
