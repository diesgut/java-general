package com.diesgut.gof.behaviour_pattern.visitor.elements;

import com.diesgut.gof.behaviour_pattern.visitor.visitors.IReportVisitor;

//La interfaz Element (el contrato para aceptar visitantes)
public interface IFinancialProduct {
    void accept(IReportVisitor visitor);
}
