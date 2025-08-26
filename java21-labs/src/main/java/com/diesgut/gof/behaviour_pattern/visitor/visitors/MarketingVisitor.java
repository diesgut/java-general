package com.diesgut.gof.behaviour_pattern.visitor.visitors;

import com.diesgut.gof.behaviour_pattern.visitor.elements.FixedDeposit;
import com.diesgut.gof.behaviour_pattern.visitor.elements.Loan;
import com.diesgut.gof.behaviour_pattern.visitor.elements.SavingsAccount;

// Visitante Concreto 2: Reporte de Marketing
public class MarketingVisitor implements IReportVisitor {
    private final StringBuilder suggestions = new StringBuilder();

    public String getSuggestions() {
        return suggestions.toString();
    }

    @Override
    public void visit(SavingsAccount account) {
        suggestions.append("Para la Cuenta de Ahorros: Ofrecer un fondo de inversión de bajo riesgo.\n");
    }

    @Override
    public void visit(FixedDeposit deposit) {
        if (deposit.durationInMonths > 12) {
            suggestions.append("Para el Depósito a Plazo: Ofrecer una renovación con tasa preferencial.\n");
        }
    }

    @Override
    public void visit(Loan loan) {
        suggestions.append("Para el Préstamo: Ofrecer un seguro de desgravamen asociado.\n");
    }
}