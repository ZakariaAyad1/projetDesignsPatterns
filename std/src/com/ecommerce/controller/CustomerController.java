package com.ecommerce.controller;

import com.ecommerce.model.*;
import com.ecommerce.patterns.decorator.ExtendedWarrantyDecorator;
import com.ecommerce.patterns.decorator.GiftWrapDecorator;
import com.ecommerce.patterns.observer.OrderProcessor;
import com.ecommerce.patterns.strategy.PaymentStrategy;
import com.ecommerce.util.InputUtil;
import com.ecommerce.view.ConsoleView;

import java.util.Optional;

public class CustomerController {
    private Customer customer;
    private ProductCatalog catalog = ProductCatalog.getInstance();
    private ConsoleView view = new ConsoleView();
    private PaymentController paymentController = new PaymentController();
    private OrderProcessor orderProcessor; // Observer Subject

    public CustomerController(Customer customer, OrderProcessor orderProcessor) {
        this.customer = customer;
        this.orderProcessor = orderProcessor;
    }

    public void showCustomerMenu() {
        boolean running = true;
        while (running) {
            customer.displayDashboard();
            System.out.println("1. Browse Products");
            System.out.println("2. View Product Details & Add to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Update Cart Item Quantity");
            System.out.println("5. Remove Item from Cart");
            System.out.println("6. Checkout");
            System.out.println("7. Logout");
            int choice = InputUtil.getIntInput("Enter choice: ");

            switch (choice) {
                case 1:
                    view.displayProducts(catalog.getAllProducts());
                    break;
                case 2:
                    handleViewAndAddToCart();
                    break;
                case 3:
                    view.displayCart(customer.getCart());
                    break;
                case 4:
                    handleUpdateCartQuantity();
                    break;
                case 5:
                    handleRemoveFromCart();
                    break;
                case 6:
                    handleCheckout();
                    break;
                case 7:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void handleViewAndAddToCart() {
        String productId = InputUtil.getStringInput("Enter Product ID to view/add: ");
        Optional<Product> productOpt = catalog.getProductById(productId);

        if (productOpt.isPresent()) {
            ProductComponent selectedProduct = productOpt.get(); // Start with the base product
            view.displayProductDetails(selectedProduct);

            if (selectedProduct.getStock() <= 0) {
                System.out.println(selectedProduct.getName() + " is out of stock.");
                return;
            }

            // Offer Decorators
            String giftWrapChoice = InputUtil.getStringInput("Add gift wrapping? ($5.00) (yes/no): ");
            if ("yes".equalsIgnoreCase(giftWrapChoice)) {
                selectedProduct = new GiftWrapDecorator(selectedProduct);
            }

            String warrantyChoice = InputUtil.getStringInput("Add extended warranty? (10% of price) (yes/no): ");
            if ("yes".equalsIgnoreCase(warrantyChoice)) {
                selectedProduct = new ExtendedWarrantyDecorator(selectedProduct);
            }

            if (selectedProduct != productOpt.get()) { // If any decorator was added
                System.out.println("--- Updated Product with Add-ons ---");
                System.out.println("Name: " + selectedProduct.getName());
                System.out.printf("New Price: $%.2f\n", selectedProduct.getPrice());
                System.out.println("----------------------------------");
            }


            int quantity = InputUtil.getIntInput("Enter quantity to add to cart (0 to skip): ");
            if (quantity > 0) {
                if (quantity > productOpt.get().getStock()) { // Check original product stock
                    System.out.println("Not enough stock. Available: " + productOpt.get().getStock());
                } else {
                    customer.getCart().addItem(selectedProduct, quantity);
                }
            }
        } else {
            System.out.println("Product not found.");
        }
    }

    private void handleUpdateCartQuantity() {
        view.displayCart(customer.getCart());
        if (customer.getCart().getItems().isEmpty()) return;

        String productId = InputUtil.getStringInput("Enter Product ID to update quantity: ");
        int newQuantity = InputUtil.getIntInput("Enter new quantity (0 to remove): ");
        customer.getCart().updateQuantity(productId, newQuantity);
    }


    private void handleRemoveFromCart() {
        view.displayCart(customer.getCart());
        if (customer.getCart().getItems().isEmpty()) return;
        String productId = InputUtil.getStringInput("Enter Product ID to remove from cart: ");
        customer.getCart().removeItem(productId);
    }


    private void handleCheckout() {
        ShoppingCart cart = customer.getCart();
        if (cart.getItems().isEmpty()) {
            System.out.println("Your cart is empty. Nothing to checkout.");
            return;
        }
        view.displayCart(cart);
        System.out.println("Proceeding to checkout...");

        PaymentStrategy paymentMethod = paymentController.selectPaymentMethod();
        boolean paymentSuccessful = paymentController.processPayment(cart, paymentMethod);

        if (paymentSuccessful) {
            System.out.println("Checkout successful! Thank you for your purchase.");
            orderProcessor.processOrder(cart); // This will notify observers & clear cart
        } else {
            System.out.println("Payment failed. Please try again or choose a different method.");
        }
    }
}