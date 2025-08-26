package com.diesgut.gof.creational_pattern.abstract_factory.products.imp.b2b;

import com.diesgut.gof.creational_pattern.abstract_factory.products.ILoanStatusReport;

public class BusinessLoanStatusReport implements ILoanStatusReport {
    @Override
    public String generate() {
        return "Generando reporte de líneas de crédito y préstamos corporativos.";
    }
}