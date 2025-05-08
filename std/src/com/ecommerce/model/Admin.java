package com.ecommerce.model;

public class Admin extends User {
    public Admin(String userId, String username, String password) {
        super(userId, username, password, UserRole.ADMIN);
    }

    @Override
    public void displayDashboard() {
        System.out.println("\n--- Admin Dashboard ---");
        // Options will be handled by AdminController via AdminView
    }
}