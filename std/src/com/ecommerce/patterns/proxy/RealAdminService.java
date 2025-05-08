package com.ecommerce.patterns.proxy;

import com.ecommerce.model.Product;
import com.ecommerce.model.ProductCatalog;

public class RealAdminService implements AdminService {
    private ProductCatalog catalog = ProductCatalog.getInstance();

    @Override
    public void addProduct(Product product) {
        catalog.addProduct(product);
        System.out.println("RealAdminService: Product '" + product.getName() + "' added to catalog.");
    }

    @Override
    public boolean updateProduct(String id, String name, double price, String description, String category, int stock) {
        boolean updated = catalog.updateProduct(id, name, price, description, category, stock);
        if (updated) {
            System.out.println("RealAdminService: Product ID '" + id + "' updated.");
        } else {
            System.out.println("RealAdminService: Product ID '" + id + "' not found for update.");
        }
        return updated;
    }

    @Override
    public boolean deleteProduct(String id) {
        boolean deleted = catalog.deleteProduct(id);
        if (deleted) {
            System.out.println("RealAdminService: Product ID '" + id + "' deleted.");
        } else {
            System.out.println("RealAdminService: Product ID '" + id + "' not found for deletion.");
        }
        return deleted;
    }
}