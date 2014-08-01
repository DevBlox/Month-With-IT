package com.tieto.it2014.ui;

import com.tieto.it2014.dao.user.AllUsersQueryDaoMem;
import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.domain.user.query.AllUsersQuery;
import java.util.List;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.RepeatingView;

public class HomePage extends WebPage {
  private static final long serialVersionUID = 1L;
  private final AllUsersQuery allUsersQuery = new AllUsersQuery(new AllUsersQueryDaoMem());

  @Override
  protected void onInitialize() {
    super.onInitialize();
    List<User> users = allUsersQuery.result();
    RepeatingView view = new RepeatingView("users");
    for (User user : users) {
      view.add(new Label(view.newChildId(), user.name + " (" + user.yearOfBirth + ")"));
    }
    add(view);
  }
}
