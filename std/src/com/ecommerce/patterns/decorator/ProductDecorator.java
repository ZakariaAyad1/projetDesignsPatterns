package com.ecommerce.patterns.decorator;

import com.ecommerce.model.ProductComponent;

public abstract class ProductDecorator implements ProductComponent {
    protected ProductComponent decoratedProduct;

    public ProductDecorator(ProductComponent decoratedProduct) {
        this.decoratedProduct = decoratedProduct;
    }

    @Override
    public String getId() {
        return decoratedProduct.getId();
    }

    @Override
    public String getName() {
        return decoratedProduct.getName();
    }

    @Override
    public double getPrice() {
        return decoratedProduct.getPrice();
    }

    @Override
    public String getDescription() {
        return decoratedProduct.getDescription();
    }

    @Override
    public int getStock() {
        return decoratedProduct.getStock();
    }

    @Override
    public void setStock(int stock) {
        decoratedProduct.setStock(stock);
    }
}