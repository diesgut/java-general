package com.diesgut.gof.estructural_pattern.flyweight.flyweight;

// La interfaz Flyweight
public interface ITransactionType {
    // El estado extrínseco (monto) se pasa como parámetro
    void processTransaction(double amount);
}