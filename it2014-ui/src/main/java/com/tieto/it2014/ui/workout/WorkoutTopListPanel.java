/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tieto.it2014.ui.workout;

import java.util.List;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
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
        add(initWorkoutList("topList", workoutModel)); 
        
        

    }

    private ListView<Workout> initWorkoutList(String wicketId, IModel<List<Workout>> workoutModel) {
         return new ListView<Workout>(wicketId, workoutModel) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem<Workout> item) {
                Workout workout = item.getModelObject();   
                item.add(new WorkoutListItemPanel("oneWorkout", workout));
            }
        };
        
       
    }
    
    
}
