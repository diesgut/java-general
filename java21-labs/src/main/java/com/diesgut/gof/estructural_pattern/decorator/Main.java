package com.diesgut.gof.estructural_pattern.decorator;

import com.diesgut.gof.estructural_pattern.decorator.component.BasicTransaction;
import com.diesgut.gof.estructural_pattern.decorator.component.ITransaction;
import com.diesgut.gof.estructural_pattern.decorator.decorator.InternationalFeeDecorator;
import com.diesgut.gof.estructural_pattern.decorator.decorator.UrgentProcessingFeeDecorator;

public class Main {
    public static void main(String[] args) {
        // Escenario 1: Una transacción simple
        ITransaction simpleTransaction = new BasicTransaction(500);
        System.out.printf("Descripción: %s\n", simpleTransaction.getDescription());
        System.out.printf("Costo Total: $%.2f\n\n", simpleTransaction.getCost());

        // Escenario 2: Una transacción internacional
        ITransaction internationalTransaction = new InternationalFeeDecorator(new BasicTransaction(1000));
        System.out.printf("Descripción: %s\n", internationalTransaction.getDescription());
        System.out.printf("Costo Total: $%.2f\n\n", internationalTransaction.getCost());

        // Escenario 3: Una transacción internacional Y urgente (apilando decoradores)
        ITransaction urgentInternational =
                new UrgentProcessingFeeDecorator(
                        new InternationalFeeDecorator(
                                new BasicTransaction(2000)
                        )
                );
        System.out.printf("Descripción: %s\n", urgentInternational.getDescription());
        System.out.printf("Costo Total: $%.2f\n", urgentInternational.getCost());
    }
}