package com.tieto.it2014.ui.user;

import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.domain.user.query.AllFriendsQuery;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.List;

public class UserFriendsPage extends WebPage {
    private static final long serialVersionUID = 1L;
    public static final String USER_ID = "userId";
    private final String userId;

    @SpringBean
    private AllFriendsQuery allFriendsQuery;

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new Label("name", userId));
        IModel<List<User>> userFriendsModel = initUserFriendsListModel();

        RepeatingView view = new RepeatingView("item");
        for (User u : userFriendsModel.getObject()) {
            view.add(new Label(view.newChildId(), u.username));
        }
        add(view);
    }

    public UserFriendsPage(PageParameters params) {
        userId = params.get(USER_ID).toString();
    }

    private IModel<List<User>> initUserFriendsListModel() {
        return new LoadableDetachableModel<List<User>>() {
            private static final long serialVersionUID = 1L;

            @Override
            protected List<User> load() {
                return allFriendsQuery.result(userId);
            }
        };
    }
}
