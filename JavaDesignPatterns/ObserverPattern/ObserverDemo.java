public class ObserverDemo {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        CurrentConditionsDisplay display = new CurrentConditionsDisplay();

        weatherData.registerObserver(display);

        weatherData.setMeasurements(30, 65);
        weatherData.setMeasurements(28, 70);
    }
}
