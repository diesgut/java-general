package com.diesgut.gof.creational_pattern.factory_method_v2.products;

public class JsonReport implements IFinancialReport {
    @Override
    public void generate() {
        System.out.println("Generando un reporte en formato JSON para APIs...");
    }
}