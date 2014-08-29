package com.tieto.it2014.ui;

import com.tieto.it2014.ui.friend.FriendPanel;
import com.tieto.it2014.ui.session.UserSession;
import com.tieto.it2014.ui.workout.UserWorkoutPanel;
import com.tieto.it2014.ui.workout.WorkoutTopListPanel;
import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class HomePage extends BasePage {

    private static final long serialVersionUID = 1L;

    private IModel<String> imei = new Model<>();
    private String imeiParam;

    public HomePage() {

    }

    public HomePage(final PageParameters params) {
        imeiParam = params.get("userImei").toString();
    }

    @Override
    protected void onInitialize() {
        updateImeiModel();
        super.onInitialize();
    }

    @Override
    protected void onConfigure() {
        updateImeiModel();
        super.onConfigure();
        initContent(CONTENT_ID);
        initSidebar(SIDEBAR_ID);
    }

    private void updateImeiModel() {
        if (StringUtils.isBlank(imeiParam) && UserSession.get().isLoggedIn()) {
            imeiParam = UserSession.get().getUser().getImei();
        }
        imei.setObject(imeiParam);
    }

    @Override
    protected Component initContent(String wicketId) {
        if (UserSession.get().isLoggedIn()) {
            return new UserWorkoutPanel(wicketId, imei);
        }
        return super.initContent(wicketId);
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
                : new FriendPanel(wicketId, imei);
    }
}
