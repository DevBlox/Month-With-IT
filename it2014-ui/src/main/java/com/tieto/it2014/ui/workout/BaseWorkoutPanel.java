package com.tieto.it2014.ui.workout;

import com.tieto.it2014.domain.user.entity.Workout;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import java.util.List;

public class BaseWorkoutPanel extends Panel {

    private static final long serialVersionUID = 1L;

    protected final IModel<List<Workout>> workoutModel;

    public BaseWorkoutPanel(String id, IModel<List<Workout>> workoutModel) {
        super(id);
        this.workoutModel = workoutModel;
    }

}
