package com.tieto.it2014.dao.user.command;

import com.tieto.it2014.dao.user.query.GetUserByIdQueryDaoMem;
import com.tieto.it2014.domain.user.entity.User;
import org.junit.Assert;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class SaveUserCommandDaoMemTest {
  private final SaveUserCommandDaoMem saveUserCommand = new SaveUserCommandDaoMem();
  private final GetUserByIdQueryDaoMem getUserById = new GetUserByIdQueryDaoMem();

  @Test
  public void assigns_id_for_new_entities() {
    User user = new User(null, "User Name", 1999);
    saveUserCommand.execute(user);
    assertNotNull(user.id);
  }

  @Test
  public void adds_new_users_to_database() {
    User savedUser = new User(null, "Test User", 1999);
    saveUserCommand.execute(savedUser);
    User foundUser = getUserById.resultOrNull(savedUser.id);
    assertNotNull(foundUser);
  }

  @Test
  public void updates_existing_user() {
    User savedUser = new User(null, "Test User", 1999);
    saveUserCommand.execute(savedUser);
    Long savedId = savedUser.id;
    savedUser.name = "Changed test name";
    saveUserCommand.execute(savedUser);
    User foundUser = getUserById.resultOrNull(savedId);
    Assert.assertEquals(savedUser.name, foundUser.name);
  }
}
