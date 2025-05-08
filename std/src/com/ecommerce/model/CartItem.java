package com.ecommerce.model;

public class CartItem {
    private ProductComponent product; // Can be a decorated product
    private int quantity;

    public CartItem(ProductComponent product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public ProductComponent getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getItemTotal() { return product.getPrice() * quantity; }

    @Override
    public String toString() {
        return String.format("%s (x%d) - $%.2f (Item Total: $%.2f)",
                product.getName(), quantity, product.getPrice(), getItemTotal());
    }
}