public class SingletonDemo {
    public static void main(String[] args) {
        ConfigurationManager config1 = ConfigurationManager.getInstance();
        config1.setConfig("Production");

        ConfigurationManager config2 = ConfigurationManager.getInstance();
        System.out.println("Config value: " + config2.getConfig());
    }
}
