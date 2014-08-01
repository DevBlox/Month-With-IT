package com.tieto.it2014.ui;

import com.tieto.it2014.dao.user.AllUsersQueryDaoMem;
import com.tieto.it2014.dao.user.SaveUserCommandDaoMem;
import com.tieto.it2014.domain.user.command.SaveUserCommand;
import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.domain.user.query.AllUsersQuery;
import java.util.List;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;

public class HomePage extends WebPage {
  private static final long serialVersionUID = 1L;
  private final AllUsersQuery allUsersQuery = new AllUsersQuery(new AllUsersQueryDaoMem());
  private final SaveUserCommand saveUserCommand = new SaveUserCommand(new SaveUserCommandDaoMem());

  @Override
  protected void onInitialize() {
    super.onInitialize();
    IModel<List<User>> usersModel = initUserListModel();
    add(initUserList("users", usersModel));
    add(initUserCountLabel("userCount", usersModel));
    add(initAddUsersButton("add"));
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

  private ListView<User> initUserList(String wicketId, final IModel<List<User>> usersModel) {
    return new ListView<User>(wicketId, usersModel) {
      private static final long serialVersionUID = 1L;

      @Override
      protected void populateItem(ListItem<User> item) {
        User user = item.getModelObject();
        item.add(new Label("user", user.name + " (" + user.yearOfBirth + ")"));
      }
    };
  }

  private Link initAddUsersButton(String wicketId) {
    return new Link(wicketId) {
      private static final long serialVersionUID = 1L;

      @Override
      public void onClick() {
        addUsers();
      }
    };
  }

  private void addUsers() {
    saveUserCommand.execute(new User(null, "Ona", 1993));
    saveUserCommand.execute(new User(null, "Gerimantas", 1988));
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
