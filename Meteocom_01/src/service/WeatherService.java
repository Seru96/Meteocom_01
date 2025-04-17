package service;

import model.WeatherData;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class WeatherService {
    private static final String API_KEY = "Your_API_key";

    public void updateWeatherData(WeatherData weatherData, double latitude, double longitude) {
        try {
            String apiUrl = String.format("https://api.openweathermap.org/data/2.5/weather?lat=%.4f&lon=%.4f&units=metric&appid=%s",
                    latitude, longitude, API_KEY);

            HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            JSONObject json = new JSONObject(content.toString());

            weatherData.setTemperature(json.getJSONObject("main").getDouble("temp"));
            weatherData.setWindSpeed(json.getJSONObject("wind").getDouble("speed"));
            weatherData.setPrecipitation(json.has("rain") ? json.getJSONObject("rain").optDouble("1h", 0.0) : 0.0);
            weatherData.setHumidity(json.getJSONObject("main").getDouble("humidity"));
            weatherData.setDate(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateForecastData(WeatherData weatherData, double latitude, double longitude, int hours) {
        try {
            String apiUrl = String.format("https://api.openweathermap.org/data/2.5/forecast?lat=%.4f&lon=%.4f&units=metric&appid=%s",
                    latitude, longitude, API_KEY);

            HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            JSONObject json = new JSONObject(content.toString());
            JSONArray list = json.getJSONArray("list");
            JSONObject forecast = list.getJSONObject(hours / 3); // Cada intervalo es de 3 horas

            weatherData.setTemperature(forecast.getJSONObject("main").getDouble("temp"));
            weatherData.setWindSpeed(forecast.getJSONObject("wind").getDouble("speed"));
            weatherData.setPrecipitation(forecast.has("rain") ? forecast.optDouble("rain", 0.0) : 0.0);
            weatherData.setHumidity(forecast.getJSONObject("main").getDouble("humidity"));
            weatherData.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse(forecast.getString("dt_txt")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
