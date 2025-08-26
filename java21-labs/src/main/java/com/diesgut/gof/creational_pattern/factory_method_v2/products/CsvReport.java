package com.diesgut.gof.creational_pattern.factory_method_v2.products;

public class CsvReport implements IFinancialReport {
    @Override
    public void generate() {
        System.out.println("Generando un reporte CSV simple, separado por comas...");
    }
}