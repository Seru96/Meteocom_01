package service;

import model.WeatherData;

import java.util.LinkedList;
import java.util.List;

public class HistoryService {
    private LinkedList<WeatherData> weatherHistory = new LinkedList<>();

    public void addWeatherData(WeatherData weatherData) {
        if (weatherHistory.size() >= 5) {
            weatherHistory.removeFirst();
        }
        weatherHistory.add(weatherData);
    }

    public List<WeatherData> getWeatherHistory() {
        return weatherHistory;
    }
}
