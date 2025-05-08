package com.ecommerce.patterns.command;

import com.ecommerce.patterns.proxy.AdminService;

public class UpdateProductCommand implements AdminCommand {
    private AdminService adminService;
    private String id;
    private String name;
    private double price;
    private String description;
    private String category;
    private int stock;

    public UpdateProductCommand(AdminService adminService, String id, String name, double price, String description, String category, int stock) {
        this.adminService = adminService;
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.stock = stock;
    }

    @Override
    public void execute() {
        if (adminService.updateProduct(id, name, price, description, category, stock)) {
            System.out.println("Product ID '" + id + "' updated via command.");
        } else {
            System.out.println("Failed to update product ID '" + id + "' via command (not found or no change).");
        }
    }
}