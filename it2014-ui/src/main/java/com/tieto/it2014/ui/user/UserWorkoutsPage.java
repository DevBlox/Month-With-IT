package com.tieto.it2014.ui.user;

import com.tieto.it2014.ui.header.HeaderPanel;
import com.tieto.it2014.ui.workout.UserWorkoutPanel;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class UserWorkoutsPage extends WebPage {

    private static final long serialVersionUID = 1L;
    public static final String USER_ID = "userId";
    private final String userId;

    public static PageParameters parametersWith(String userId) {
        return new PageParameters().add(USER_ID, userId);
    }

    public UserWorkoutsPage(PageParameters params) {
        userId = params.get(USER_ID).toString();
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new UserWorkoutPanel("workoutPanel", this.userId));
        add(new HeaderPanel("headerPanel"));
    }
}
