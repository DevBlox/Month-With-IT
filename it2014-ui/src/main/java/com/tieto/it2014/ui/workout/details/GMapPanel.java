package com.tieto.it2014.ui.workout.details;

import com.googlecode.wickedcharts.highcharts.options.Axis;
import com.googlecode.wickedcharts.highcharts.options.ChartOptions;
import com.googlecode.wickedcharts.highcharts.options.ExportingOptions;
import com.googlecode.wickedcharts.highcharts.options.Function;
import com.googlecode.wickedcharts.highcharts.options.Legend;
import com.googlecode.wickedcharts.highcharts.options.Options;
import com.googlecode.wickedcharts.highcharts.options.PlotLine;
import com.googlecode.wickedcharts.highcharts.options.SeriesType;
import com.googlecode.wickedcharts.highcharts.options.Title;
import com.googlecode.wickedcharts.highcharts.options.Tooltip;
import com.googlecode.wickedcharts.highcharts.options.color.HexColor;
import com.googlecode.wickedcharts.highcharts.options.series.Series;
import com.googlecode.wickedcharts.highcharts.options.series.SimpleSeries;
import com.googlecode.wickedcharts.wicket6.highcharts.Chart;
import com.tieto.it2014.domain.user.entity.UserLoc;
import com.tieto.it2014.domain.user.entity.Workout;
import com.tieto.it2014.domain.util.Util;
import com.tieto.it2014.domain.workout.query.WorkoutsQuery;
import com.tieto.it2014.ui.error.ErrorPage404;
import java.security.AccessControlException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.log4j.Logger;
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

    private static final Logger LOGGER = Logger.getLogger(GMapPanel.class);

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
                markers.add(new GLatLng(uLoc.getLatitude(), uLoc.getLongtitude()));
            }

            Util.GraphData data = Util.getGraphData(uLocs);

            // adding chart to page
            Options options = new Options();
            options.setTitle(new Title("  "));

            ChartOptions chartOptions = new ChartOptions();
            chartOptions.setType(SeriesType.SPLINE);
            options.setChartOptions(chartOptions);

            Axis xAxis = new Axis();
            xAxis.setTitle(new Title("Distance, km"));

            String[] array = new String[data.getAxisData().size()];
            int index = 0;
            for (Object value : data.getAxisData()) {
                array[index] = value.toString();
                index++;
            }
            if (data.getAxisData().get(data.getAxisData().size() - 1) > 20) {
                xAxis.setTickInterval(8f);
            }

            xAxis.setCategories(array);

            options.setxAxis(xAxis);

            PlotLine plotLines = new PlotLine();
            plotLines.setValue(0f);
            plotLines.setWidth(1);
            plotLines.setColor(new HexColor("#999999"));

            Axis yAxis = new Axis();
            yAxis.setTitle(new Title("Time, min"));
            yAxis.setPlotLines(Collections.singletonList(plotLines));
            options.setyAxis(yAxis);

            //Do not show exporting btn
            options.setExporting(new ExportingOptions().setEnabled(Boolean.FALSE));

            Tooltip tooltip = new Tooltip();
            tooltip.setFormatter(new Function(
                    "return '<b>Distance: </b>' + this.x + ' km<br /><b>Time:</b> '+ this.y +' min';"));
            options.setTooltip(tooltip);

            options.setLegend(new Legend().setEnabled(Boolean.FALSE));

            Series<Number> series1 = new SimpleSeries();
            series1.setName("Time");
            Number[] array2 = new Number[data.getSeriesData().size()];
            index = 0;
            for (Number value : data.getSeriesData()) {
                array2[index] = value;
                index++;
            }
            series1.setData(array2);
            options.addSeries(series1);

            add(new Chart("chart", options));

            // adding google maps to page
            GMap map = new GMap("map");
            map.setStreetViewControlEnabled(false);
            map.setScaleControlEnabled(true);
            map.setScrollWheelZoomEnabled(true);
            map.fitMarkers(markers);

            map.addOverlay(new GMarker(new GMarkerOptions(map, markers.get(0), "Start", new GIcon("http://haliucinas.eu/images/start.png"), null)));

            map.addOverlay(new GPolyline("red", 1, (float) 1, markers.toArray(new GLatLng[markers.size() - 1])));

            map.addOverlay(new GMarker(new GMarkerOptions(map, markers.get(markers.size() - 1), "End", new GIcon("http://haliucinas.eu/images/end.png"), null)));
            add(map);

            add(initInfoPanel("infoPanel", workoutsModel.getObject()));

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            setResponsePage(ErrorPage404.class);
        }
    }

    private Component initInfoPanel(String infoPanel, Workout workout) {
        Form form = new Form(infoPanel);
        form.add(new Label("start", "Start time: " + workout.getStartTime()));
        form.add(new Label("finish", "Finish time: " + workout.getFinishTime()));
        form.add(new Label("distance", "Distance: " + workout.getDistance() + " km."));
        form.add(new Label("duration", "Duration: " + workout.getDuration()));
        form.add(new Label("avgSpeed", "Average speed: " + Util.formatDoubleToString(workout.getDistanceDouble() / ((double) workout.getDurationInt() / 3600)) + " km/h"));
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
                LOGGER.error(e.getMessage(), e);
                setResponsePage(ErrorPage404.class);
            }
            return null;
        }
    }

}
