package com.tieto.it2014.ui;

import com.tieto.it2014.dao.user.query.AllUsersQueryDaoMem;
import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.domain.user.query.AllUsersQuery;
import com.tieto.it2014.ui.user.NewUserPage;
import com.tieto.it2014.ui.user.UserListPanel;
import java.util.List;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;

public class HomePage extends WebPage {
  private static final long serialVersionUID = 1L;
  private final AllUsersQuery allUsersQuery = new AllUsersQuery(new AllUsersQueryDaoMem());

  @Override
  protected void onInitialize() {
    super.onInitialize();
    IModel<List<User>> usersModel = initUserListModel();
    add(new UserListPanel("users", usersModel));
    add(initUserCountLabel("userCount", usersModel));
    add(initAddMemberButton("add"));
  }

  private IModel<List<User>> initUserListModel() {
    return new LoadableDetachableModel<List<User>>() {
      private static final long serialVersionUID = 1L;

      @Override
      protected List<User> load() {
        return allUsersQuery.result();
      }
    };
  }

  private Link initAddMemberButton(String wicketId) {
    return new Link(wicketId) {
      private static final long serialVersionUID = 1L;

      @Override
      public void onClick() {
        setResponsePage(NewUserPage.class);
      }
    };
  }

  private Component initUserCountLabel(String wicketId, final IModel<List<User>> listModel) {
    return new Label(wicketId, new Model<Integer>() {
      private static final long serialVersionUID = 1L;

      @Override
      public Integer getObject() {
        return listModel.getObject().size();
      }
    });
  }
}
