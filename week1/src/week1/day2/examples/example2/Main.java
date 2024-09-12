package week1.day2.examples.example2;

import week1.day2.examples.example2.entities.*;

public class Main {
    public static void main(String[] args) {

        Product electronicProduct1 = new Product("Electronic Product 1", Category.ELECTRONIC, 5000);
        Product clothingProduct1 = new Product("Clothing Product 1", Category.CLOTHING, 1000);
        Product groceryProduct1 = new Product("Grocery Product 1", Category.GROCERY, 500);

        ShoppingCart shoppingCart = new ShoppingCart();

        shoppingCart.addProduct(electronicProduct1);
        shoppingCart.addProduct(clothingProduct1);
        shoppingCart.addProduct(groceryProduct1);

        System.out.println("Total price without discount: " + shoppingCart.calculateTotalPriceWithoutDiscount());
        System.out.println("Total price with discount: " + shoppingCart.calculateTotalPrice());

    }
}
