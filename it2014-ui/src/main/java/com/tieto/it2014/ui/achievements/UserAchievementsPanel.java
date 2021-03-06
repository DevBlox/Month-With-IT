package com.tieto.it2014.ui.achievements;

import com.tieto.it2014.domain.achievment.command.AddAchievementCommand;
import com.tieto.it2014.domain.achievment.entity.UserAchievement;
import com.tieto.it2014.domain.achievment.entity.UserAchievementNoDate;
import com.tieto.it2014.domain.achievment.query.UserAchievementsQuery;
import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.domain.user.query.GetUserByIdQuery;
import com.tieto.it2014.domain.util.Util;
import com.tieto.it2014.ui.error.ErrorPage404;
import com.tieto.it2014.ui.session.UserSession;
import java.util.List;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.lang.Objects;

public class UserAchievementsPanel extends Panel {

    private static final long serialVersionUID = 1L;

    private String usableImei;
    private String name;
    private List<UserAchievement> listOfAchievments;
    private User friend;

    @SpringBean
    private GetUserByIdQuery getUserByIdQuery;

    @SpringBean
    private UserAchievementsQuery userAchievementsQuery;

    @SpringBean
    private AddAchievementCommand addAchievementQuery;

    @SpringBean
    private AchievementsChecker achievementChecker;

    public UserAchievementsPanel(String id, IModel<String> imei) {
        super(id);
        this.usableImei = imei.getObject();
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        if (Objects.equal(usableImei, UserSession.get().getUser().getImei())) {
            name = "My";
            usableImei = UserSession.get().getUser().getImei();
        } else {
            friend = getUserByIdQuery.resultOrNull(usableImei);

            if (Objects.equal(friend, null)) {
                setResponsePage(ErrorPage404.class);
            }

            name = friend.getUsername();
        }

        add(new Label("headerLabel", name + " achievements"));

        listOfAchievments = userAchievementsQuery.result(usableImei);

        checkAchievements();

        add(initAchievementList());

    }

    public void checkAchievements() {
        for (UserAchievement achievement : listOfAchievments) {
            if (achievement.getDate() == null) {
                if (achievementChecker.checksAchievementById(achievement.getAchievmentId(), usableImei)) {
                    if (Objects.equal(usableImei, UserSession.get().getUser().getImei())) {
                        addAchievementQuery.execute(new UserAchievementNoDate(0, achievement.getAchievmentId(), Util.getCurrentTimestamp(), usableImei, true, false));
                        achievement.setDate(Util.getCurrentTimestamp());
                        achievement.setIsNew(true);
                        achievement.setIsSeen(false);
                    } else {
                        achievement.setDate(Util.getCurrentTimestamp());
                        achievement.setIsNew(false);
                        achievement.setIsSeen(true);
                    }
                } else {
                    if (Objects.equal(usableImei, UserSession.get().getUser().getImei())) {
                        achievement.setIsNew(false);
                        achievement.setIsSeen(true);
                    } else {
                        achievement.setIsNew(true);
                        achievement.setIsSeen(false);
                    }

                }
            } else {
                if (Objects.equal(usableImei, UserSession.get().getUser().getImei())) {
                    achievement.setIsNew(false);
                    achievement.setIsSeen(true);
                } else {
                    achievement.setIsNew(true);
                    achievement.setIsSeen(false);
                }
            }
        }
    }

    private AttributeAppender setAttributeAppender(String setClass) {
        return new AttributeAppender("class", new Model<>(setClass), " ");
    }

    private ListView<UserAchievement> initAchievementList() {
        return new ListView<UserAchievement>("achievmentList", listOfAchievments) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem<UserAchievement> item) {
                UserAchievement achievment = item.getModelObject();
                UserAchievementsListItem listItem = new UserAchievementsListItem("achievmentItem", achievment);
                if (achievment.getDate() != null) {
                    if (Objects.equal(usableImei, UserSession.get().getUser().getImei())) {
                        listItem.add(setAttributeAppender("achieved"));
                    } else {
                        listItem.add(setAttributeAppender("achievedFriend"));
                    }
                }
                if (Objects.equal(usableImei, UserSession.get().getUser().getImei())) {
                    if (achievment.getIsNew()) {
                        achievment.setIsNew(!achievment.getIsNew());
                        listItem.add(setAttributeAppender("achievedNew"));
                    }
                }
                item.add(listItem);
            }

        };
    }

}
