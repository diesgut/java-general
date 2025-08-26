package com.diesgut.gof.creational_pattern.factory_method_v1.products.imp;

import com.diesgut.gof.creational_pattern.factory_method_v1.products.IBankingProduct;

//hipotecario
public class MortgageLoan implements IBankingProduct {

    @Override
    public void open() {
        System.out.println("Opening a mortgage loan");
    }

    @Override
    public String getDescription() {
        return "This is a mortgage loan";
    }
}
