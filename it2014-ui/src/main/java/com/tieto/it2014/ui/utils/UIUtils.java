package com.tieto.it2014.ui.utils;

import org.apache.wicket.Component;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.cycle.RequestCycle;

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

    public static String getUrl() {
        Url url = RequestCycle.get().getRequest().getUrl();
        return RequestCycle.get().getUrlRenderer().renderFullUrl(url);
    }

}
