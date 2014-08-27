package com.tieto.it2014.ui.achievments;

import com.tieto.it2014.ui.BasePage;
import com.tieto.it2014.ui.friend.FriendPanel;
import com.tieto.it2014.ui.session.UserSession;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class AchievmentsPage extends BasePage {

    private static final long serialVersionUID = 1L;

    private final IModel<String> imei = new Model<>();

    @Override
    protected Component initSidebar(String wicketId) {
        return UserSession.get().hasUser()
                ? new FriendPanel(wicketId, imei)
                : new EmptyPanel(wicketId);
    }

}
