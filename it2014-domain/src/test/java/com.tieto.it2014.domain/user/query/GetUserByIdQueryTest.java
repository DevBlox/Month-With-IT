//package com.tieto.it2014.domain.user.query;
//
//import com.tieto.it2014.domain.BaseDomainTest;
//import com.tieto.it2014.domain.user.entity.User;
//import org.junit.Assert;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//
//public class GetUserByIdQueryTest extends BaseDomainTest {
//
//    @Autowired
//    @InjectMocks
//    private GetUserByIdQuery getUserByIdQuery;
//
//    @Mock
//    private GetUserByIdQuery.Dao getUserByIdQueryDao;
//
//    @Test
//    public void get_uder_by_id_query_calls_the_dao() {
//        User user10 = new User(10L, "10", 1980);
//        User user20 = new User(20L, "20", 1980);
//
//        Mockito.when(getUserByIdQueryDao.resultOrNull(Mockito.eq(10L))).thenReturn(user10);
//        Mockito.when(getUserByIdQueryDao.resultOrNull(Mockito.eq(20L))).thenReturn(user20);
//        Mockito.when(getUserByIdQueryDao.resultOrNull(Mockito.eq(30L))).thenReturn(null);
//
//        Assert.assertNotNull(getUserByIdQuery.resultOrNull(10L));
//        Assert.assertNotNull(getUserByIdQuery.resultOrNull(20L));
//        Assert.assertNull(getUserByIdQuery.resultOrNull(30L));
//
//        Mockito.verify(getUserByIdQueryDao, Mockito.times(3)).resultOrNull(Mockito.any(Long.class));
//    }
//}
