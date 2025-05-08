package com.ecommerce.patterns.command;

import com.ecommerce.patterns.proxy.AdminService;

public class DeleteProductCommand implements AdminCommand {
    private AdminService adminService;
    private String productId;

    public DeleteProductCommand(AdminService adminService, String productId) {
        this.adminService = adminService;
        this.productId = productId;
    }

    @Override
    public void execute() {
        if (adminService.deleteProduct(productId)) {
            System.out.println("Product ID '" + productId + "' deleted via command.");
        } else {
            System.out.println("Failed to delete product ID '" + productId + "' via command (not found).");
        }
    }
}