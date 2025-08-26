package com.diesgut.gof.behaviour_pattern.visitor.elements;

import com.diesgut.gof.behaviour_pattern.visitor.visitors.IReportVisitor;

public class SavingsAccount implements IFinancialProduct {
    public final double balance;

    public SavingsAccount(double balance) {
        this.balance = balance;
    }

    @Override
    public void accept(IReportVisitor visitor) {
        visitor.visit(this); // Llama al método 'visit' específico para SavingsAccount
    }
}