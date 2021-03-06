/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tieto.it2014.ui.workout;

import com.tieto.it2014.domain.user.entity.Workout;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.PropertyModel;

/**
 *
 * @author pc4
 */
class WorkoutListItemPanel extends BasicWorkoutListItemPanel {

    private final Workout workout;

    public WorkoutListItemPanel(String id, Workout workout) {
        super(id, workout);
        this.workout = workout;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new Label("imei", new PropertyModel(workout, "imei")));
    }
    
}
