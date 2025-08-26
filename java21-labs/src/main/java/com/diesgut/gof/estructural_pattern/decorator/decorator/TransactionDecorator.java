package com.diesgut.gof.estructural_pattern.decorator.decorator;

import com.diesgut.gof.estructural_pattern.decorator.component.ITransaction;

// El Decorador Abstracto: mantiene una referencia al objeto Componente.
// (OPCIONAL) Una clase abstracta que implementa la interfaz y contiene la referencia al objeto envuelto. Ayuda a no repetir código.
public abstract class TransactionDecorator implements ITransaction {
    protected ITransaction wrappedTransaction;

    public TransactionDecorator(ITransaction wrappedTransaction) {
        this.wrappedTransaction = wrappedTransaction;
    }

    @Override
    public double getCost() {
        // Delega la llamada al objeto envuelto.
        return wrappedTransaction.getCost();
    }

    @Override
    public String getDescription() {
        // Delega la llamada al objeto envuelto.
        return wrappedTransaction.getDescription();
    }
}