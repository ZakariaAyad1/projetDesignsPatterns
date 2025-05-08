package com.ecommerce.patterns.adapter;

import com.ecommerce.patterns.strategy.PaymentStrategy;

public class PaymentGatewayAdapter implements PaymentStrategy {
    private ThirdPartyPaymentAPI thirdPartyAPI;
    private String merchantId;

    public PaymentGatewayAdapter(ThirdPartyPaymentAPI thirdPartyAPI, String merchantId) {
        this.thirdPartyAPI = thirdPartyAPI;
        this.merchantId = merchantId;
    }

    @Override
    public boolean pay(double amount) {
        System.out.println("Using Adapter for Third-Party Payment Gateway.");
        // Convert our system's call to the third-party API's expected call
        return thirdPartyAPI.submitPayment(merchantId, amount, "USD");
    }

    @Override
    public String getName() {
        return "Third-Party Gateway";
    }
}