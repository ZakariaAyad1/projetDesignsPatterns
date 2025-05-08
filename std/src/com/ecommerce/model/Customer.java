package com.ecommerce.model;

public class Customer extends User {
    private ShoppingCart cart;

    public Customer(String userId, String username, String password) {
        super(userId, username, password, UserRole.CUSTOMER);
        this.cart = new ShoppingCart(this);
    }

    public ShoppingCart getCart() {
        return cart;
    }

    @Override
    public void displayDashboard() {
        System.out.println("\n--- Customer Dashboard (" + getUsername() + ") ---");
        // Options will be handled by CustomerController via CustomerView
    }
}