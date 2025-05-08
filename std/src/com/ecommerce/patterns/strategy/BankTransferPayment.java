package com.ecommerce.patterns.strategy;

public class BankTransferPayment implements PaymentStrategy {
    private String bankAccount;
    private String routingNumber;

    public BankTransferPayment(String bankAccount, String routingNumber) {
        this.bankAccount = bankAccount;
        this.routingNumber = routingNumber;
    }
    @Override
    public boolean pay(double amount) {
        System.out.println("Processing Bank Transfer of $" + String.format("%.2f", amount));
        System.out.println("Transfer from account ending " + bankAccount.substring(Math.max(0, bankAccount.length() - 4)));
        System.out.println("Payment successful via Bank Transfer.");
        return true;
    }
    @Override
    public String getName() {
        return "Bank Transfer";
    }
}