package com.tieto.it2014.ui;

import com.tieto.it2014.ui.workout.Workout;
import com.tieto.it2014.ui.workout.WorkoutTopListPanel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;




public class HomePage extends WebPage {

    private static final long serialVersionUID = 1L;

    @Override
    protected void onInitialize() {
        super.onInitialize();
        
        IModel<List<Workout>> workoutModel = initWorkoutListModel();
        
        add(new WorkoutTopListPanel("workout", workoutModel));
        
    }
    
    private IModel<List<Workout>> initWorkoutListModel() {
        return new LoadableDetachableModel<List<Workout>>() {
            private static final long serialVersionUID = 1L;
            
            @Override
            protected List<Workout> load() {
                 List<Workout> array = new ArrayList<>();
                 
                 for (int i = 0; i < 100; i++) {
                     array.add(new Workout(Integer.toString(i), 
                                                    new Date(79456), 
                                                    new Date(98751), 
                                                    0.63,
                                                    9756984.0));                     
                 }
                 return array;
            }
        };
    }

    /*
    
    private IModel<List<User>> initUserListModel() {
        return new LoadableDetachableModel<List<User>>() {
            private static final long serialVersionUID = 1L;

            @Override
            protected List<User> load() {
                return allUsersQuery.result();
            }
        };
    }

    private Link initAddMemberButton(String wicketId) {
        return new Link(wicketId) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {
                setResponsePage(NewUserPage.class);
            }
        };
    }

    private Component initUserCountLabel(String wicketId, final IModel<List<User>> listModel) {
        return new Label(wicketId, new Model<Integer>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Integer getObject() {
                return listModel.getObject().size();
            }
        });
    }
    */
}
