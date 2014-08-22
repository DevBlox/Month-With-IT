package com.tieto.it2014.ui.workout.details;

import com.googlecode.wickedcharts.highcharts.options.Axis;
import com.googlecode.wickedcharts.highcharts.options.ChartOptions;
import com.googlecode.wickedcharts.highcharts.options.ExportingOptions;
import com.googlecode.wickedcharts.highcharts.options.HorizontalAlignment;
import com.googlecode.wickedcharts.highcharts.options.Legend;
import com.googlecode.wickedcharts.highcharts.options.LegendLayout;
import com.googlecode.wickedcharts.highcharts.options.Options;
import com.googlecode.wickedcharts.highcharts.options.PlotLine;
import com.googlecode.wickedcharts.highcharts.options.SeriesType;
import com.googlecode.wickedcharts.highcharts.options.Title;
import com.googlecode.wickedcharts.highcharts.options.VerticalAlignment;
import com.googlecode.wickedcharts.highcharts.options.color.HexColor;
import com.googlecode.wickedcharts.highcharts.options.series.Series;
import com.googlecode.wickedcharts.highcharts.options.series.SimpleSeries;
import com.googlecode.wickedcharts.wicket6.highcharts.Chart;
import com.tieto.it2014.domain.Util.Util;
import com.tieto.it2014.domain.user.entity.UserLoc;
import com.tieto.it2014.domain.user.entity.Workout;
import com.tieto.it2014.domain.workout.query.WorkoutsQuery;
import com.tieto.it2014.ui.error.ErrorPage404;
import java.security.AccessControlException;
import java.util.ArrayList;
import java.util.Collections;
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
        List<Double> axisData = new ArrayList<>();
        List<Double> seriesData = new ArrayList<>();

