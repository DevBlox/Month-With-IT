package com.tieto.it2014.dao.user.command;

import com.tieto.it2014.dao.user.query.GetUserByIdQueryDaoMem;
import com.tieto.it2014.domain.user.entity.User;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class DeleteCommandDaoMemTest {
  private final SaveUserCommandDaoMem saveUser = new SaveUserCommandDaoMem();
  private final DeleteCommandDaoMem deleteUser = new DeleteCommandDaoMem();
  private final GetUserByIdQueryDaoMem getUserById = new GetUserByIdQueryDaoMem();

  @Test
  public void removes_user_from_database() {
    User user = new User(null, "Some user", 2002);
    saveUser.execute(user);
    assertNotNull(getUserById.resultOrNull(user.id));
    deleteUser.execute(user);
    assertNull(getUserById.resultOrNull(user.id));
  }
}
