package com.diesgut.gof.behaviour_pattern.observer.observers;

// Observador concreto para actualizar el dashboard
public class DashboardUpdaterObserver implements ITransactionObserver {
    @Override
    public void update(String accountId, String transactionType, double amount, double newBalance) {
        System.out.println(
                "DASHBOARD: Panel actualizado para la cuenta " + accountId + ". Nuevo Saldo: " + newBalance
        );
    }
}