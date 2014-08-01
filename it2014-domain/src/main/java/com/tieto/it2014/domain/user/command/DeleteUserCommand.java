package com.tieto.it2014.domain.user.command;

import com.tieto.it2014.domain.user.entity.User;
import java.io.Serializable;

public class DeleteUserCommand implements Serializable {
  private static final long serialVersionUID = 1L;
  private final Dao dao;

  public interface Dao extends Serializable {
    void execute(User user);
  }

  public DeleteUserCommand(Dao dao) {
    this.dao = dao;
  }

  public void execute(User user) {
    dao.execute(user);
  }
}
