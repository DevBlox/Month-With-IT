package com.tieto.it2014.ui;

import java.util.Arrays;
import java.util.List;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.RepeatingView;

public class HomePage extends WebPage {
  private static final long serialVersionUID = 1L;
  List<User> users = Arrays.asList(
      new User(1L, "Simas", 1992),
      new User(2L, "Živilė", 1994),
      new User(3L, "Tomas", 1993),
      new User(4L, "Jolanta", 1997),
      new User(5L, "Kasparas", 1989));

  @Override
  protected void onInitialize() {
    super.onInitialize();
    RepeatingView view = new RepeatingView("users");
    for (User user : users) {
      view.add(new Label(view.newChildId(), user.name + " (" + user.yearOfBirth + ")"));
    }
    add(view);
  }
}
