package com.diesgut.gof.behaviour_pattern.observer.observers;

// Observador concreto para notificaciones por email
public class EmailNotifierObserver implements ITransactionObserver {
    @Override
    public void update(String accountId, String transactionType, double amount, double newBalance) {
        System.out.println(
                "EMAIL: Notificación enviada. Cuenta: " + accountId + ", Tipo: " + transactionType + ", Monto: " + amount
        );
    }
}
