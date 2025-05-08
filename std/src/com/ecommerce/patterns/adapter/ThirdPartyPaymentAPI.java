package com.ecommerce.patterns.adapter;

// Simulate a third-party API with a different interface
public class ThirdPartyPaymentAPI {
    public boolean submitPayment(String merchantId, double totalAmount, String currency) {
        System.out.println("ThirdPartyPaymentAPI: Submitting payment...");
        System.out.println("Merchant: " + merchantId + ", Amount: " + totalAmount + " " + currency);
        // Simulate success
        System.out.println("ThirdPartyPaymentAPI: Payment accepted.");
        return true;
    }
}