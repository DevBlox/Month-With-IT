package com.tieto.it2014.dao.misc;

import com.tieto.it2014.dao.BaseDaoTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.ResultSet;

public class H2LiquibaseTest extends BaseDaoTest {

    @Autowired
    private org.apache.commons.dbcp.BasicDataSource dataSource;

    @Test
    public void liquibase_has_run() throws Exception {
        ResultSet results = dataSource
                .getConnection()
                .createStatement()
                .executeQuery("SELECT COUNT(*) AS COUNT FROM USERS");
        results.next();
        Assert.assertEquals(4, results.getInt("COUNT"));
    }

}
