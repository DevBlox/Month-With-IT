/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tieto.it2014.ui.workout;

import com.tieto.it2014.domain.user.entity.UserLoc;

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
    
    private final IModel<List<UserLoc>> workoutModel;

    public WorkoutTopListPanel(String id, IModel<List<UserLoc>> workoutModel) {
        super(id);
        this.workoutModel = workoutModel;   
    }
    
     @Override
    protected void onInitialize() {
        super.onInitialize();
        add(initWorkoutList("topList", workoutModel)); 
        
        

    }

    private ListView<UserLoc> initWorkoutList(String wicketId, IModel<List<UserLoc>> workoutModel) {
         return new ListView<UserLoc>(wicketId, workoutModel) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem<UserLoc> item) {
                UserLoc userLoc = item.getModelObject();
                item.add(new WorkoutListItemPanel("oneWorkout", userLoc));
            }
        };
        
       
    }
    
    
}
