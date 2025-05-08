package com.ecommerce.model;

public interface ProductComponent {
    String getId();
    String getName();
    double getPrice();
    String getDescription();
    int getStock();
    void setStock(int stock);
}