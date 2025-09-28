public class ProxyDemo {
    public static void main(String[] args) {
        Image image = new ProxyImage("photo1.jpg");

        // image loaded only once
        image.display();
        System.out.println("----");
        image.display();
    }
}
