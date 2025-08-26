package com.diesgut.gof.creational_pattern.factory_method_v2.products;

public class PdfReport implements IFinancialReport {
    @Override
    public void generate() {
        System.out.println("Generando un complejo reporte en formato PDF con gráficos y tablas...");
    }
}