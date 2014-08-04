package com.tieto.it2014.dao.misc;

import com.tieto.it2014.dao.BaseDaoTest;
import com.tieto.it2014.domain.user.command.CreateUserCommand;
import com.tieto.it2014.domain.user.entity.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.exceptions.verification.WantedButNotInvoked;

public class MockTest extends BaseDaoTest {

    @Mock
    private CreateUserCommand createUserCommand;

    @After
    public void tearDown() {
        Mockito.reset(createUserCommand);
    }

    @Test
    public void testMock() {
        User user = new User(666L, "666", 666);

        Assert.assertNotNull(createUserCommand);

        Mockito.when(createUserCommand.execute()).thenReturn(user);
        User mockedUser = createUserCommand.execute();

        Assert.assertNotNull(mockedUser);
        Assert.assertEquals(user.id, mockedUser.id);
        Assert.assertEquals(user.name, mockedUser.name);
        Assert.assertEquals(user.yearOfBirth, mockedUser.yearOfBirth);

        Mockito.verify(createUserCommand, Mockito.times(1)).execute();
    }

    @Test(expected = WantedButNotInvoked.class)
    public void testNotInvokedMock() {
        Mockito.verify(createUserCommand, Mockito.times(1)).execute();
    }

}