//        0.49211000000000005 0.5077200000000001
//        479.0 488.0
//        0.4968599999999999 0.5110899999999999
//        425.0 435.0
//        0.4855199999999999 0.5018199999999999
//        416.0 426.0
        try {
            uLocs = workoutsModel.getObject().getLocs();

            for (UserLoc uLoc : uLocs) {
                markers.add(new GLatLng(uLoc.latitude, uLoc.longtitude));
            }

//            Double dist = 0.0;
//            Double time = 0.0;
//            int timeInSeconds = 0;
//            Double totalTime = 0.0;
//            Double totalDist = 0.0;
//            Double km = 0.0;
//
//            for (int i = 0; i <= uLocs.size() - 2; i++) {
//                UserLoc ul1 = uLocs.get(i);
//                UserLoc ul2 = uLocs.get(i + 1);
//                if (dist + Util.calculateDistance(ul1.latitude, ul1.longtitude, ul1.altitude, ul2.latitude, ul2.longtitude, ul2.altitude) > 0.5) {
//                    Double distDiff = Util.calculateDistance(ul1.latitude, ul1.longtitude, ul1.altitude, ul2.latitude, ul2.longtitude, ul2.altitude) * 100;
//                    Double timeDiff = (double) Util.calculateDuration(ul1.timeStamp, ul2.timeStamp);
//                    time += (double) timeDiff / distDiff;
//                    totalTime += time;
//                    km += 0.5;
//                    axisData.add(km);
//                    seriesData.add((time % 3600) / 60);
//                    dist = 0.0;
//                    time = timeDiff - (double) timeDiff / distDiff;
//                }
//                timeInSeconds += Util.calculateDuration(ul1.timeStamp, ul2.timeStamp);
//                time += Util.calculateDuration(ul1.timeStamp, ul2.timeStamp);
//                totalDist += Util.calculateDistance(ul1.latitude, ul1.longtitude, ul1.altitude, ul2.latitude, ul2.longtitude, ul2.altitude);
//                dist += Util.calculateDistance(ul1.latitude, ul1.longtitude, ul1.altitude, ul2.latitude, ul2.longtitude, ul2.altitude);
//            }
//            axisData.add((km + (((totalDist * 1000) % 500) / 1000)));
//            seriesData.add(((timeInSeconds - totalTime) % 3600) / 60);
//
//            double dist = 0;
//            double distDiffSum = 0;
//            double totalDist = 0;
//            float time = 0;
//            float timeDiffSum = 0;
//            for (int i = 0; i <= uLocs.size() - 2; i++) {
//                UserLoc ul1 = uLocs.get(i+1);
//                UserLoc ul2 = uLocs.get(i);
//                if (dist + Util.calculateDistance(ul1.latitude, ul1.longtitude, ul1.altitude, ul2.latitude, ul2.longtitude, ul2.altitude) > 0.5) {
//
//                    System.out.println(dist + " " + (dist + Util.calculateDistance(ul1.latitude, ul1.longtitude, ul1.altitude, ul2.latitude, ul2.longtitude, ul2.altitude)));
//
//                    distDiffSum += Util.calculateDistance(ul1.latitude, ul1.longtitude, ul1.altitude, ul2.latitude, ul2.longtitude, ul2.altitude);
//                    timeDiffSum += (float) Util.calculateDuration(ul1.timeStamp, ul2.timeStamp);
//
//                    Double distDiff = Util.calculateDistance(ul1.latitude, ul1.longtitude, ul1.altitude, ul2.latitude, ul2.longtitude, ul2.altitude) * 100;
//                    float timeDiff = (float) Util.calculateDuration(ul1.timeStamp, ul2.timeStamp);
//
//                    time += timeDiff / distDiff;
//                    //System.out.println(time);
//                    seriesData.add((time % 3600) / 60);
//                    Double minChange = 0.500 - dist;
//                    dist += minChange;
//                    totalDist += dist;
//                    axisData.add(totalDist);
//                    dist = (distDiff/100) - minChange;
//                    time = (float)(timeDiff - timeDiff / distDiff);
//                }
//                dist += Util.calculateDistance(ul1.latitude, ul1.longtitude, ul1.altitude, ul2.latitude, ul2.longtitude, ul2.altitude);
//                time += Util.calculateDuration(ul1.timeStamp, ul2.timeStamp);
//            }
//            axisData.add(dist + totalDist - distDiffSum);
//            seriesData.add(((time-timeDiffSum) % 3600) / 60);


            UserLoc ul1 = uLocs.get(0);
            UserLoc ul2 = uLocs.get(1);
            Double lc = 0.5;
            Double time = 0.0;
            int totalTime = 0;
            Double timeDiffSum = 0.0;
            Double sum = 0.0;
            double dist = Util.calculateDistance(ul1.latitude, ul1.longtitude, ul1.altitude, ul2.latitude, ul2.longtitude, ul2.altitude);
            for (int i = 1; i <= uLocs.size() - 2; i++) {

                ul1 = uLocs.get(i);
                ul2 = uLocs.get(i+1);

                if (dist + Util.calculateDistance(ul1.latitude, ul1.longtitude, ul1.altitude, ul2.latitude, ul2.longtitude, ul2.altitude) >= lc) {

                    timeDiffSum += Util.calculateDuration(ul1.timeStamp, ul2.timeStamp);

                    Double distDiff = Util.calculateDistance(ul1.latitude, ul1.longtitude, ul1.altitude, ul2.latitude, ul2.longtitude, ul2.altitude) * 100;
                    float timeDiff = Util.calculateDuration(ul1.timeStamp, ul2.timeStamp);

                    time += timeDiff / distDiff;
                    Double addValue = time.isNaN() ? seriesData.get(seriesData.size()-1) : time ;
                    addValue = Util.truncate(((addValue % 3600) / 60), 2);
                    seriesData.add(addValue);
                    sum += time;
                    System.out.println(time);
                    axisData.add(lc);
                    time = (timeDiff - timeDiff / distDiff);

                    lc += 0.5;
                }

                dist += Util.calculateDistance(ul1.latitude, ul1.longtitude, ul1.altitude, ul2.latitude, ul2.longtitude, ul2.altitude);
                time += Util.calculateDuration(ul1.timeStamp, ul2.timeStamp);
                totalTime += Util.calculateDuration(ul1.timeStamp, ul2.timeStamp);

            }
            axisData.add(dist);
            System.out.println(time-timeDiffSum);
            seriesData.add(Util.truncate((((time-timeDiffSum) % 3600) / 60), 2));
            sum += time-timeDiffSum;

            System.out.println(sum + " " + totalTime);

            // adding chart to page
            Options options = new Options();
            options.setTitle(new Title("My speed chart"));

            ChartOptions chartOptions = new ChartOptions();
            chartOptions.setType(SeriesType.LINE);
            chartOptions.setMarginRight(130);
            chartOptions.setMarginBottom(25);
            options.setChartOptions(chartOptions);

            Title title = new Title("Average workout speed");
            title.setX(-20);
            options.setTitle(title);

            Axis xAxis = new Axis();
            String[] array = new String[axisData.size()];
            int index = 0;
            for (Object value : axisData) {
                array[index] = Util.formatDoubleToString((double) value);
                index++;
            }
            //xAxis.setTitle(new Title("Distance"));
            xAxis.setCategories(array);
            options.setxAxis(xAxis);

            PlotLine plotLines = new PlotLine();
            plotLines.setValue(0f);
            plotLines.setWidth(1);
            plotLines.setColor(new HexColor("#999999"));

            Axis yAxis = new Axis();
            yAxis.setTitle(new Title("Time (min.)"));
            yAxis.setPlotLines(Collections.singletonList(plotLines));
            options.setyAxis(yAxis);

            //Do not show exporting btn
            options.setExporting(new ExportingOptions().setEnabled(Boolean.FALSE));

            options.setLegend(new Legend().setEnabled(Boolean.FALSE));

            Series<Number> series1 = new SimpleSeries();
            series1.setName("");
            Number[] array2 = new Number[axisData.size()];
            index = 0;
            for (Number value : seriesData) {
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
                setResponsePage(ErrorPage404.class);
            }
            return null;
        }
    }

}
