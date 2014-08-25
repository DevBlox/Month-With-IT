package com.tieto.it2014.ui.top;

import com.tieto.it2014.domain.user.entity.UserStats;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;

/**
 * Created by mantas on 20/08/14.
 */
public class TopListItemPanel extends Panel {

    private static final long serialVersionUID = 1L;

    private UserStats st;

    public TopListItemPanel(String id, UserStats st) {
        super(id);
        this.st = st;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new Label("no", new PropertyModel(st, "topId")));
        add(new Label("userName", new PropertyModel(st, "userName")));
        add(new Label("totalDist", new PropertyModel(st, "distance")));
        add(new Label("totalTime", new PropertyModel(st, "time")));
        add(new Label("avgSpeed", new PropertyModel(st, "avgSpeed")));
    }
}
