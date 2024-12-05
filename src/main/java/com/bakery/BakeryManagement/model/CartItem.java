package com.bakery.BakeryManagement.model;

public class CartItem {
    private int id; // ID of the product
    private int quantity;  // Quantity of the product
    private double price;  // Price of the product

    // Add this if `Product` details are needed
    private Products product; // Optional: Reference to Product
    
    // Constructors
    public CartItem() {
    }

    public CartItem(int id, int quantity, double price) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int productId) {
        this.id = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }
}
