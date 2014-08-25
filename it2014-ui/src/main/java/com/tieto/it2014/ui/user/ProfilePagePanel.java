package com.tieto.it2014.ui.user;

import com.tieto.it2014.domain.weight.entity.Weight;
import java.util.List;
import org.apache.wicket.markup.html.panel.Panel;

public class ProfilePagePanel extends Panel {

    private static final long serialVersionUID = 1L;
    private List<Weight> data;
    private String imei;

    public ProfilePagePanel(String id) {
        super(id);
    }

}
