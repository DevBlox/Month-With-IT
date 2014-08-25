package com.tieto.it2014.ui.statistics;

import com.tieto.it2014.domain.Util.Util;
import com.tieto.it2014.domain.user.entity.Workout;
import com.tieto.it2014.domain.weight.entity.Weight;
import com.tieto.it2014.domain.weight.query.WeightQuery;
import com.tieto.it2014.domain.workout.query.WorkoutsQuery;
import com.tieto.it2014.ui.session.UserSession;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * Created by mantas on 20/08/14.
 */
public class StatsPanel extends Panel {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private WorkoutsQuery workoutQuery;

    @SpringBean
    private WeightQuery weightQuery;

    public StatsPanel(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        WorkoutsModel workoutsModel = new WorkoutsModel();
        Calendar cal = Calendar.getInstance();
        double totalDist = 0;
        int totalTime = 0;
        int totalDays = 0;
        double weightDiff = 0;
        Set<Integer> yearCount = new LinkedHashSet<>();
        Set<Integer> monthCount = new LinkedHashSet<>();
        Set<Integer> daysCount = new LinkedHashSet<>();

        List<Weight> weights = weightQuery.result(UserSession.get().getUser().imei);

        for (Workout wo : workoutsModel.getObject()) {
            totalDist += wo.getDistanceDouble();
            totalTime += wo.getDurationInt();

            cal.setTimeInMillis(wo.getStartTimeTimestamp());
            yearCount.add(cal.get(Calendar.YEAR));
            monthCount.add(cal.get(Calendar.MONTH));
            daysCount.add(cal.get(Calendar.DAY_OF_MONTH));
        }

        for (int year : yearCount) {
            for (int month : monthCount) {
                for (int day : daysCount) {
                    totalDays++;
                }
            }
        }

        weightDiff = weights.isEmpty() ? 0 : weights.get(weights.size() - 1).weight - weights.get(0).weight;
        weightDiff = (double) (Math.round(weightDiff * 10)) / 10;

        add(new Label("totalDist", "Total distance: " + Util.formatDoubleToString(totalDist) + " km."));
        add(new Label("totalTime", "Total time: " + Util.getDurationString(totalTime)));
        add(new Label("totalDays", "Days: " + totalDays + " d."));
        add(new Label("Calories", "Calories: "));
        if (weightDiff > 0) {
            add(new Label("Weight", "Weight change: +" + weightDiff + " kg."));
        } else {
            add(new Label("Weight", "Weight change: " + weightDiff + " kg."));
        }
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
