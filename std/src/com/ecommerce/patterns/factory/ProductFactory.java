package com.ecommerce.patterns.factory;

import com.ecommerce.model.Product;
import com.ecommerce.model.ProductCatalog;

// This factory is simpler as Product itself uses a Builder
// It mainly ensures Product ID generation and potentially default values
public class ProductFactory {
    public Product createProduct(String name, double price, String description, String category, int stock) {
        String id = ProductCatalog.getInstance().generateId();
        return new Product.ProductBuilder(id, name, price)
                .description(description)
                .category(category)
                .stock(stock)
                .build();
    }
}