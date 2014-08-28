package com.tieto.it2014.ui.achievments;

import com.tieto.it2014.domain.achievment.entity.UserAchievement;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

public class UserAchievementsListItem extends Panel {

    private static final long serialVersionUID = 1L;

    private final UserAchievement achievement;
    private Label image;

    public UserAchievementsListItem(String id, UserAchievement achievement) {
        super(id);
        this.achievement = achievement;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new Label("achievementLabel", achievement.getName()));
        image = new Label("achievementImage");
        image.add(new AttributeAppender("class", new Model<>("achievement_"
                + achievement.getAchievmentId()), " "));
        add(image);
        add(new Label("achievementDescription", achievement.getDescription()));
    }

}
