package com.diesgut.gof.behaviour_pattern.visitor.visitors;

import com.diesgut.gof.behaviour_pattern.visitor.elements.FixedDeposit;
import com.diesgut.gof.behaviour_pattern.visitor.elements.Loan;
import com.diesgut.gof.behaviour_pattern.visitor.elements.SavingsAccount;

//La interfaz Visitor (declara una visita para cada tipo de elemento)
public interface IReportVisitor {
    void visit(SavingsAccount account);
    void visit(FixedDeposit deposit);
    void visit(Loan loan);
}