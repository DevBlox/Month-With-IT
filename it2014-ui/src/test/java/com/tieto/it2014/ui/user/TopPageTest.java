package com.tieto.it2014.ui.user;

import com.tieto.it2014.ui.BaseWebTest;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class TopPageTest extends BaseWebTest {
    private WicketTester tester;

    @Before
    public void setUp() {
        tester = createWicketTester();
    }

    @Test
    public void renders_successfully() {
        tester.startPage(TopPage.class);
        tester.assertRenderedPage(TopPage.class);
    }
}
