package com.tieto.it2014.ui.user;

import com.tieto.it2014.domain.user.entity.Workout;
import com.tieto.it2014.domain.user.query.GetUserByIdQuery;
import com.tieto.it2014.domain.workout.query.WorkoutsQuery;
import com.tieto.it2014.ui.workout.WorkoutListPanel;
import java.util.List;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class UserWorkoutsPage extends WebPage {

    private static final long serialVersionUID = 1L;
    public static final String USER_ID = "userId";
    private final String userId;
    private WorkoutsModel workoutsModel;

    @SpringBean
    private WorkoutsQuery workoutQuery;

    @SpringBean
    private GetUserByIdQuery userById;

    public static PageParameters parametersWith(String userId) {
        return new PageParameters().add(USER_ID, userId);
    }

    public UserWorkoutsPage(PageParameters params) {
        userId = params.get(USER_ID).toString();
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        workoutsModel = new WorkoutsModel();
        WorkoutListPanel workoutPanel = new WorkoutListPanel("workoutsList", workoutsModel);
        Component showMoreLink = initShowMoreLink(workoutPanel);
        String username = userById.resultOrNull(userId).username;
        showMoreLink.setOutputMarkupId(true);
        workoutPanel.setOutputMarkupId(true);
        add(workoutPanel);
        add(showMoreLink);
        add(new Label("Heading", username + " workouts"));
    }

    private class WorkoutsModel extends LoadableDetachableModel<List<Workout>> {

        private static final long serialVersionUID = 1L;
        private final Integer ALL_WORKOUTS = null;
        private boolean hasMoreWorkouts = false;
        private Integer workoutsToShow = 2;

        @Override
        protected List<Workout> load() {
            if (workoutsToShow == ALL_WORKOUTS) {
                hasMoreWorkouts = false;
                return workoutQuery.result(userId, ALL_WORKOUTS);
            }
            List<Workout> list = workoutQuery.result(userId, workoutsToShow + 1);
            hasMoreWorkouts = (list.size() > workoutsToShow);
            if (hasMoreWorkouts) {
                list = list.subList(0, workoutsToShow);
            }
            return list;
        }

        boolean hasMoreWorkouts() {
            getObject();
            return hasMoreWorkouts;
        }

        void showAllWorkouts() {
            workoutsToShow = ALL_WORKOUTS;
        }

        void showMoreWorkouts() {
            workoutsToShow += workoutsToShow;
        }
    }

    private AjaxLink initShowMoreLink(final WorkoutListPanel workoutPanel) {
        return new AjaxLink("link") {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(AjaxRequestTarget target) {
                //workoutsModel.showAllWorkouts();
                workoutsModel.showMoreWorkouts();
                target.add(workoutPanel);
                target.add(this);
            }

            @Override
            protected void onConfigure() {
                super.onConfigure();
                setVisible(workoutsModel.hasMoreWorkouts());
            }
        };
    }
}
