package week1.day2.labworks.lab3.entities;

public class Pen {

    public void draw(Shape shape){
        System.out.println("The area of the shape: " + shape.calculateArea());
    }

    public void changeColor(String color, Shape shape){
        shape.setColor(color);
        String shapeName = shape.getClass().getSimpleName();
        System.out.println(shapeName + "'s new color: " + shape.getColor());
    }

}
