package com.tieto.it2014.ui.workout;

import com.tieto.it2014.domain.user.entity.Workout;
import com.tieto.it2014.domain.user.query.GetFriendByImeiQuery;
import com.tieto.it2014.domain.user.query.GetUserByIdQuery;
import com.tieto.it2014.domain.workout.query.WorkoutsQuery;
import com.tieto.it2014.ui.session.UserSession;
import java.util.List;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class UserWorkoutPanel extends Panel {

    private static final long serialVersionUID = 1L;

    public static final String USER_ID = "userId";
    private final IModel<String> imei;
    private WorkoutsModel workoutsModel;

    @SpringBean
    private WorkoutsQuery workoutQuery;

    @SpringBean
    private GetUserByIdQuery userById;

    public UserWorkoutPanel(String id, IModel<String> imei) {
        super(id);
        this.imei = imei;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        workoutsModel = new WorkoutsModel();

        WorkoutListPanel workoutPanel = new WorkoutListPanel("workoutsList", (IModel<List<Workout>>) workoutsModel);
        Component showMoreLink = initShowMoreLink(workoutPanel);

        String username = userById.resultOrNull(imei.getObject()).username;

        if (UserSession.get().hasUser()) {
            if (UserSession.get().getUser().imei.equals(imei)) {
                username = "My";
            }
        }

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
                return workoutQuery.result(UserSession.get().getUser().imei, imei.getObject(), ALL_WORKOUTS);
            }
            List<Workout> list = workoutQuery.result(UserSession.get().getUser().imei, imei.getObject(), workoutsToShow + 1);
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
            workoutsToShow += 2;
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
