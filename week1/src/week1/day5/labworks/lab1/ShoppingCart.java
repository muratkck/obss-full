package com.day5.lab1;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart<T extends Product> {

    private List<T> shoppingCartItems;
    private int capacity;
    private int itemCount = 0;

    public ShoppingCart(int capacity) {
        this.shoppingCartItems = new ArrayList<T>();
    }

    public void addItem(T item) {
        this.shoppingCartItems.add(item);
    }

    public double calculateTotalPrice(){
        double totalPrice = 0;
        for(T item : shoppingCartItems){
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }



}
