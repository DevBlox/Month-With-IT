package com.tieto.it2014.ui.workout.details;

import com.tieto.it2014.domain.user.entity.UserLoc;
import com.tieto.it2014.domain.user.entity.Workout;
import com.tieto.it2014.domain.workout.query.WorkoutsQuery;
import com.tieto.it2014.ui.BasePage;
import com.tieto.it2014.ui.error.ErrorPage404;
import com.tieto.it2014.ui.session.UserSession;
import com.tieto.it2014.ui.workout.WorkoutListPanel;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.security.AccessControlException;
import java.util.List;

/**
 * Created by mantas on 18/08/14.
 */
public class Details extends BasePage {

    private static final long serialVersionUID = 1L;
    private String userImei;
    private String workoutToFindImei;
    private int workoutId;

    public Details(final PageParameters params) {
        userImei = params.get("userImei").toString();
        workoutToFindImei = params.get("imei").toString();
        workoutId = Integer.parseInt(params.get("id").toString());
    }

    @Override
    protected Component initFullContent(String wicketId) {
        return new GMapPanel(wicketId, userImei, workoutToFindImei, workoutId);
    }
}
