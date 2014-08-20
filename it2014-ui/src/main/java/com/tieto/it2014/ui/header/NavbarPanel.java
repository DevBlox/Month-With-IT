package com.tieto.it2014.ui.header;

import com.tieto.it2014.ui.user.StatisticsPage;
import com.tieto.it2014.ui.user.TopPage;
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
    private Link statisticsPageLink;
    private Link topPageLink;
    private ListItem weightListItem;
    private ListItem statisticsListItem;
    private ListItem topListItem;

    public NavbarPanel(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        weightPageLink = initWeightLink("weightPageLink");
        statisticsPageLink = initStatisticsLink("statisticsPageLink");
        topPageLink = initTopLink("topPageLink");

        weightListItem = new ListItem("weightListItem", 0);
        statisticsListItem = new ListItem("statisticsListItem", 1);
        topListItem = new ListItem("topListItem", 2);

        add(weightListItem);
        add(statisticsListItem);
        add(topListItem);

        weightListItem.add(weightPageLink);
        statisticsListItem.add(statisticsPageLink);
        topListItem.add(topPageLink);

        if (Objects.equals(weightPageLink.getPage().getClass(), WeightPage.class)) {
            weightListItem.add(new AttributeAppender("class", new Model<>("active"), " "));
        } else if (Objects.equals(weightPageLink.getPage().getClass(), StatisticsPage.class)) {
            statisticsListItem.add(new AttributeAppender("class", new Model<>("active"), " "));
        } else if (Objects.equals(weightPageLink.getPage().getClass(), TopPage.class)) {
            topListItem.add(new AttributeAppender("class", new Model<>("active"), " "));
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

    private Link initStatisticsLink(String statisticsPageLink) {
        return new Link(statisticsPageLink) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {
                setResponsePage(StatisticsPage.class);
            }

        };
    }

    private Link initTopLink(String topPageLink) {
        return new Link(topPageLink) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {
                setResponsePage(TopPage.class);
            }

        };
    }

}