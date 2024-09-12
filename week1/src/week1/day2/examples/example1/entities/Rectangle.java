package week1.day2.examples.example1.entities;

public class Rectangle extends Shape {
    private double longSideLength;
    private double shortSideLength;

    public Rectangle(double longSideLength, double shortSideLength) {
        this.longSideLength = longSideLength;
        this.shortSideLength = shortSideLength;
    }
    public double getLongSideLength() {
        return longSideLength;
    }
    public double getShortSideLength() {
        return shortSideLength;
    }

    @Override
    public double getArea() {
        return longSideLength * shortSideLength;
    }

}
