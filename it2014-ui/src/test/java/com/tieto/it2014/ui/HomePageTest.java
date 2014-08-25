package com.tieto.it2014.ui;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

public class HomePageTest extends BaseWebTest {
    private WicketTester tester;

    @Before
    public void setUp() {
        tester = createWicketTester();
    }

    @Test
    public void rendersSuccessfully() {
        tester.startPage(HomePage.class);
        tester.assertRenderedPage(HomePage.class);
    }
}
