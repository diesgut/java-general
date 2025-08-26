package com.diesgut.gof.estructural_pattern.flyweight;

import com.diesgut.gof.estructural_pattern.flyweight.factory.TransactionTypeFactory;
import com.diesgut.gof.estructural_pattern.flyweight.flyweight.ITransactionType;

// El objeto del cliente que contiene el estado extrínseco
public class Transaction {
    // Estado Extrínseco (único para cada transacción)
    private final double amount;
    private final long timestamp;

    // Referencia al Flyweight compartido
    private final ITransactionType type;

    public Transaction(double amount, String typeName) {
        this.amount = amount;
        this.timestamp = System.currentTimeMillis();
        this.type = TransactionTypeFactory.getTransactionType(typeName);
    }

    public void process() {
        type.processTransaction(amount);
    }
}