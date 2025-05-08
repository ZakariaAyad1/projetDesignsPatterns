package com.ecommerce.patterns.observer;

import com.ecommerce.model.Customer;
import com.ecommerce.model.ShoppingCart;

public class AdminNotifier implements Observer {
    private String adminName;

    public AdminNotifier(String adminName) {
        this.adminName = adminName;
    }

    @Override
    public void update(String eventType, Object data) {
        System.out.print("\n--- ADMIN NOTIFICATION (" + adminName + ") --- : ");
        if ("USER_REGISTERED".equals(eventType) && data instanceof Customer) {
            Customer customer = (Customer) data;
            System.out.println("New customer registered: " + customer.getUsername() + " (ID: " + customer.getUserId() + ")");
        } else if ("PURCHASE_COMPLETED".equals(eventType) && data instanceof ShoppingCart) {
            ShoppingCart cart = (ShoppingCart) data;
            System.out.println("Purchase completed by " + cart.getOwner().getUsername() +
                    ". Total: $" + String.format("%.2f", cart.calculateTotal()));
        } else {
            System.out.println("General Event: " + eventType + " - Data: " + data.toString());
        }
        System.out.println("----------------------------------");
    }
}