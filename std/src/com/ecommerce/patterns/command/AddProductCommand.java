package com.ecommerce.patterns.command;

import com.ecommerce.model.Product;
import com.ecommerce.patterns.proxy.AdminService; // Will use the proxy

public class AddProductCommand implements AdminCommand {
    private AdminService adminService;
    private Product product;

    public AddProductCommand(AdminService adminService, Product product) {
        this.adminService = adminService;
        this.product = product;
    }

    @Override
    public void execute() {
        adminService.addProduct(product);
        System.out.println("Product '" + product.getName() + "' added via command.");
    }
}