package com.ecommerce.patterns.strategy;

public interface PaymentStrategy {
    boolean pay(double amount);
    String getName();
}