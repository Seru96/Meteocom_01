package view;

import model.WeatherData;
import service.HistoryService;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class WeatherGUI extends JFrame {
    private WeatherData weatherData;
    private HistoryService historyService;

    public WeatherGUI(WeatherData weatherData, HistoryService historyService) {
        this.weatherData = weatherData;
        this.historyService = historyService;
        initialize();
    }

    private void initialize() {
        setTitle("Meteocom");
        setSize(460, 294); // Tamaño ajustado
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Panel para la palabra "Meteocom"
        JPanel meteocomPanel = new JPanel();
        meteocomPanel.setBackground(Color.decode("#bde0ff")); // Color de fondo
        JLabel meteocomLabel = new JLabel("Meteocom");
        meteocomLabel.setFont(new Font("Arial", Font.BOLD, 36)); // Tamaño y negrita
        meteocomPanel.add(meteocomLabel);

        // Panel para los datos del clima
        JPanel weatherPanel = new JPanel();
        weatherPanel.setLayout(new BoxLayout(weatherPanel, BoxLayout.Y_AXIS));
        Border border = BorderFactory.createLineBorder(Color.decode("#ffe280")); // Borde de color #ffe280
        Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border compoundBorder = BorderFactory.createCompoundBorder(border, emptyBorder);
        weatherPanel.setBorder(compoundBorder); // Borde con color
        weatherPanel.setBackground(Color.decode("#ffffff")); // Color de fondo

        // Añadir datos del clima
        addWeatherInfo("Longitud: " + weatherData.getLongitude(), weatherPanel);
        addWeatherInfo("Latitud: " + weatherData.getLatitude(), weatherPanel);
        addWeatherInfo("Fecha: " + weatherData.getDate().toString(), weatherPanel);
        addWeatherInfo("Precipitación: " + weatherData.getPrecipitation() + " mm", weatherPanel);
        addWeatherInfo("Velocidad del viento: " + weatherData.getWindSpeed() + " m/s", weatherPanel);
        addWeatherInfo("Humedad: " + weatherData.getHumidity() + " %", weatherPanel);
        addWeatherInfo("Temperatura: " + weatherData.getTemperature() + " °C", weatherPanel);

     // Centrar los datos del clima en su panel
        weatherPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Panel para las alertas
        JPanel alertPanel = new JPanel();
        alertPanel.setBackground(Color.decode("#ffbe00")); // Color de fondo
        JLabel alertLabel = new JLabel("¡Alerta! Condiciones climáticas adversas.");
        alertPanel.add(alertLabel);

        // Botón para ver el historial
        JButton historyButton = new JButton("Ver Historial");
        historyButton.addActionListener(e -> showHistory());

        // Panel para el botón del historial
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(historyButton);

        // Agregar paneles al panel principal
        mainPanel.add(meteocomPanel, BorderLayout.NORTH); // Arriba y a la izquierda
        mainPanel.add(weatherPanel, BorderLayout.CENTER);
        mainPanel.add(alertPanel, BorderLayout.SOUTH);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH); // Agregar el botón del historial abajo del panel de alerta

        // Centrar la ventana en la pantalla
        setLocationRelativeTo(null);

        // Agregar el panel principal al JFrame
        add(mainPanel);
        setVisible(true);
    }

    private void addWeatherInfo(String info, JPanel panel) {
        JLabel infoLabel = new JLabel(info);
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(infoLabel);
    }

    private void showHistory() {
        StringBuilder historyMessage = new StringBuilder();
        historyMessage.append("Historial del clima (últimas 5 entradas):\n");
        for (WeatherData wd : historyService.getWeatherHistory()) {
            historyMessage.append("Fecha: ").append(wd.getDate()).append(", ")
                    .append("Temperatura: ").append(wd.getTemperature()).append(" °C, ")
                    .append("Humedad: ").append(wd.getHumidity()).append(" %, ")
                    .append("Velocidad del viento: ").append(wd.getWindSpeed()).append(" m/s, ")
                    .append("Precipitación: ").append(wd.getPrecipitation()).append(" mm, ")
                    .append("Latitud: ").append(wd.getLatitude()).append(", ")
                    .append("Longitud: ").append(wd.getLongitude()).append("\n");
        }
        JOptionPane.showMessageDialog(this, historyMessage.toString());
    }
}
