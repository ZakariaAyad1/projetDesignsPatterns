package com.ecommerce.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProductCatalog {
    private static ProductCatalog instance;
    private Map<String, Product> products; // Using Product directly, not ProductComponent for catalog management
    private int nextProductId = 1;

    private ProductCatalog() {
        products = new HashMap<>();
        // Initialize with some sample products
        addProduct(new Product.ProductBuilder(generateId(), "Laptop Pro", 1200.00)
                .description("High-end laptop for professionals")
                .category("Electronics")
                .stock(10)
                .build());
        addProduct(new Product.ProductBuilder(generateId(), "Wireless Mouse", 25.00)
                .description("Ergonomic wireless mouse")
                .category("Accessories")
                .stock(50)
                .build());
        addProduct(new Product.ProductBuilder(generateId(), "Java Programming Book", 45.99)
                .description("Comprehensive guide to Java")
                .category("Books")
                .stock(30)
                .build());
    }

    public static synchronized ProductCatalog getInstance() {
        if (instance == null) {
            instance = new ProductCatalog();
        }
        return instance;
    }

    public String generateId() {
        return "P" + String.format("%03d", nextProductId++);
    }

    public void addProduct(Product product) {
        products.put(product.getId(), product);
    }

    public Optional<Product> getProductById(String id) {
        return Optional.ofNullable(products.get(id));
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    public boolean updateProduct(String id, String name, double price, String description, String category, int stock) {
        Optional<Product> productOpt = getProductById(id);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            if (name != null && !name.isEmpty()) product = new Product.ProductBuilder(id, name, product.getPrice()).description(product.getDescription()).category(product.getCategory()).stock(product.getStock()).build(); // Rebuild for immutability or use setters
            if (price > 0) product.setPrice(price);
            if (description != null) product.setDescription(description);
            if (category != null) product.setCategory(category);
            if (stock >= 0) product.setStock(stock);
            products.put(id, product); // if setters used, this line not strictly needed if object is mutable
            return true;
        }
        return false;
    }

    public boolean deleteProduct(String id) {
        return products.remove(id) != null;
    }

    public void decreaseStock(String productId, int quantity) {
        getProductById(productId).ifPresent(p -> p.setStock(p.getStock() - quantity));
    }
}