package com.tieto.it2014.ui.achievments;

import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.domain.user.query.GetUserByIdQuery;
import com.tieto.it2014.ui.session.UserSession;
import java.util.ArrayList;
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
    private List<Achievement> listOfAchievments;
    private User friend;

    @SpringBean
    private GetUserByIdQuery getUserByIdQuery;

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

        listOfAchievments = new ArrayList<>();

        add(new ListView<Achievement>("achievmentList", listOfAchievments) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem<Achievement> item) {
                Achievement achievment = item.getModelObject();

                item.add(new UserAchievementsListItem("achievmentItem"));
            }

        });
    }

}
