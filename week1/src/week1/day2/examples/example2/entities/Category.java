package week1.day2.examples.example2.entities;

public enum Category {
    ELECTRONIC(0.1), CLOTHING(0.2), GROCERY(0.3);

    private double discountRate;

    public double getDiscountRate() {
        return discountRate;
    }

    Category(double discountRate) {
        this.discountRate = discountRate;
    }
}
