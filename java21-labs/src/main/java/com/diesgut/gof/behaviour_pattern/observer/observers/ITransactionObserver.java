package com.diesgut.gof.behaviour_pattern.observer.observers;

// Interfaz para todos los observadores
public interface ITransactionObserver {
    void update(String accountId, String transactionType, double amount, double newBalance);
}