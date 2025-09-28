public class CurrentConditionsDisplay implements Observer {
    @Override
    public void update(float temperature, float humidity) {
        System.out.println("Current conditions: " + temperature + "°C and " + humidity + "% humidity");
    }
}
