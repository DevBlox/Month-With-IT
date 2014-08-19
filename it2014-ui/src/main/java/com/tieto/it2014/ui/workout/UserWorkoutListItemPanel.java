package com.tieto.it2014.ui.workout;

import com.tieto.it2014.domain.user.entity.Workout;
import com.tieto.it2014.ui.session.UserSession;
import com.tieto.it2014.ui.workout.details.Details;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * Created by mantas on 18/08/14.
 */
public class UserWorkoutListItemPanel extends BasicWorkoutListItemPanel {

    public UserWorkoutListItemPanel(String id, Workout workout) {
        super(id, workout);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new Link("details") {
            @Override
            public void onClick() {
                setResponsePage(Details.class, new PageParameters().add("userImei", UserSession.get().getUser().imei).add("imei", workout.getImei()).add("id", workout.getId()));
            }
        });
    }
}
