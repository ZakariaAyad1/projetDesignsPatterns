package com.ecommerce.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShoppingCart {
    private final Customer owner;
    private List<CartItem> items;

    public ShoppingCart(Customer owner) {
        this.owner = owner;
        this.items = new ArrayList<>();
    }

    public void addItem(ProductComponent product, int quantity) {
        if (product.getStock() < quantity) {
            System.out.println("Error: Not enough stock for " + product.getName());
            return;
        }
        Optional<CartItem> existingItem = items.stream()
                .filter(item -> item.getProduct().getId().equals(product.getId()))
                .findFirst();

        if (existingItem.isPresent()) {
            existingItem.get().setQuantity(existingItem.get().getQuantity() + quantity);
        } else {
            items.add(new CartItem(product, quantity));
        }
        // product.setStock(product.getStock() - quantity); // Stock deduction should happen at checkout
        System.out.println(quantity + " of '" + product.getName() + "' added to cart.");
    }

    public void removeItem(String productId) {
        items.removeIf(item -> item.getProduct().getId().equals(productId));
        System.out.println("Product ID " + productId + " removed from cart if it existed.");
    }

    public void updateQuantity(String productId, int newQuantity) {
        Optional<CartItem> itemToUpdate = items.stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();
        if (itemToUpdate.isPresent()) {
            if (newQuantity <= 0) {
                removeItem(productId);
            } else if (itemToUpdate.get().getProduct().getStock() < newQuantity) {
                System.out.println("Error: Not enough stock to update quantity for " + itemToUpdate.get().getProduct().getName());
            }
            else {
                itemToUpdate.get().setQuantity(newQuantity);
                System.out.println("Quantity for " + itemToUpdate.get().getProduct().getName() + " updated to " + newQuantity);
            }
        } else {
            System.out.println("Product ID " + productId + " not found in cart.");
        }
    }


    public List<CartItem> getItems() {
        return new ArrayList<>(items); // Return a copy
    }

    public double calculateTotal() {
        return items.stream().mapToDouble(CartItem::getItemTotal).sum();
    }

    public void clearCart() {
        items.clear();
    }

    public Customer getOwner() {
        return owner;
    }
}