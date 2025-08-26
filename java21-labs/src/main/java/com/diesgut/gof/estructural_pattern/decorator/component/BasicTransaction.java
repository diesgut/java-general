package com.diesgut.gof.estructural_pattern.decorator.component;

// El Componente Concreto: el objeto inicial al que se le añadirán responsabilidades.
public class BasicTransaction implements ITransaction {
    private double amount;

    public BasicTransaction(double amount) {
        this.amount = amount;
    }

    @Override
    public double getCost() {
        // La transacción base tiene una comisión fija, por ejemplo, de $1.
        return 1.0;
    }

    @Override
    public String getDescription() {
        return "Transacción Básica";
    }
}