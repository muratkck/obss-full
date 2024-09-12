package week1.day2.examples.example2.entities;

public class ShoppingCart {

    private Product[] products;
    private int productsCount = 0;
    private int capacity = 10;

    public ShoppingCart() {
        products = new Product[capacity];
    }

    public void addProduct(Product product) {
        if (productsCount >= capacity) {
            System.out.println("Shopping cart is full");
            return;
        }
        products[productsCount++] = product;
        System.out.println("Added product: " + product.getProductName());
    }

    public double calculateTotalPriceWithoutDiscount() {
        double totalPriceWithoutDiscount = 0;

        for (Product product : this.products) {
            if (product != null) {
                totalPriceWithoutDiscount += product.getPrice();
            }
            else{
                break;
            }
        }
        return totalPriceWithoutDiscount;
    }

    public double calculateTotalPrice() {
        double totalPriceWithDiscount = 0;

        for (Product product : this.products) {
            if (product != null) {
                totalPriceWithDiscount += product.calculateDiscountedPrice();
            }
            else{
                break;
            }
        }
        return totalPriceWithDiscount;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
