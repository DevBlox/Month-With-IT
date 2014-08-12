package com.tieto.it2014.ui.friend;

import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

public class FriendPanel extends Panel {

    private static final long serialVersionUID = 1L;

    public FriendPanel(String id) {
        super(id);
    }

    private ListView<List<String>> labels;
    private List<String> array;

    @Override
    protected void onInitialize() {
        super.onInitialize();

        array = new ArrayList();
        for (int i = 0; i < 200; i++) {
            array.add("Friend " + i);
        }

        labels = new ListView("friendListItem", array) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem item) {
                String label = item.getModelObject().toString();
                item.add(new Label("labelItem", label));
            }
        };
        add(labels);
    }

}
