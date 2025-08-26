package com.diesgut.gof.behaviour_pattern.visitor.elements;

import com.diesgut.gof.behaviour_pattern.visitor.visitors.IReportVisitor;

public class Loan implements IFinancialProduct {
    public final double amountOwed;

    public Loan(double amountOwed) {
        this.amountOwed = amountOwed;
    }

    @Override
    public void accept(IReportVisitor visitor) {
        visitor.visit(this);
    }
}