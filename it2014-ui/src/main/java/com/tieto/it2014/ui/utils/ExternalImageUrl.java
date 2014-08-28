package com.tieto.it2014.ui.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.model.Model;

/**
 * Created by mantas on 28/08/14.
 */
public class ExternalImageUrl extends WebComponent {

    public ExternalImageUrl(String id, Model<String> imageUrl) {
        super(id);
        add(new AttributeModifier("src", true, imageUrl));
        setVisible(StringUtils.isBlank(imageUrl.getObject()));
    }

    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);
        checkComponentTag(tag, "img");
    }

}