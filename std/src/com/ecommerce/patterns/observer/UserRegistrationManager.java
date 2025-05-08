package com.ecommerce.patterns.observer;

import com.ecommerce.model.Customer;

public class UserRegistrationManager extends Subject {
    public void registerUser(Customer customer) {
        // Actual user registration logic would be in UserStore, called by AuthController
        System.out.println("UserRegistrationManager: " + customer.getUsername() + " processed.");
        notifyObservers("USER_REGISTERED", customer);
    }
}