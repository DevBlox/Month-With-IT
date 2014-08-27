package com.tieto.it2014.ui.achievments;

import com.tieto.it2014.domain.achievment.entity.Achievement;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.Panel;

public class UserAchievementsListItem extends Panel {

    private static final long serialVersionUID = 1L;

    private final Achievement achievement;

    public UserAchievementsListItem(String id, Achievement achievement) {
        super(id);
        this.achievement = achievement;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new Label("achievementLabel", achievement.getName()));
        add(new EmptyPanel("achievementImage"));
        add(new Label("achievementDescription", achievement.getDescription()));
    }

}
