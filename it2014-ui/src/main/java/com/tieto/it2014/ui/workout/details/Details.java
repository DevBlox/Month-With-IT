package com.tieto.it2014.ui.workout.details;

import com.tieto.it2014.ui.BasePage;
import org.apache.wicket.Component;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * Created by mantas on 18/08/14.
 */
public class Details extends BasePage {

    private static final long serialVersionUID = 1L;
    private String userImei;
    private String workoutToFindImei;
    private int workoutId;

    public Details(final PageParameters params) {
        userImei = params.get("userImei").toString();
        workoutToFindImei = params.get("imei").toString();
        workoutId = Integer.parseInt(params.get("id").toString());
    }

    @Override
    protected Component initFullContent(String wicketId) {
        return new GMapPanel(wicketId, userImei, workoutToFindImei, workoutId);
    }
}
