package com.bakery.BakeryManagement.model;

import java.util.List;

public class OrderRequest {
    private int userId; // User ID placing the order
    private List<CartItem> cartItems; // List of cart items
    private double discount; // Discount applied to the order
    private Promotion promotion; // Promotion ID (nullable)

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }
}
