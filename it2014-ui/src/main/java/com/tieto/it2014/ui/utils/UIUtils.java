package com.tieto.it2014.ui.utils;

import org.apache.wicket.Component;

public class UIUtils {

    public static <T extends Component> T ajaxReady(T component) {
        component.setOutputMarkupId(true);
        component.setOutputMarkupPlaceholderTag(true);
        return component;
    }

    public static <T extends Component> T withInfoMsg(T component, String infoMsg) {
        component.info(infoMsg);
        return component;
    }

}
