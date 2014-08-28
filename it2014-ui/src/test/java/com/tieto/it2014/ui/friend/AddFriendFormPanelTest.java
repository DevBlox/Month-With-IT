package com.tieto.it2014.ui.friend;

import com.tieto.it2014.ui.BaseWebTest;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;

public class AddFriendFormPanelTest extends BaseWebTest {

    private WicketTester tester;

    @Before
    public void setUp() {
        tester = createWicketTester();
    }

}
