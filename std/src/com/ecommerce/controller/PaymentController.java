package com.ecommerce.controller;

import com.ecommerce.model.ShoppingCart;
import com.ecommerce.patterns.adapter.PaymentGatewayAdapter;
import com.ecommerce.patterns.adapter.ThirdPartyPaymentAPI;
import com.ecommerce.patterns.strategy.BankTransferPayment;
import com.ecommerce.patterns.strategy.CreditCardPayment;
import com.ecommerce.patterns.strategy.PayPalPayment;
import com.ecommerce.patterns.strategy.PaymentStrategy;
import com.ecommerce.util.InputUtil;

public class PaymentController {
    public PaymentStrategy selectPaymentMethod() {
        System.out.println("\n--- Select Payment Method ---");
        System.out.println("1. Credit Card");
        System.out.println("2. PayPal");
        System.out.println("3. Bank Transfer");
        System.out.println("4. Third-Party Gateway (Adapter Demo)");
        int choice = InputUtil.getIntInput("Choose payment method: ");

        switch (choice) {
            case 1:
                String ccNum = InputUtil.getStringInput("Enter Credit Card Number: ");
                String expiry = InputUtil.getStringInput("Enter Expiry Date (MM/YY): ");
                String cvv = InputUtil.getStringInput("Enter CVV: ");
                return new CreditCardPayment(ccNum, expiry, cvv);
            case 2:
                String email = InputUtil.getStringInput("Enter PayPal Email: ");
                return new PayPalPayment(email);
            case 3:
                String bankAccount = InputUtil.getStringInput("Enter Bank Account Number: ");
                String routingNumber = InputUtil.getStringInput("Enter Routing Number: ");
                return new BankTransferPayment(bankAccount, routingNumber);
            case 4:
                // Simulate getting an instance of the third-party API
                ThirdPartyPaymentAPI thirdPartyAPI = new ThirdPartyPaymentAPI();
                return new PaymentGatewayAdapter(thirdPartyAPI, "MERCHANT_XYZ123");
            default:
                System.out.println("Invalid payment method. Defaulting to Credit Card.");
                return new CreditCardPayment("0000000000000000", "12/25", "123"); // Fallback
        }
    }

    public boolean processPayment(ShoppingCart cart, PaymentStrategy paymentStrategy) {
        double totalAmount = cart.calculateTotal();
        if (totalAmount <= 0) {
            System.out.println("Cart is empty or total is zero. No payment needed.");
            return false; // Or true if empty cart means "success"
        }
        System.out.println("\nProcessing payment for total: $" + String.format("%.2f", totalAmount) +
                " using " + paymentStrategy.getName());
        return paymentStrategy.pay(totalAmount);
    }
}