package com.diesgut.gof.behaviour_pattern.mediator;

import com.diesgut.gof.behaviour_pattern.mediator.component.ComplianceService;
import com.diesgut.gof.behaviour_pattern.mediator.component.CustomerAccount;
import com.diesgut.gof.behaviour_pattern.mediator.component.FraudDetectionSystem;
import com.diesgut.gof.behaviour_pattern.mediator.component.TransactionLedger;
import com.diesgut.gof.behaviour_pattern.mediator.mediator.TransferMediator;

public class Main {
    public static void main(String[] args) {
        // Aquí iría la configuración y ejecución del patrón Mediator
        // 1. Crear los componentes
        CustomerAccount account = new CustomerAccount();
        FraudDetectionSystem fraudSystem = new FraudDetectionSystem();
        ComplianceService complianceService = new ComplianceService();
        TransactionLedger ledger = new TransactionLedger();

        // 2. Crear el mediador y conectarlo con los componentes
        TransferMediator mediator = new TransferMediator(account, fraudSystem, complianceService, ledger);

        // 3. Iniciar el proceso
        double amountToTransfer = 250.0;
        String destinationAccount = "ACC-456";

        System.out.println("CLIENTE: Solicitando una transferencia de " + amountToTransfer);
        mediator.setTransactionData(amountToTransfer, destinationAccount);
        account.initiateTransfer(destinationAccount, amountToTransfer);
    }
}
