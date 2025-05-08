package com.ecommerce.patterns.proxy;

import com.ecommerce.model.Product;

public interface AdminService {
    void addProduct(Product product);
    boolean updateProduct(String id, String name, double price, String description, String category, int stock);
    boolean deleteProduct(String id);
}