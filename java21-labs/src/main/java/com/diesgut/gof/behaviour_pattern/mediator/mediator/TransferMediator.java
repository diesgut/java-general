package com.diesgut.gof.behaviour_pattern.mediator.mediator;

import com.diesgut.gof.behaviour_pattern.mediator.component.*;

// ----- EL MEDIADOR CONCRETO -----
public class TransferMediator implements ITransactionMediator {
    // El mediador conoce a todos los componentes
    private CustomerAccount account;
    private FraudDetectionSystem fraudSystem;
    private ComplianceService complianceService;
    private TransactionLedger ledger;

    // Datos temporales de la transacción en curso
    private double transferAmount;
    private String toAccount;

    public TransferMediator(CustomerAccount account, FraudDetectionSystem fraudSystem, ComplianceService complianceService, TransactionLedger ledger) {
        this.account = account;
        this.account.setMediator(this);

        this.fraudSystem = fraudSystem;
        this.fraudSystem.setMediator(this);

        this.complianceService = complianceService;
        this.complianceService.setMediator(this);

        this.ledger = ledger;
        this.ledger.setMediator(this);
    }

    public void setTransactionData(double amount, String toAccount) {
        this.transferAmount = amount;
        this.toAccount = toAccount;
    }

    // Aquí está la magia: el flujo de trabajo centralizado
    @Override
    public void notify(Component component, String event) {
        System.out.println("Mediador recibió notificación: '" + event + "' de " + component.getClass().getSimpleName());

        if (component == account && event.equals("balanceChecked")) {
            System.out.println("Mediador: Saldo OK. Iniciando verificación de fraude.");
            fraudSystem.checkTransaction();
        } else if (component == fraudSystem && event.equals("fraudCheckPassed")) {
            System.out.println("Mediador: Fraude OK. Iniciando verificación de cumplimiento.");
            complianceService.checkCompliance();
        } else if (component == complianceService && event.equals("complianceCheckPassed")) {
            System.out.println("Mediador: Cumplimiento OK. Realizando débito y registrando transacción.");
            account.debit(transferAmount);
            ledger.record(account.getAccountId(), this.toAccount, this.transferAmount);
        } else if (component == ledger && event.equals("transactionRecorded")) {
            System.out.println("--- Proceso de Transferencia Finalizado por el Mediador ---");
        }
    }
}
