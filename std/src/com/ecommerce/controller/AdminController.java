package com.ecommerce.controller;

import com.ecommerce.model.Product;
import com.ecommerce.model.ProductCatalog;
import com.ecommerce.model.User;
import com.ecommerce.patterns.command.*;
import com.ecommerce.patterns.factory.ProductFactory;
import com.ecommerce.patterns.proxy.AdminService;
import com.ecommerce.patterns.proxy.AdminServiceProxy;
import com.ecommerce.util.InputUtil;
import com.ecommerce.view.ConsoleView;

public class AdminController {
    private User adminUser;
    private AdminService adminService; // This will be the Proxy
    private ProductFactory productFactory = new ProductFactory();
    private ProductCatalog catalog = ProductCatalog.getInstance();
    private ConsoleView view = new ConsoleView();
    private AdminActionInvoker invoker = new AdminActionInvoker();

    public AdminController(User adminUser) {
        this.adminUser = adminUser;
        this.adminService = new AdminServiceProxy(adminUser); // Use the proxy
    }

    public void showAdminMenu() {
        boolean running = true;
        while (running) {
            adminUser.displayDashboard();
            System.out.println("1. Add Product");
            System.out.println("2. Update Product");
            System.out.println("3. Delete Product");
            System.out.println("4. View All Products");
            System.out.println("5. Logout");
            int choice = InputUtil.getIntInput("Enter choice: ");

            switch (choice) {
                case 1:
                    handleAddProduct();
                    break;
                case 2:
                    handleUpdateProduct();
                    break;
                case 3:
                    handleDeleteProduct();
                    break;
                case 4:
                    view.displayProducts(catalog.getAllProducts());
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void handleAddProduct() {
        System.out.println("\n--- Add New Product ---");
        String name = InputUtil.getStringInput("Product Name: ");
        double price = InputUtil.getDoubleInput("Price: ");
        String description = InputUtil.getStringInput("Description (optional): ");
        String category = InputUtil.getStringInput("Category (optional): ");
        int stock = InputUtil.getIntInput("Stock: ");

        Product newProduct = productFactory.createProduct(name, price, description, category, stock);
        AdminCommand addCommand = new AddProductCommand(adminService, newProduct);
        invoker.setCommand(addCommand);
        invoker.executeCommand();
    }

    private void handleUpdateProduct() {
        System.out.println("\n--- Update Product ---");
        String id = InputUtil.getStringInput("Product ID to update: ");
        if (catalog.getProductById(id).isEmpty()){
            System.out.println("Product with ID " + id + " not found.");
            return;
        }
        // For simplicity, we ask for all fields. A real app might allow partial updates.
        String name = InputUtil.getStringInput("New Name (leave blank to keep current): ");
        double price = InputUtil.getDoubleInput("New Price (0 to keep current): ");
        String description = InputUtil.getStringInput("New Description (leave blank to keep current): ");
        String category = InputUtil.getStringInput("New Category (leave blank to keep current): ");
        int stock = InputUtil.getIntInput("New Stock (-1 to keep current): ");

        AdminCommand updateCommand = new UpdateProductCommand(adminService, id, name, price, description, category, stock);
        invoker.setCommand(updateCommand);
        invoker.executeCommand();
    }

    private void handleDeleteProduct() {
        System.out.println("\n--- Delete Product ---");
        String id = InputUtil.getStringInput("Product ID to delete: ");
        AdminCommand deleteCommand = new DeleteProductCommand(adminService, id);
        invoker.setCommand(deleteCommand);
        invoker.executeCommand();
    }
}