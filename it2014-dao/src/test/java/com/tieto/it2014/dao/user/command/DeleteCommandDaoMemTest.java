//package com.tieto.it2014.dao.user.command;
//
//import com.tieto.it2014.dao.BaseDaoTest;
//import com.tieto.it2014.dao.user.query.GetUserByIdQueryDaoMem;
//import com.tieto.it2014.domain.user.command.DeleteUserCommand;
//import com.tieto.it2014.domain.user.command.SaveUserCommand;
//import com.tieto.it2014.domain.user.entity.User;
//import com.tieto.it2014.domain.user.query.GetUserByIdQuery;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertNull;
//
//public class DeleteCommandDaoMemTest extends BaseDaoTest {
//
//    @Autowired
//    private SaveUserCommand.Dao saveUser;
//
//    @Autowired
//    private DeleteUserCommand.Dao deleteUser;
//
//    @Autowired
//    private GetUserByIdQuery.Dao getUserById;
//
//    @Test
//    public void removes_user_from_database() {
//        User user = new User("Some user", 2002);
//        Long id = saveUser.execute(user);
//
//        User foundUser = getUserById.resultOrNull(id);
//        assertNotNull(foundUser);
//
//        deleteUser.execute(foundUser);
//        foundUser = getUserById.resultOrNull(id);
//        assertNull(foundUser);
//    }
//
//}
