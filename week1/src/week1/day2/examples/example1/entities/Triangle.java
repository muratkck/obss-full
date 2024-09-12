package week1.day2.examples.example1.entities;

public class Triangle extends Shape{
    private double floorArea;
    private double height;

    public Triangle(double floorArea, double height) {
        this.floorArea = floorArea;
        this.height = height;
    }

    public double getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(double floorArea) {
        this.floorArea = floorArea;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public double getArea() {
        return floorArea * height / 2;
    }
}
