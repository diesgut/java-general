package com.diesgut.gof.behaviour_pattern.visitor.visitors;

import com.diesgut.gof.behaviour_pattern.visitor.elements.FixedDeposit;
import com.diesgut.gof.behaviour_pattern.visitor.elements.Loan;
import com.diesgut.gof.behaviour_pattern.visitor.elements.SavingsAccount;

//Visitante Concreto 1: Reporte de Salud Financiera
public class FinancialHealthVisitor implements IReportVisitor {
    private double netWorth = 0;

    public double getNetWorth() {
        return netWorth;
    }

    @Override
    public void visit(SavingsAccount account) {
        // Lógica para la cuenta de ahorros
        netWorth += account.balance;
    }

    @Override
    public void visit(FixedDeposit deposit) {
        // Lógica para el depósito a plazo
        netWorth += deposit.amount;
    }

    @Override
    public void visit(Loan loan) {
        // Lógica para el préstamo
        netWorth -= loan.amountOwed;
    }
}