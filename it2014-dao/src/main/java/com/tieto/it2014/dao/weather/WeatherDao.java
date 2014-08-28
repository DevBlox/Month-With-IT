package com.tieto.it2014.dao.weather;

import java.net.URL;
import java.util.Scanner;

/**
 * Created by mantas on 28/08/14.
 */
public class WeatherDao {

    private static String jsonSrc;
    private static final String url = "http://api.openweathermap.org/data/2.5/weather?q=Vilnius";

    private static void getJsonFromRemoteApi() {
        try {
            URL urlObj = new URL(url);
            Scanner scan = new Scanner(urlObj.openStream());
            jsonSrc = "";
            while (scan.hasNext())
                jsonSrc += scan.nextLine();
            scan.close();
        } catch (Exception e) {}
    }

    public static String getJsonSrc() {
        getJsonFromRemoteApi();
        return jsonSrc;
    }

}
