//package com.tieto.it2014.dao.user.command;
//
//import com.tieto.it2014.dao.BaseDaoTest;
//import com.tieto.it2014.dao.user.query.GetUserByIdQueryDaoMem;
//import com.tieto.it2014.domain.user.command.SaveUserCommand;
//import com.tieto.it2014.domain.user.entity.User;
//import com.tieto.it2014.domain.user.query.GetUserByIdQuery;
//import org.junit.Assert;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//
//public class SaveUserCommandDaoMemTest extends BaseDaoTest {
//
//    @Autowired
//    private SaveUserCommand.Dao saveUserCommand;
//
//    @Autowired
//    private GetUserByIdQuery.Dao getUserById;
//
//    @Test
//    public void assigns_id_for_new_entities() {
//        User user = new User("User Name", 1999);
//        Long id = saveUserCommand.execute(user);
//        assertNotNull(id);
//    }
//
//    @Test
//    public void adds_new_users_to_database() {
//        User savedUser = new User("Test User", 1999);
//        Long id = saveUserCommand.execute(savedUser);
//        User foundUser = getUserById.resultOrNull(id);
//        assertNotNull(foundUser);
//    }
//
//    @Test
//    public void updates_existing_user() {
//        User savedUser = new User("Test User", 1999);
//        Long id = saveUserCommand.execute(savedUser);
//
//        User foundUser = getUserById.resultOrNull(id);
//        foundUser.name = "Changed test name";
//        saveUserCommand.execute(foundUser);
//
//        foundUser = getUserById.resultOrNull(id);
//        assertEquals("Changed test name", foundUser.name);
//    }
//
//}
