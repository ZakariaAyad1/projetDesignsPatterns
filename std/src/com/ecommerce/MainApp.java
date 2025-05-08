package com.ecommerce;

import com.ecommerce.controller.AdminController;
import com.ecommerce.controller.AuthController;
import com.ecommerce.controller.CustomerController;
import com.ecommerce.model.Admin;
import com.ecommerce.model.Customer;
import com.ecommerce.model.User;
import com.ecommerce.model.UserRole;
import com.ecommerce.patterns.observer.AdminNotifier;
import com.ecommerce.patterns.observer.OrderProcessor;
import com.ecommerce.patterns.observer.UserRegistrationManager;
import com.ecommerce.util.InputUtil;

public class MainApp {
    public static void main(String[] args) {
        // Setup Observers
        AdminNotifier adminNotifier = new AdminNotifier("SystemAdmin"); // An observer
        UserRegistrationManager userRegManager = new UserRegistrationManager(); // Subject
        OrderProcessor orderProcessor = new OrderProcessor(); // Subject

        userRegManager.registerObserver(adminNotifier);
        orderProcessor.registerObserver(adminNotifier);

        // Setup Controllers
        AuthController authController = new AuthController(userRegManager);
        // AdminController and CustomerController will be instantiated after login

        System.out.println("Welcome to the Console E-Commerce Application!");

        User currentUser = null;
        boolean running = true;

        while (running) {
            if (currentUser == null) {
                System.out.println("\n--- Main Menu ---");
                System.out.println("1. Login");
                System.out.println("2. Register (as Customer)");
                System.out.println("3. Exit");
                int choice = InputUtil.getIntInput("Enter choice: ");

                switch (choice) {
                    case 1:
                        currentUser = authController.login();
                        break;
                    case 2:
                        currentUser = authController.register(); // Automatically logs in the new customer
                        break;
                    case 3:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } else {
                // User is logged in
                if (currentUser.getRole() == UserRole.ADMIN) {
                    AdminController adminController = new AdminController(currentUser);
                    adminController.showAdminMenu();
                    currentUser = null; // Logout after admin tasks
                } else if (currentUser.getRole() == UserRole.CUSTOMER) {
                    CustomerController customerController = new CustomerController((Customer) currentUser, orderProcessor);
                    customerController.showCustomerMenu();
                    currentUser = null; // Logout after customer tasks
                }
            }
        }

        System.out.println("Thank you for using the E-Commerce App. Goodbye!");
        InputUtil.closeScanner(); // Close scanner at the end
    }
}