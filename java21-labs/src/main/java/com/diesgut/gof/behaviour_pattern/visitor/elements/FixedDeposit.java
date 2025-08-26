package com.diesgut.gof.behaviour_pattern.visitor.elements;

import com.diesgut.gof.behaviour_pattern.visitor.visitors.IReportVisitor;

public class FixedDeposit implements IFinancialProduct {
    public final double amount;
    public final int durationInMonths;
    public FixedDeposit(double amount, int duration) { this.amount = amount; this.durationInMonths = duration; }

    @Override
    public void accept(IReportVisitor visitor) {
        visitor.visit(this);
    }
}