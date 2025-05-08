package com.ecommerce.patterns.strategy;

public class PayPalPayment implements PaymentStrategy {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public boolean pay(double amount) {
        System.out.println("Processing PayPal payment of $" + String.format("%.2f", amount) + " for account " + email);
        // Simulate payment processing
        System.out.println("Payment successful via PayPal.");
        return true;
    }
    @Override
    public String getName() {
        return "PayPal";
    }
}