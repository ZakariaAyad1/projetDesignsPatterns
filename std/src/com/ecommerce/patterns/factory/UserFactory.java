package com.ecommerce.patterns.factory;

import com.ecommerce.model.Admin;
import com.ecommerce.model.Customer;
import com.ecommerce.model.User;
import com.ecommerce.model.UserRole;
import com.ecommerce.model.UserStore;

public class UserFactory {
    public User createUser(UserRole role, String username, String password) {
        String userId = UserStore.getInstance().generateUserId();
        switch (role) {
            case ADMIN:
                return new Admin(userId, username, password);
            case CUSTOMER:
                return new Customer(userId, username, password);
            default:
                throw new IllegalArgumentException("Unknown user role: " + role);
        }
    }
}