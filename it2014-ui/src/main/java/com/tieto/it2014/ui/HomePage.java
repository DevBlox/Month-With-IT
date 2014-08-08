package com.tieto.it2014.ui;

import com.tieto.it2014.domain.user.entity.UserLoc;
import com.tieto.it2014.domain.user.entity.Workout;
import com.tieto.it2014.domain.workout.query.WorkoutsQuery;
import com.tieto.it2014.ui.workout.WorkoutTopListPanel;
import java.util.List;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;




public class HomePage extends WebPage {

    private static final long serialVersionUID = 1L;
    
    @SpringBean
    private WorkoutsQuery workoutQuery;

    @Override
    protected void onInitialize() {
        super.onInitialize();
        
        IModel<List<Workout>> workoutModel = initWorkoutListModel();

        add(new Label("Heading", "Recent workouts"));
        add(new WorkoutTopListPanel("topList", workoutModel));
        add(new Link("registerPage") {

            @Override
            public void onClick() {
                setResponsePage(RegisterPage.class);
            }
        });
    }
    
    private IModel<List<Workout>> initWorkoutListModel() {
        return new LoadableDetachableModel<List<Workout>>() {
            private static final long serialVersionUID = 1L;
            
            @Override
            protected List<Workout> load() {
                 return workoutQuery.result();
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
