package com.ecommerce.model;

public class Product implements ProductComponent {
    private final String id;
    private final String name;
    private double price;
    private String description;
    private String category;
    private int stock;

    private Product(ProductBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.price = builder.price;
        this.description = builder.description;
        this.category = builder.category;
        this.stock = builder.stock;
    }

    @Override public String getId() { return id; }
    @Override public String getName() { return name; }
    @Override public double getPrice() { return price; }
    @Override public String getDescription() { return description != null ? description : "No description available."; }
    public String getCategory() { return category != null ? category : "Uncategorized"; }
    @Override public int getStock() { return stock; }

    public void setPrice(double price) { this.price = price; }
    public void setDescription(String description) { this.description = description; }
    public void setCategory(String category) { this.category = category; }
    @Override public void setStock(int stock) { this.stock = stock; }

    @Override
    public String toString() {
        return String.format("ID: %s, Name: %s, Price: $%.2f, Stock: %d, Desc: %s",
                id, name, price, stock, getDescription());
    }

    // **BUILDER PATTERN**
    public static class ProductBuilder {
        private final String id; // Required
        private final String name; // Required
        private double price;     // Required
        private String description;
        private String category;
        private int stock = 0;

        public ProductBuilder(String id, String name, double price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }

        public ProductBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ProductBuilder category(String category) {
            this.category = category;
            return this;
        }

        public ProductBuilder stock(int stock) {
            this.stock = stock;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}