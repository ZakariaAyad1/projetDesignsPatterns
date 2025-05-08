package com.ecommerce.view;

import com.ecommerce.model.Product;
import com.ecommerce.model.ProductComponent;
import com.ecommerce.model.ShoppingCart;

import java.util.List;

public class ConsoleView {
    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayProducts(List<Product> products) {
        if (products.isEmpty()) {
            System.out.println("No products available.");
            return;
        }
        System.out.println("\n--- Available Products ---");
        for (Product product : products) {
            System.out.println(product);
        }
        System.out.println("--------------------------");
    }

    public void displayProductDetails(ProductComponent product) {
        System.out.println("\n--- Product Details ---");
        System.out.println("ID: " + product.getId());
        System.out.println("Name: " + product.getName());
        System.out.printf("Price: $%.2f\n", product.getPrice());
        System.out.println("Description: " + product.getDescription());
        System.out.println("Stock: " + product.getStock());
        System.out.println("-----------------------");
    }

    public void displayCart(ShoppingCart cart) {
        System.out.println("\n--- Your Shopping Cart ---");
        if (cart.getItems().isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            cart.getItems().forEach(System.out::println);
            System.out.printf("Cart Total: $%.2f\n", cart.calculateTotal());
        }
        System.out.println("--------------------------");
    }
}