package com.tieto.it2014.ui.header;

import com.tieto.it2014.ui.user.WeightPage;
import java.util.Objects;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

public class NavbarPanel extends Panel {

    private static final long serialVersionUID = 1L;

    private Link weightPageLink;
    private ListItem weightListItem;

    public NavbarPanel(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        weightPageLink = initWeightLink("weightPageLink");
        weightListItem = new ListItem("li", 0);
        add(weightListItem);
        weightListItem.add(weightPageLink);

        if (Objects.equals(weightPageLink.getPage().getClass(), WeightPage.class)) {
            weightListItem.add(new AttributeAppender("class", new Model<>("active"), " "));
        }
    }

    private Link initWeightLink(String weightPageLink) {
        return new Link(weightPageLink) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {
                setResponsePage(WeightPage.class);
            }

        };
    }

}
