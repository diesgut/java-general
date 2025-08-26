package com.diesgut.gof.behaviour_pattern.strategy.context;

import com.diesgut.gof.behaviour_pattern.strategy.strategies.IFeeCalculationStrategy;

// El Contexto
public class TransferService {
    // El contexto mantiene una referencia a una de las estrategias.
    private IFeeCalculationStrategy feeStrategy;

    // La estrategia se puede establecer en el constructor o con un setter.
    public void setFeeStrategy(IFeeCalculationStrategy feeStrategy) {
        this.feeStrategy = feeStrategy;
    }

    public void executeTransfer(String fromAccount, String toAccount, double amount) {
        // 1. El contexto delega el cálculo de la comisión a la estrategia.
        double fee = feeStrategy.calculate(amount);
        double totalAmount = amount + fee;

        System.out.println("Ejecutando transferencia de $" + amount);
        System.out.println("Estrategia de comisión aplicada: " + feeStrategy.getClass().getSimpleName());
        System.out.println("Comisión: $" + fee);
        System.out.println("Monto total a debitar: $" + totalAmount);
        // 2. Lógica para realizar la transferencia...
    }
}
