package week1.day2.labworks.lab3.entities;

public class Circle extends Shape {
    private int radius;

    public Circle(int radius, String color) {
        super(color);
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public double calculateArea() {
        return this.radius * this.radius * Math.PI;
    }
}
