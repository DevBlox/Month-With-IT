package com.tieto.it2014.ui.error;

import com.tieto.it2014.ui.BaseWebTest;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

public class ErrorPage404Test extends BaseWebTest {

    private WicketTester tester;

    @Before
    public void setUp() {
        tester = createWicketTester();
    }

    @Test
    public void rendersSuccesfuly() {
        tester.startPage(ErrorPage404.class);
        tester.assertRenderedPage(ErrorPage404.class);
    }

}
