package com.diesgut.gof.creational_pattern.factory_method_v1.creator;

import com.diesgut.gof.creational_pattern.factory_method_v1.products.IBankingProduct;
import com.diesgut.gof.creational_pattern.factory_method_v1.products.imp.CheckingAccount;

public class CheckingAccountBankOfficer extends BankOfficer {
    @Override
    protected IBankingProduct createBankingProduct() {
        return new CheckingAccount();
    }
}
