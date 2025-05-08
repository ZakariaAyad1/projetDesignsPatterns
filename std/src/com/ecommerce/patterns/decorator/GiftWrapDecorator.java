package com.ecommerce.patterns.decorator;

import com.ecommerce.model.ProductComponent;

public class GiftWrapDecorator extends ProductDecorator {
    private static final double GIFT_WRAP_COST = 5.00;

    public GiftWrapDecorator(ProductComponent decoratedProduct) {
        super(decoratedProduct);
    }

    @Override
    public String getName() {
        return super.getName() + " (Gift Wrapped)";
    }

    @Override
    public double getPrice() {
        return super.getPrice() + GIFT_WRAP_COST;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " Comes beautifully gift wrapped.";
    }
}