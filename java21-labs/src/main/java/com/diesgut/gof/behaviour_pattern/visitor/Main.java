package com.diesgut.gof.behaviour_pattern.visitor;

import com.diesgut.gof.behaviour_pattern.visitor.elements.FixedDeposit;
import com.diesgut.gof.behaviour_pattern.visitor.elements.IFinancialProduct;
import com.diesgut.gof.behaviour_pattern.visitor.elements.Loan;
import com.diesgut.gof.behaviour_pattern.visitor.elements.SavingsAccount;
import com.diesgut.gof.behaviour_pattern.visitor.visitors.FinancialHealthVisitor;
import com.diesgut.gof.behaviour_pattern.visitor.visitors.MarketingVisitor;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // La estructura de objetos: el portafolio del cliente
        List<IFinancialProduct> portfolio = List.of(
                new SavingsAccount(5000),
                new FixedDeposit(10000, 24),
                new Loan(12000)
        );

        // --- Generar el primer reporte ---
        System.out.println("--- Reporte de Salud Financiera ---");
        FinancialHealthVisitor healthVisitor = new FinancialHealthVisitor();
        for (IFinancialProduct product : portfolio) {
            product.accept(healthVisitor);
        }
        System.out.println("Patrimonio Neto del Cliente: $" + healthVisitor.getNetWorth());

        // --- Generar el segundo reporte sin tocar las clases de productos ---
        System.out.println("\n--- Reporte de Marketing ---");
        MarketingVisitor marketingVisitor = new MarketingVisitor();
        for (IFinancialProduct product : portfolio) {
            product.accept(marketingVisitor);
        }
        System.out.println(marketingVisitor.getSuggestions());
    }
}
