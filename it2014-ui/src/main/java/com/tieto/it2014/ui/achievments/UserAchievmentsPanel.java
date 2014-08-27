package com.tieto.it2014.ui.achievments;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public class UserAchievmentsPanel extends Panel {

    private static final long serialVersionUID = 1L;

    private final String userImei;

    public UserAchievmentsPanel(String id, IModel<String> imei) {
        super(id);
        this.userImei = imei.getObject();
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new Label("label",
                "You are viewing person's achievments whose imei is "
                + userImei));
    }

}
