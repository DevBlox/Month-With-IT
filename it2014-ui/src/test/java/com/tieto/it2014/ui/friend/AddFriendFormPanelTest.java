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

//    @Test
//    public void throws_error_if_friend_in_list() {
//        ModalWindow modalWindow = new ModalWindow("modal");
//
//        tester.startComponentInPage(
//                new AddFriendFormPanel(modalWindow.getContentId(), modalWindow));
//
//        FormTester formTester = tester.newFormTester(modalWindow.getContentId()
//                + ":add");
//        formTester.setValue("inputFriendEmail", "my@friend.yea");
//        formTester.submit();
//
//    }
}
