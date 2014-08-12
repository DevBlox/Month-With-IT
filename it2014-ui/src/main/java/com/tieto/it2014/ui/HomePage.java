package com.tieto.it2014.ui;

import com.tieto.it2014.domain.user.entity.Workout;
import com.tieto.it2014.domain.workout.query.WorkoutsQuery;
import com.tieto.it2014.ui.login.LoginPanel;
import com.tieto.it2014.ui.session.UserSession;
import com.tieto.it2014.ui.workout.WorkoutTopListPanel;
import java.util.List;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class HomePage extends WebPage {

    private static final long serialVersionUID = 1L;
    
    private Link registerButton;

    @SpringBean
    private WorkoutsQuery workoutQuery;

    @Override
    protected void onInitialize() {
        super.onInitialize();

        IModel<List<Workout>> workoutModel = initWorkoutListModel();

        add(new Label("Heading", "Recent workouts"));
        add(new Label("loginStatus", new Model<String>() {
            private static final long serialVersionUID = 1L;

            @Override
            public String getObject() {
                return UserSession.get().hasUser()
                        ? UserSession.get().getUser().email
                        : "-";
            }

        }));
        add(new WorkoutTopListPanel("topList", workoutModel));
        
        
        
        registerButton = (Link) initRegisterButton("registerPage");
        registerButton.setOutputMarkupId(true);
        add(registerButton);
//        add(new Link("registerPage") {
//            private static final long serialVersionUID = 1L;
//
//            @Override
//            public void onClick() {
//                setResponsePage(RegisterPage.class);
//            }
//        });
        add(new LoginPanel("loginPanel"));
    }

    @Override
    protected void onConfigure() {
        registerButton.setVisible(!UserSession.get().hasUser());
        super.onConfigure();
    }
    
    

    private IModel<List<Workout>> initWorkoutListModel() {
        return new LoadableDetachableModel<List<Workout>>() {
            private static final long serialVersionUID = 1L;

            @Override
            protected List<Workout> load() {
                 return workoutQuery.result(null, 100);
            }
        };
    }
    
     private Component initRegisterButton(String wicketId) {
        return new Link(wicketId) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {
                setResponsePage(RegisterPage.class);
            }

        };
    }
}
