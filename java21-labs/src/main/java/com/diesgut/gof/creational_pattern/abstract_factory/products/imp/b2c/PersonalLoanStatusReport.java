package com.diesgut.gof.creational_pattern.abstract_factory.products.imp.b2c;

import com.diesgut.gof.creational_pattern.abstract_factory.products.ILoanStatusReport;

public class PersonalLoanStatusReport implements ILoanStatusReport {
    @Override
    public String generate() {
        return "Generando reporte de préstamos personales (hipotecario, automotriz).";
    }
}
