package com.tieto.it2014.dao.user.command;

import com.tieto.it2014.dao.BaseDaoTest;
import com.tieto.it2014.domain.user.command.SaveUserCommand;
import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.domain.user.query.GetUserByIdQuery;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SaveUserCommandDaoJpaTest extends BaseDaoTest {

    @Autowired
    private SaveUserCommand.Dao saveUserCommand;

    @Autowired
    private GetUserByIdQuery.Dao getUserById;

    private static final String USER_NAME = "Changed test name";

    @Test
    public void adds_new_users_to_database() {
        User savedUser = new User("123123123", "username", "test", "test@username.lt", "", true);
        saveUserCommand.execute(savedUser);
        User foundUser = getUserById.resultOrNull("123123123");
        assertNotNull(foundUser);
    }

    @Test
    public void updates_existing_user() {
        User savedUser = new User("1231231234", "username1", "test1", "test1@username.lt", "", false);
        saveUserCommand.execute(savedUser);

        User foundUser = getUserById.resultOrNull("1231231234");
        foundUser.username = USER_NAME;
        saveUserCommand.execute(foundUser);

        foundUser = getUserById.resultOrNull("1231231234");
        assertEquals(USER_NAME, foundUser.username);
    }

}
