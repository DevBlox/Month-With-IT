package com.tieto.it2014.dao.weather;

import java.net.URL;
import java.util.Scanner;
import org.apache.log4j.Logger;

/**
 * Created by mantas on 28/08/14.
 */
public class WeatherDao {

    private static String jsonSrc;
    private static final String URL = "http://api.openweathermap.org/data/2.5/weather?id=593116";

    private static final Logger LOGGER = Logger.getLogger(WeatherDao.class);

    private WeatherDao() {
    }

    private static void getJsonFromRemoteApi() {
        try {
            URL urlObj = new URL(URL);
            Scanner scan = new Scanner(urlObj.openStream());
            jsonSrc = "";
            while (scan.hasNext()) {
                jsonSrc += scan.nextLine();
            }
            scan.close();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    public static String getJsonSrc() {
        getJsonFromRemoteApi();
        return jsonSrc;
    }

}
