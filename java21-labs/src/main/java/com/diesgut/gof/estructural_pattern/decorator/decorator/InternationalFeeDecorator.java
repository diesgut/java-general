package com.diesgut.gof.estructural_pattern.decorator.decorator;

import com.diesgut.gof.estructural_pattern.decorator.component.ITransaction;

// Decorador Concreto 1: añade la comisión por transferencia internacional.
public class InternationalFeeDecorator extends TransactionDecorator {
    public InternationalFeeDecorator(ITransaction wrappedTransaction) {
        super(wrappedTransaction);
    }

    @Override
    public double getCost() {
        // Añade el costo adicional al costo del objeto envuelto.
        return super.getCost() + 25.0; // Comisión internacional de $25
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", con Comisión Internacional";
    }
}