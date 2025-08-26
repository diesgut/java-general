package com.diesgut.gof.behaviour_pattern.strategy;

import com.diesgut.gof.behaviour_pattern.strategy.context.TransferService;
import com.diesgut.gof.behaviour_pattern.strategy.strategies.NoFeeStrategy;
import com.diesgut.gof.behaviour_pattern.strategy.strategies.StandardFeeStrategy;
import com.diesgut.gof.behaviour_pattern.strategy.strategies.VipFeeStrategy;

public class Main {
    public static void main(String[] args) {
        TransferService transferService = new TransferService();
        double amount = 500.0;

        // Escenario 1: Un cliente normal en un día de semana
        System.out.println("--- Transferencia Estándar ---");
        transferService.setFeeStrategy(new StandardFeeStrategy());
        transferService.executeTransfer("ACC-001", "ACC-002", amount);

        // Escenario 2: Un cliente VIP
        System.out.println("\n--- Transferencia Cliente VIP ---");
        transferService.setFeeStrategy(new VipFeeStrategy());
        transferService.executeTransfer("ACC-VIP", "ACC-003", amount);

        // Escenario 3: Un cliente nuevo con promoción
        System.out.println("\n--- Transferencia en Promoción ---");
        transferService.setFeeStrategy(new NoFeeStrategy());
        transferService.executeTransfer("ACC-NEW", "ACC-004", amount);
    }
}
