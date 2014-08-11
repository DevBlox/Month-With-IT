package com.tieto.it2014.ui.user;

import com.tieto.it2014.domain.user.entity.Workout;
import com.tieto.it2014.domain.user.query.GetUserByIdQuery;
import com.tieto.it2014.domain.workout.query.WorkoutsQuery;
import com.tieto.it2014.ui.workout.WorkoutListPanel;
import java.util.List;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class UserWorkoutsPage extends WebPage {

    private static final long serialVersionUID = 1L;
    public static final String USER_ID = "userId";

    @SpringBean
    private WorkoutsQuery workoutQuery;

    private final String userId;

    @SpringBean
    private GetUserByIdQuery getUserByIdQuery;

    public static PageParameters parametersWith(Long userId) {
        return new PageParameters().add(USER_ID, userId);
    }

    public UserWorkoutsPage(PageParameters params) {
        userId = params.get(USER_ID).toString();
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        IModel<List<Workout>> userWorkoutsModel = initUserWorkoutsListModel();

        add(new Label("Heading", "User workouts"));
        add(new WorkoutListPanel("workoutsList", userWorkoutsModel));
    }

    private IModel<List<Workout>> initUserWorkoutsListModel() {
        return new LoadableDetachableModel<List<Workout>>() {
            private static final long serialVersionUID = 1L;

            @Override
            protected List<Workout> load() {
                return workoutQuery.result(userId, null);
            }
        };
    }
}
