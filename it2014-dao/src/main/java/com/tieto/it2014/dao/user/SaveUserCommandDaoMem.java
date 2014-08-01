package com.tieto.it2014.dao.user;

import com.tieto.it2014.dao.DatabaseInMemory;
import com.tieto.it2014.domain.user.command.SaveUserCommand;
import com.tieto.it2014.domain.user.entity.User;
import java.util.Objects;

public class SaveUserCommandDaoMem implements SaveUserCommand.Dao {
  private static final long serialVersionUID = 1L;

  @Override
  public void execute(User user) {
    synchronized (DatabaseInMemory.users) {
      int index = -1;
      for (int i = 0; i < DatabaseInMemory.users.size(); i++) {
        if (Objects.equals(DatabaseInMemory.users.get(i).id, user.id)) {
          index = i;
          break;
        }
      }
      if (index < 0) {
        user.id = DatabaseInMemory.lastUserId.incrementAndGet();
        DatabaseInMemory.users.add(user.createCopy());
      } else {
        DatabaseInMemory.users.set(index, user.createCopy());
      }
    }
  }
}
