package com.ecommerce.patterns.strategy;

public class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    private String expiryDate;
    private String cvv;

    public CreditCardPayment(String cardNumber, String expiryDate, String cvv) {
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    @Override
    public boolean pay(double amount) {
        System.out.println("Processing credit card payment of $" + String.format("%.2f", amount));
        // Simulate payment processing
        System.out.println("Card Number: " + cardNumber.replaceAll("\\d(?=\\d{4})", "*")); // Mask card number
        System.out.println("Payment successful via Credit Card.");
        return true;
    }

    @Override
    public String getName() {
        return "Credit Card";
    }
}