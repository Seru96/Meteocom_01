package service;

import model.WeatherData;

public class AlertService {
    private WeatherData weatherData;

    public AlertService(WeatherData weatherData) {
        this.weatherData = weatherData;
    }

    public String checkForAlerts() {
        StringBuilder alerts = new StringBuilder();

        if (weatherData.getTemperature() > 20.0) { // Ejemplo de umbral alto fijo para la temperatura
            alerts.append("Alerta: ¡Límite de temperatura excedida!\n");
        }
        
        if (weatherData.getTemperature() < 10.0) { // Ejemplo de umbral bajo fijo para la temperatura
            alerts.append("Alerta: ¡Límite de temperatura excedida!\n");
        }

        if (weatherData.getWindSpeed() > 12.5) { // Ejemplo de umbral fijo para la velocidad del viento
            alerts.append("Alerta: ¡Límite de velocidad de viento excedida!\n");
        }

        if (weatherData.getPrecipitation() > 5.0) { // Ejemplo de umbral fijo para la precipitación
            alerts.append("Alerta: ¡Límite de precipitacion excedida!\n");
        }

        return alerts.toString();
    }
}
