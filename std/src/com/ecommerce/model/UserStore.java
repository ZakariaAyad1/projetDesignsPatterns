package com.ecommerce.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class UserStore {
    private static UserStore instance;
    private Map<String, User> usersByUsername;
    private Map<String, User> usersById;
    private AtomicInteger nextUserId = new AtomicInteger(1);


    private UserStore() {
        usersByUsername = new HashMap<>();
        usersById = new HashMap<>();
        // Add a default admin
        Admin defaultAdmin = new Admin(generateUserId(), "admin", "admin123");
        addUser(defaultAdmin);
    }

    public static synchronized UserStore getInstance() {
        if (instance == null) {
            instance = new UserStore();
        }
        return instance;
    }

    public String generateUserId() {
        return "U" + String.format("%03d", nextUserId.getAndIncrement());
    }

    public void addUser(User user) {
        usersByUsername.put(user.getUsername(), user);
        usersById.put(user.getUserId(), user);
    }

    public Optional<User> getUserByUsername(String username) {
        return Optional.ofNullable(usersByUsername.get(username));
    }

    public Optional<User> getUserById(String userId) {
        return Optional.ofNullable(usersById.get(userId));
    }

    public boolean usernameExists(String username) {
        return usersByUsername.containsKey(username);
    }
}