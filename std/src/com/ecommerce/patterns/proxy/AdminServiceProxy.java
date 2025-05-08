package com.ecommerce.patterns.proxy;

import com.ecommerce.model.Product;
import com.ecommerce.model.User;
import com.ecommerce.model.UserRole;

public class AdminServiceProxy implements AdminService {
    private RealAdminService realAdminService;
    private User currentUser; // The user attempting the action

    public AdminServiceProxy(User currentUser) {
        this.currentUser = currentUser;
        this.realAdminService = new RealAdminService(); // Can be injected
    }

    private boolean isAdmin() {
        if (currentUser != null && currentUser.getRole() == UserRole.ADMIN) {
            return true;
        }
        System.out.println("Access Denied: Admin privileges required for this action.");
        return false;
    }

    @Override
    public void addProduct(Product product) {
        if (isAdmin()) {
            System.out.println("AdminServiceProxy: Authenticated. Forwarding addProduct request.");
            realAdminService.addProduct(product);
        }
    }

    @Override
    public boolean updateProduct(String id, String name, double price, String description, String category, int stock) {
        if (isAdmin()) {
            System.out.println("AdminServiceProxy: Authenticated. Forwarding updateProduct request.");
            return realAdminService.updateProduct(id, name, price, description, category, stock);
        }
        return false;
    }

    @Override
    public boolean deleteProduct(String id) {
        if (isAdmin()) {
            System.out.println("AdminServiceProxy: Authenticated. Forwarding deleteProduct request.");
            return realAdminService.deleteProduct(id);
        }
        return false;
    }
}