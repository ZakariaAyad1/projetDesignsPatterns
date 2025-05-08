package com.ecommerce.patterns.decorator;

import com.ecommerce.model.ProductComponent;

public class ExtendedWarrantyDecorator extends ProductDecorator {
    private static final double WARRANTY_COST_PERCENTAGE = 0.10; // 10% of product price

    public ExtendedWarrantyDecorator(ProductComponent decoratedProduct) {
        super(decoratedProduct);
    }

    @Override
    public String getName() {
        return super.getName() + " (Extended Warranty)";
    }

    @Override
    public double getPrice() {
        return super.getPrice() + (decoratedProduct.getPrice() * WARRANTY_COST_PERCENTAGE);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " Includes a 2-year extended warranty.";
    }
}