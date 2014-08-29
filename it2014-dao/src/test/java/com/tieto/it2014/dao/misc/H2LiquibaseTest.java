package com.tieto.it2014.dao.misc;

import com.tieto.it2014.dao.BaseDaoTest;
import java.sql.ResultSet;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class H2LiquibaseTest extends BaseDaoTest {

    @Autowired
    private org.apache.commons.dbcp.BasicDataSource dataSource;

    @Test
    public void liquibaseHasRun() throws Exception {
        ResultSet results = dataSource
                .getConnection()
                .createStatement()
                .executeQuery("SELECT COUNT(*) AS COUNT FROM DATABASECHANGELOG");
        results.next();
        Assert.assertTrue(results.getInt("COUNT") > 0);
    }

}
