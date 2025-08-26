package com.diesgut.gof.creational_pattern.factory_method_v1.products.imp;

import com.diesgut.gof.creational_pattern.factory_method_v1.products.IBankingProduct;

//corriente
public class CheckingAccount implements IBankingProduct {

    @Override
    public void open() {
        System.out.println("Opening a checking account");
    }

    @Override
    public String getDescription() {
        return "This is a checking account";
    }
}
