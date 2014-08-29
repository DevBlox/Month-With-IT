package com.tieto.it2014.ui.top;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.domain.user.entity.UserStats;
import com.tieto.it2014.domain.user.entity.Workout;
import com.tieto.it2014.domain.user.query.AllUsersQuery;
import com.tieto.it2014.domain.util.Util;
import com.tieto.it2014.domain.workout.query.CurrentMonthWorkoutsQuery;
import com.tieto.it2014.ui.session.UserSession;
import org.apache.commons.lang3.text.WordUtils;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.*;

/**
 * Created by mantas on 20/08/14.
 */
public class TopPanel extends Panel {

    private static final long serialVersionUID = 1L;

    public TopPanel(String id) {
        super(id);
    }

    @SpringBean
    private CurrentMonthWorkoutsQuery workoutQuery;

    @SpringBean
    private AllUsersQuery allUsersQuery;

    @Override
    protected void onInitialize() {
        super.onInitialize();

        List<UserStats> usList = new ArrayList<>();
        for (User u : allUsersQuery.result()) {

            WorkoutsModel workoutsModel = new WorkoutsModel(u.imei);

            double totalDist = 0;
            int totalTime = 0;

            for (Workout wo : workoutsModel.getObject()) {
                totalDist += wo.getDistanceDouble();
                totalTime += wo.getDurationInt();
            }

            Double d = (totalDist / ((double) totalTime / 3600));
            d = d.equals(Double.NaN) ? 0 : d;
            usList.add(new UserStats(u.imei, u.username, totalDist, totalTime, d));

        }

        Collections.sort(usList, new Comparator<UserStats>() {
            @Override
            public int compare(UserStats o1, UserStats o2) {
                if (o1.getDistance().equals(o2.getDistance())) {
                    return o1.userName.trim().compareTo(o2.userName.trim());
                }
                return o2.getDistance().compareTo(o1.getDistance());
            }
        });

        usList = Lists.newArrayList(Iterables.limit(usList, 10));

        usList = Lists.newArrayList(Collections2.filter(usList, new Predicate<UserStats>() {
            @Override
            public boolean apply(UserStats t) {
                return t.getDistance() != 0;
            }
        }));

        // 5. Assign id to every item.
        int i = 0;
        for (UserStats st : usList) {
            st.topId = ++i;
        }

        add(new Label("Heading", "Top 10 of "
                + WordUtils.capitalizeFully(
                        Util.theMonth(
                                Calendar.getInstance().get(Calendar.MONTH)))));
        RepeatingView view = new RepeatingView("topItem");
        for (UserStats us : usList) {
            TopListItemPanel item = new TopListItemPanel(view.newChildId(), us);
            if (Objects.equals(UserSession.get().getUser().imei, us.id)) {
                item.add(new AttributeAppender("class", "active"));
            }
            view.add(item);
        }
        add(view);
    }

    private class WorkoutsModel extends LoadableDetachableModel<List<Workout>> {

        private static final long serialVersionUID = 1L;
        String imei;

        public WorkoutsModel(String imei) {
            this.imei = imei;
        }

        @Override
        protected List<Workout> load() {
            return workoutQuery.result(imei);
        }
    }
}
