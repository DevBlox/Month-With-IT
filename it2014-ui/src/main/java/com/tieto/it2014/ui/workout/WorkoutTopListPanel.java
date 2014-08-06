/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tieto.it2014.ui.workout;

import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.ui.HomePage.Workout;
import com.tieto.it2014.ui.user.UserListItemPanel;
import java.util.List;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.validator.RangeValidator;

/**
 *
 * @author pc4
 */
public class WorkoutTopListPanel extends Panel {
    
    private final IModel<List<Workout>> workoutModel; //!!! Users turi but Workout

    public WorkoutTopListPanel(String id, IModel<List<Workout>> workoutModel) {
        super(id);
        this.workoutModel = workoutModel;    ///!!! Users turi but Workout
    }
    
     @Override
    protected void onInitialize() {
        super.onInitialize();
        add(initWorkoutList("workoutTopListPanel", workoutModel)); ///!!! Users turi but Workout
        
        

    }

    private ListView<Workout> initWorkoutList(String wicketId, IModel<List<Workout>> workoutModel) {
         return new ListView<Workout>(wicketId, workoutModel) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem<Workout> item) {
                Workout workout = item.getModelObject();    // pakeisti tipa i Workout
                item.add(new WorkoutListItemPanel("workout", workout));
            }
        };
        
       
    }
    
    
}
