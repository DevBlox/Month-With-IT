package com.tieto.it2014.ui.workout.details;

import com.tieto.it2014.domain.user.entity.UserLoc;
import com.tieto.it2014.domain.user.entity.Workout;
import com.tieto.it2014.domain.workout.query.WorkoutsQuery;
import com.tieto.it2014.ui.error.ErrorPage404;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.gmap.GMap;
import org.wicketstuff.gmap.api.*;

import java.security.AccessControlException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by mantas on 18/08/14.
 */
public class GMapPanel extends Panel {

    private String userImei;
    private String workoutToFindImei;
    private int workoutId;

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
        List<UserLoc> uLocs = workoutsModel.getObject().getLocs();
        List<GLatLng> markers = new ArrayList<>();
        for (UserLoc uLoc : uLocs) {
            markers.add(new GLatLng(uLoc.latitude, uLoc.longtitude));
        }

        GMap map = new GMap("map");
        map.setStreetViewControlEnabled(false);
        map.setScaleControlEnabled(true);
        map.setScrollWheelZoomEnabled(true);
        map.fitMarkers(markers);
        map.addOverlay(new GMarker(new GMarkerOptions(map, markers.get(0), "A", new GIcon("/img/start.png"), null)));
        map.addOverlay(new GMarker(new GMarkerOptions(map, markers.get(markers.size()-1), "B", new GIcon("/img/end.png"), null)));
        map.addOverlay(new GPolyline("red", 1, (float) 1, markers.toArray(new GLatLng[markers.size()-1])));
        add(map);
    }

    private class WorkoutsModel extends LoadableDetachableModel<Workout> {

        private static final long serialVersionUID = 1L;

        @Override
        protected Workout load() {
            List<Workout> list;
            try {
                list = workoutQuery.result(userImei, workoutToFindImei, workoutId);
                return list.get(workoutId-1);
            } catch (AccessControlException e) {
                setResponsePage(ErrorPage404.class);
            }
            return null;
        }
    }

}
