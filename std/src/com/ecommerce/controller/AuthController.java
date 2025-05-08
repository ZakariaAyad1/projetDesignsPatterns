package com.ecommerce.controller;

import com.ecommerce.model.Customer;
import com.ecommerce.model.User;
import com.ecommerce.model.UserRole;
import com.ecommerce.model.UserStore;
import com.ecommerce.patterns.factory.UserFactory;
import com.ecommerce.patterns.observer.Observer; // For AdminNotifier
import com.ecommerce.patterns.observer.UserRegistrationManager;
import com.ecommerce.util.InputUtil;

import java.util.Optional;

public class AuthController {
    private UserStore userStore = UserStore.getInstance();
    private UserFactory userFactory = new UserFactory();
    private UserRegistrationManager userRegistrationManager; // Observer Subject

    public AuthController(UserRegistrationManager userRegistrationManager) {
        this.userRegistrationManager = userRegistrationManager;
    }

    public User login() {
        System.out.println("\n--- Login ---");
        String username = InputUtil.getStringInput("Enter username: ");
        String password = InputUtil.getStringInput("Enter password: ");

        Optional<User> userOpt = userStore.getUserByUsername(username);
        if (userOpt.isPresent() && userOpt.get().getPassword().equals(password)) {
            System.out.println("Login successful. Welcome " + username + "!");
            return userOpt.get();
        } else {
            System.out.println("Invalid username or password.");
            return null;
        }
    }

    public User register() {
        System.out.println("\n--- Register ---");
        String username;
        while (true) {
            username = InputUtil.getStringInput("Enter new username: ");
            if (userStore.usernameExists(username)) {
                System.out.println("Username already exists. Please choose another.");
            } else {
                break;
            }
        }
        String password = InputUtil.getStringInput("Enter new password: ");

        // For simplicity, all new registrations are Customers
        Customer newCustomer = (Customer) userFactory.createUser(UserRole.CUSTOMER, username, password);
        userStore.addUser(newCustomer);
        System.out.println("Registration successful for " + username + "!");

        // Notify observers (e.g., AdminNotifier)
        userRegistrationManager.registerUser(newCustomer);

        return newCustomer;
    }
}