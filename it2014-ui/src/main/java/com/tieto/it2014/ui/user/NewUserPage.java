package com.tieto.it2014.ui.user;

import com.tieto.it2014.domain.user.command.CreateUserCommand;
import org.apache.wicket.markup.html.WebPage;

public class NewUserPage extends WebPage {
  private static final long serialVersionUID = 1L;
  private final CreateUserCommand createUser = new CreateUserCommand();

  @Override
  protected void onInitialize() {
    super.onInitialize();
    add(new EditUserPanel("editUser", createUser.execute()));
  }
}
