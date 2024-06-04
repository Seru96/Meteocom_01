package view;

import model.WeatherData;

import java.util.Scanner;

public class WeatherView {
    private WeatherData weatherData;

    public WeatherView(WeatherData weatherData) {
        this.weatherData = weatherData;
    }

    public void selectLocation() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Seleccione una ubicación:");
        System.out.println("1. Madrid - Torre Espacio");
        System.out.println("2. Madrid - Torre de Cristal");
        System.out.println("3. Madrid - Torre Cepsa");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                weatherData.setLatitude(40.4556);
                weatherData.setLongitude(-3.6924);
                break;
            case 2:
                weatherData.setLatitude(40.4624);
                weatherData.setLongitude(-3.6871);
                break;
            case 3:
                weatherData.setLatitude(40.4748);
                weatherData.setLongitude(-3.6878);
                break;
            default:
                System.out.println("Elección inválida, por defecto se seleccionará Madrid - Torre Espacio");
                weatherData.setLatitude(40.4556);
                weatherData.setLongitude(-3.6924);
                break;
        }
    }

    public int selectForecastPeriod() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("¿Desea ver el clima actual o una previsión?");
        System.out.println("1. Clima actual");
        System.out.println("2. Previsión de 3 horas");
        System.out.println("3. Previsión de 6 horas");
        System.out.println("4. Previsión de 9 horas");

        int choice = scanner.nextInt();
        if (choice < 1 || choice > 4) {
            System.out.println("Elección inválida, por defecto se seleccionará el clima actual.");
            choice = 1;
        }
        return choice;
    }

    public boolean askToViewHistory() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("¿Le gustaría ver el historial del clima? (sí/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("sí") || response.equals("si"); // Aqui el codigo cogera tambien como válido sí sin sin tilde.
    }
}
