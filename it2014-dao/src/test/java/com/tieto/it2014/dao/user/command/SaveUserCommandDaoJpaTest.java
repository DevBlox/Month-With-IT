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
    private static final String IMEI1 = "123123123";
    private static final String IMEI2 = "1231231234";

    @Test
    public void addsNewUsersToDatabase() {
        User savedUser = new User(IMEI1, "username", "test", "test@username.lt", "", true);
        saveUserCommand.execute(savedUser);
        User foundUser = getUserById.resultOrNull(IMEI1);
        assertNotNull(foundUser);
    }

    @Test
    public void updatesExistingUser() {
        User savedUser = new User(IMEI2, "username1", "test1", "test1@username.lt", "", false);
        saveUserCommand.execute(savedUser);

        User foundUser = getUserById.resultOrNull(IMEI2);
        foundUser.username = USER_NAME;
        saveUserCommand.execute(foundUser);

        foundUser = getUserById.resultOrNull(IMEI2);
        assertEquals(USER_NAME, foundUser.username);
    }

}
