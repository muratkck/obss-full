package week1.day2.examples.example2.entities;

public class Product {

    private String productName;
    private Category category;
    private double price;

    public Product(String productName, Category category, double price) {
        this.productName = productName;
        this.category = category;
        this.price = price;
    }

    public double calculateDiscountedPrice(){
        return price * (1 - category.getDiscountRate());
    }

    public Category getCategoryName() {
        return category;
    }

    public void setCategoryName(Category category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
