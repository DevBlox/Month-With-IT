package com.tieto.it2014.ui.workout.details;

import com.tieto.it2014.domain.Util.Util;
import com.tieto.it2014.domain.user.entity.UserLoc;
import com.tieto.it2014.domain.user.entity.Workout;
import com.tieto.it2014.domain.workout.query.WorkoutsQuery;
import com.tieto.it2014.ui.error.ErrorPage404;
import java.security.AccessControlException;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.gmap.GMap;
import org.wicketstuff.gmap.api.GIcon;
import org.wicketstuff.gmap.api.GLatLng;
import org.wicketstuff.gmap.api.GMarker;
import org.wicketstuff.gmap.api.GMarkerOptions;
import org.wicketstuff.gmap.api.GPolyline;

/**
 * Created by mantas on 18/08/14.
 */
public class GMapPanel extends Panel {

    private static final long serialVersionUID = 1L;

    private final String userImei;
    private final String workoutToFindImei;
    private final int workoutId;

    @SpringBean
    private WorkoutsQuery workoutQuery;

    public GMapPanel(String id, String userImei, String workoutToFindImei, int workoutId) {
        super(id);
        this.userImei = userImei;
        this.workoutToFindImei = workoutToFindImei;
        this.workoutId = workoutId;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        WorkoutsModel workoutsModel = new WorkoutsModel();
        List<UserLoc> uLocs;
        List<GLatLng> markers = new ArrayList<>();

        try {
            uLocs = workoutsModel.getObject().getLocs();
            for (UserLoc uLoc : uLocs) {
                markers.add(new GLatLng(uLoc.latitude, uLoc.longtitude));
            }

            GMap map = new GMap("map");
            map.setStreetViewControlEnabled(false);
            map.setScaleControlEnabled(true);
            map.setScrollWheelZoomEnabled(true);
            map.fitMarkers(markers);

            //TODO: Find a proper way to load images
            map.addOverlay(new GMarker(new GMarkerOptions(map, markers.get(0), "Start", new GIcon("http://haliucinas.eu/images/start.png"), null)));

            map.addOverlay(new GPolyline("red", 1, (float) 1, markers.toArray(new GLatLng[markers.size() - 1])));

            map.addOverlay(new GMarker(new GMarkerOptions(map, markers.get(markers.size() - 1), "End", new GIcon("http://haliucinas.eu/images/end.png"), null)));
            add(map);

            add(initInfoPanel("infoPanel", workoutsModel.getObject()));

        } catch (Exception e) {
            setResponsePage(ErrorPage404.class);
        }
    }

    private Component initInfoPanel(String infoPanel, Workout workout) {
        Form form = new Form(infoPanel);
        form.add(new Label("start", "Start time: " + workout.getStartTime()));
        form.add(new Label("finish", "Finish time: " + workout.getFinishTime()));
        form.add(new Label("distance", "Distance: " + workout.getDistance() + " km."));
        form.add(new Label("duration", "Duration: " + workout.getDuration()));
        form.add(new Label("avgSpeed", "Average speed: " + Util.formatDoubleToString(workout.getDistanceDouble()/((double)workout.getDurationInt()/3600)) + " km/h"));
        return form;
    }

    private class WorkoutsModel extends LoadableDetachableModel<Workout> {

        private static final long serialVersionUID = 1L;

        @Override
        protected Workout load() {
            List<Workout> list;
            try {
                list = workoutQuery.result(userImei, workoutToFindImei, workoutId);
                return list.get(workoutId - 1);
            } catch (AccessControlException e) {
                setResponsePage(ErrorPage404.class);
            }
            return null;
        }
    }

}
