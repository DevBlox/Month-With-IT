/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tieto.it2014.ui.workout;

import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.domain.user.entity.UserLoc;
import com.tieto.it2014.domain.user.entity.Workout;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;

/**
 *
 * @author pc4
 */
public class WorkoutTopListPanel extends Panel {
    
    private final IModel<List<Workout>> workoutModel;

    public WorkoutTopListPanel(String id, IModel<List<Workout>> workoutModel) {
        super(id);
        this.workoutModel = workoutModel;   
    }
    
    @Override
    protected void onInitialize() {
         super.onInitialize();
         RepeatingView view = new RepeatingView("oneWorkout");
         for (Workout wk : workoutModel.getObject()) {
            view.add(new WorkoutListItemPanel(view.newChildId(), wk));
         }
         add(view);
    }
    
}
