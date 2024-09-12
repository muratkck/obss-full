package week1.day2.labworks.lab3;

import week1.day2.labworks.lab3.entities.Circle;
import week1.day2.labworks.lab3.entities.Pen;
import week1.day2.labworks.lab3.entities.Rectangle;
import week1.day2.labworks.lab3.entities.Shape;

public class Main {

    public static void main(String[] args) {
        Shape rectangle = new Rectangle(10, 12, "Black");
        Shape circle = new Circle(3, "Red");
        Pen pen = new Pen();

        System.out.println("Rectangle - First color: " + rectangle.getColor());
        System.out.println("Circle - First color: " + circle.getColor());


        pen.draw(rectangle);
        pen.draw(circle);

        pen.changeColor("Blue", circle);
        pen.changeColor("Yellow", rectangle);

        System.out.println("Rectangle - First color: " + rectangle.getColor());
        System.out.println("Circle - First color: " + circle.getColor());
    }
}
