package com.tieto.it2014.ui;

import com.tieto.it2014.ui.friend.FriendPanel;
import com.tieto.it2014.ui.session.UserSession;
import com.tieto.it2014.ui.workout.UserWorkoutPanel;
import com.tieto.it2014.ui.workout.WorkoutTopListPanel;
import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.EmptyPanel;

public class HomePage extends BasePage {

    private static final long serialVersionUID = 1L;

    private String id;

    @Override
    protected void onInitialize() {
        id = getRequest().getRequestParameters().getParameterValue("friend_imei").toString("");
        if (StringUtils.isBlank(id) && UserSession.get().isLoggedIn()) {
            id = UserSession.get().getUser().imei;
        }
        super.onInitialize();
    }

    @Override
    protected void onConfigure() {
        id = getRequest().getRequestParameters().getParameterValue("friend_imei").toString("");
        if (StringUtils.isBlank(id) && UserSession.get().isLoggedIn()) {
            id = UserSession.get().getUser().imei;
        }
        super.onConfigure();
        initContent(CONTENT_ID);
        initSidebar(SIDEBAR_ID);

    }

    @Override
    protected Component initContent(String wicketId) {
        return !UserSession.get().isLoggedIn()
                ? super.initContent(wicketId)
                : new UserWorkoutPanel(
                        wicketId,
                        id);
    }

    @Override
    protected Component initFullContent(String wicketId) {
        return UserSession.get().isLoggedIn()
                ? super.initFullContent(wicketId)
                : new WorkoutTopListPanel(wicketId);
    }

    @Override
    protected Component initSidebar(String wicketId) {
        return UserSession.get().getUser() == null
                ? new EmptyPanel(wicketId).setVisible(false)
                : new FriendPanel(wicketId);
    }
}
