package com.diesgut.gof.creational_pattern.factory_method_v1.products.imp;

import com.diesgut.gof.creational_pattern.factory_method_v1.products.IBankingProduct;

//ahorro
public class SavingsAccount implements IBankingProduct {

    @Override
    public void open() {
        System.out.println("Opening a savings account");
    }

    @Override
    public String getDescription() {
        return "This is a savings account";
    }
}
