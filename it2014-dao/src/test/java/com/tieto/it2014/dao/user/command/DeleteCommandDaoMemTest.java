package com.tieto.it2014.dao.user.command;

import com.tieto.it2014.dao.BaseDaoTest;
import com.tieto.it2014.domain.user.command.DeleteUserCommand;
import com.tieto.it2014.domain.user.command.SaveUserCommand;
import com.tieto.it2014.domain.user.query.GetUserByIdQuery;
import org.springframework.beans.factory.annotation.Autowired;

public class DeleteCommandDaoMemTest extends BaseDaoTest {

    @Autowired
    private SaveUserCommand.Dao saveUser;

    @Autowired
    private DeleteUserCommand.Dao deleteUser;

    @Autowired
    private GetUserByIdQuery.Dao getUserById;

}
