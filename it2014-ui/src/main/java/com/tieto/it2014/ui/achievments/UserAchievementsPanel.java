package com.tieto.it2014.ui.achievments;

import com.tieto.it2014.domain.achievment.entity.UserAchievement;
import com.tieto.it2014.domain.achievment.query.UserAchievementsQuery;
import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.domain.user.query.GetUserByIdQuery;
import com.tieto.it2014.ui.session.UserSession;
import java.util.List;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.lang.Objects;

public class UserAchievementsPanel extends Panel {

    private static final long serialVersionUID = 1L;

    private final String friendImei;
    private String name;
    private List<UserAchievement> listOfAchievments;
    private User friend;

    @SpringBean
    private GetUserByIdQuery getUserByIdQuery;

    @SpringBean
    private UserAchievementsQuery userAchievmentsQuery;

    public UserAchievementsPanel(String id, IModel<String> imei) {
        super(id);
        this.friendImei = imei.getObject();
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        if (Objects.equal(friendImei, UserSession.get().getUser().imei)) {
            name = "My";
        } else {
            friend = getUserByIdQuery.resultOrNull(friendImei);
            name = friend.username;
        }

        add(new Label("headerLabel", name + " achievments"));

        listOfAchievments = userAchievmentsQuery.result(UserSession.get().getUser().imei);

        System.out.println(listOfAchievments.size());

        add(new ListView<UserAchievement>("achievmentList", listOfAchievments) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem<UserAchievement> item) {
                UserAchievement achievment = item.getModelObject();

                item.add(new UserAchievementsListItem("achievmentItem"));
            }

        });
    }

}
