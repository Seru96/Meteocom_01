package model;

import java.util.Date;

public class WeatherData {
    private double temperature;
    private double windSpeed;
    private double precipitation;
    private double humidity;
    private double latitude;
    private double longitude;
    private Date date;

    // Constructor
    public WeatherData() {
    }

    // Getters
    public double getTemperature() {
        return temperature;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public double getPrecipitation() {
        return precipitation;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public Date getDate() {
        return date;
    }

    // Setters
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setPrecipitation(double precipitation) {
        this.precipitation = precipitation;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

