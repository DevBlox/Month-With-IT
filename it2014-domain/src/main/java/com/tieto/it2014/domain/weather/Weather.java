package com.tieto.it2014.domain.weather;

import java.io.Serializable;

/**
 * Created by mantas on 28/08/14.
 */
public class Weather implements Serializable {

    private static final long serialVersionUID = 1L;

    private String mainWeather;
    private String weatherDescription;
    private String icon;
    private int iconId;

    private double tempInKelvins;
    private double tempInCelsius;

    private int pressure;
    private double humidity;

    private double windSpeed;
    private double windDeg;

    private double clouds;

    private Long timestmap;

    public Long getTimestmap() {
        return timestmap;
    }

    public void setTimestmap(Long timestmap) {
        this.timestmap = timestmap;
    }

    public String getMainWeather() {
        return mainWeather;
    }

    public void setMainWeather(String mainWeather) {
        this.mainWeather = mainWeather;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public double getTempInKelvins() {
        return tempInKelvins;
    }

    public void setTemp(double tempInKelvins) {
        this.tempInKelvins = tempInKelvins;
        this.tempInCelsius = tempInKelvins - 273.15;
    }

    public double getTempInCelsius() {
        return tempInCelsius;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getWindDeg() {
        return windDeg;
    }

    public void setWindDeg(double windDeg) {
        this.windDeg = windDeg;
    }

    public double getClouds() {
        return clouds;
    }

    public void setClouds(double clouds) {
        this.clouds = clouds;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }
}
