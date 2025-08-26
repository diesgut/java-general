package com.diesgut.gof.estructural_pattern.decorator.decorator;

import com.diesgut.gof.estructural_pattern.decorator.component.ITransaction;

// Decorador Concreto 2: añade la comisión por procesamiento urgente.
public class UrgentProcessingFeeDecorator extends TransactionDecorator {
    public UrgentProcessingFeeDecorator(ITransaction wrappedTransaction) {
        super(wrappedTransaction);
    }

    @Override
    public double getCost() {
        // Añade el costo adicional al costo del objeto envuelto.
        return super.getCost() + 10.0; // Comisión urgente de $10
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", con Procesamiento Urgente";
    }
}