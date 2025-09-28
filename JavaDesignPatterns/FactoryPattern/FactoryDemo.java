public class FactoryDemo {
    public static void main(String[] args) {
        ShapeFactory factory = new ShapeFactory();

        Shape circle = factory.createShape("circle");
        circle.draw();

        Shape square = factory.createShape("square");
        square.draw();
    }
}
