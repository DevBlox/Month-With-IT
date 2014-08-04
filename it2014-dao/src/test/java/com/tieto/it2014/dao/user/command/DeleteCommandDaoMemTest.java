package com.tieto.it2014.dao.user.command;

import com.tieto.it2014.dao.BaseDaoTest;
import com.tieto.it2014.dao.user.query.GetUserByIdQueryDaoMem;
import com.tieto.it2014.domain.user.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class DeleteCommandDaoMemTest extends BaseDaoTest {

    @Autowired
    private SaveUserCommandDaoMem saveUser;

    @Autowired
    private DeleteCommandDaoMem deleteUser;

    @Autowired
    private GetUserByIdQueryDaoMem getUserById;

    @Test
    public void removes_user_from_database() {
        User user = new User(null, "Some user", 2002);
        saveUser.execute(user);
        assertNotNull(getUserById.resultOrNull(user.id));
        deleteUser.execute(user);
        assertNull(getUserById.resultOrNull(user.id));
    }
}
