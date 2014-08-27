package com.tieto.it2014.ui.achievments;

import com.tieto.it2014.ui.BasePage;
import com.tieto.it2014.ui.friend.FriendPanel;
import com.tieto.it2014.ui.session.UserSession;
import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class AchievmentsPage extends BasePage {

    private static final long serialVersionUID = 1L;

    private final IModel<String> imei = new Model<>();
    private final String imeiParam;

    public AchievmentsPage(final PageParameters parameters) {
        this.imeiParam = parameters.get("userImei").toString();
    }

    @Override
    protected void onInitialize() {
        configureImei();
        super.onInitialize();
    }

    @Override
    protected Component initSidebar(String wicketId) {
        return UserSession.get().hasUser()
                ? new FriendPanel(wicketId, imei, AchievmentsPage.class)
                : new EmptyPanel(wicketId);
    }

    @Override
    protected Component initContent(String wicketId) {
        return new UserAchievementsPanel(CONTENT_ID, imei);
    }

    private void configureImei() {
        if (StringUtils.isBlank(imeiParam)) {
            imei.setObject(UserSession.get().getUser().imei);
        } else {
            imei.setObject(imeiParam);
        }
    }

}
