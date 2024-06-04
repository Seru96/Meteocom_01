package main;

import model.WeatherData;
import service.AlertService;
import service.HistoryService;
import service.WeatherService;
import view.WeatherGUI;
import view.WeatherView;

import javax.swing.*;

public class WeatherMain {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        WeatherView weatherView = new WeatherView(weatherData);
        HistoryService historyService = new HistoryService();

        // Seleccionar la ubicación
        weatherView.selectLocation();

        // Seleccionar el periodo de previsión
        int forecastPeriod = weatherView.selectForecastPeriod();

        // Actualizar los datos meteorológicos
        WeatherService weatherService = new WeatherService();
        if (forecastPeriod == 1) {
            weatherService.updateWeatherData(weatherData, weatherData.getLatitude(), weatherData.getLongitude());
        } else {
            weatherService.updateForecastData(weatherData, weatherData.getLatitude(), weatherData.getLongitude(), (forecastPeriod - 1) * 3);
        }

        // Agregar datos climáticos al historial
        historyService.addWeatherData(weatherData);

        // Crear AlertService con la misma instancia de WeatherData
        AlertService alertService = new AlertService(weatherData);

        // Verificar las alertas
        String alerts = alertService.checkForAlerts();
        System.out.println(alerts);

        // Mostrar la GUI
        SwingUtilities.invokeLater(() -> new WeatherGUI(weatherData, historyService));
    }
}
