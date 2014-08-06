package com.tieto.it2014.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;



public class HomePage extends WebPage {

    private static final long serialVersionUID = 1L;
    
    public class Workout {
            
            // EMEI
            // Start
            // Finish
            // Duration
            // Distance
            
            String imei = null;
            Date start = null;
            Date finish = null;
            double duration = 0;
            double distance = 0;
            
            Workout(String imei,
                    Date start,
                    Date finish,
                    double duration,
                    double distance) {
                this.imei = imei;
                this.start = start;
                this.finish = finish;
                this.duration = duration;
                this.distance = distance;
            }
        }


    @Override
    protected void onInitialize() {
        super.onInitialize();
        
        IModel<List<Workout>> workoutModel = initWorkoutListModel();
        
    }
    
    private IModel<List<Workout>> initWorkoutListModel() {
        return new LoadableDetachableModel<List<Workout>>() {
            private static final long serialVersionUID = 1L;
            
            @Override
            protected List<Workout> load() {
                return new ArrayList<>(
                        Arrays.asList(new Workout("4128", 
                                                    new Date(79456), 
                                                    new Date(98751), 
                                                    0.63,
                                                    9756984),
                                      new Workout("97663",
                                                    new Date(98796),
                                                    new Date(3210688),
                                                    97564.0,
                                                    32196847)
                                       )
                );
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
