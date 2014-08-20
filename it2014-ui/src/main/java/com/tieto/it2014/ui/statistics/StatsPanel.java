package com.tieto.it2014.ui.statistics;

import com.tieto.it2014.domain.Util.Util;
import com.tieto.it2014.domain.user.entity.Workout;
import com.tieto.it2014.domain.workout.query.WorkoutsQuery;
import com.tieto.it2014.ui.error.ErrorPage404;
import com.tieto.it2014.ui.session.UserSession;
import com.tieto.it2014.ui.workout.WorkoutListPanel;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.security.AccessControlException;
import java.util.List;

/**
 * Created by mantas on 20/08/14.
 */
public class StatsPanel extends Panel {

    @SpringBean
    private WorkoutsQuery workoutQuery;

    public StatsPanel(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        WorkoutsModel workoutsModel = new WorkoutsModel();
        double totalDist = 0;
        int totalTime = 0;
        for (Workout wo : workoutsModel.getObject()) {
            totalDist += wo.getDistanceDouble();
            totalTime += wo.getDurationInt();
        }
        add(new Label("totalDist", "Total distance: " + Util.format(totalDist) + " km."));
        add(new Label("totalTime", "Total time: " + Util.getDurationString(totalTime)));
    }

    private class WorkoutsModel extends LoadableDetachableModel<List<Workout>> {

        private static final long serialVersionUID = 1L;
        private final Integer ALL_WORKOUTS = null;
        String userImei = UserSession.get().getUser().imei;

        @Override
        protected List<Workout> load() {
            return workoutQuery.result(userImei, userImei, ALL_WORKOUTS);
        }
    }
}
