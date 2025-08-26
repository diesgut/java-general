package com.diesgut.gof.creational_pattern.factory_method_v1.creator;

import com.diesgut.gof.creational_pattern.factory_method_v1.products.IBankingProduct;

//puede ser una interface que declare createBankingProduct, como el factory method, luego usar esa interface dodne se requera
public abstract class BankOfficer {

    // Lógica de negocio principal. No cambia.
    // Fíjate que usa el producto creado por el factory method.
    public void openProductForClient() {
        IBankingProduct product = createBankingProduct();

        System.out.println("Initiating product opening process...");
        product.open();
        System.out.println("Description: " + product.getDescription());
        //logica de negocio adicional
        System.out.println("Process completed!");
    }

    // ⭐ El "Factory Method" abstracto.
    // Las subclases están OBLIGADAS a proveer una implementación.
    protected abstract IBankingProduct createBankingProduct();
}
