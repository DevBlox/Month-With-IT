package com.tieto.it2014.domain.user.command;

import com.tieto.it2014.domain.user.entity.User;
import java.io.Serializable;

public class CreateUserCommand implements Serializable {
  private static final long serialVersionUID = 1L;

  public User execute() {
    return new User(null, null, null);
  }
}
