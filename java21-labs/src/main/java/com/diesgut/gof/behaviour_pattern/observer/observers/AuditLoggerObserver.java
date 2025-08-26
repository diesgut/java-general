package com.diesgut.gof.behaviour_pattern.observer.observers;

// Observador concreto para el registro de auditoría
public class AuditLoggerObserver implements ITransactionObserver {
    @Override
    public void update(String accountId, String transactionType, double amount, double newBalance) {
        System.out.println(
                "AUDITORIA: Transacción registrada. Cuenta: " + accountId + ", Tipo: " + transactionType + ", Monto: " + amount
        );
    }
}
