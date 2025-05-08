package com.ecommerce.patterns.observer;

import com.ecommerce.model.ProductCatalog;
import com.ecommerce.model.ShoppingCart;
import com.ecommerce.model.CartItem;

public class OrderProcessor extends Subject {
    public void processOrder(ShoppingCart cart) {
        // Simulate order processing (e.g., inventory update, payment confirmation)
        System.out.println("OrderProcessor: Processing order for " + cart.getOwner().getUsername());

        // Decrease stock
        ProductCatalog catalog = ProductCatalog.getInstance();
        for (CartItem item : cart.getItems()) {
            catalog.decreaseStock(item.getProduct().getId(), item.getQuantity());
        }

        notifyObservers("PURCHASE_COMPLETED", cart);
        cart.clearCart(); // Clear cart after successful purchase
    }
}