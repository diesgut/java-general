package com.diesgut.gof.creational_pattern.factory_method_v2;

import com.diesgut.gof.creational_pattern.factory_method_v2.factory.ReportFactory;
import com.diesgut.gof.creational_pattern.factory_method_v2.products.IFinancialReport;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Solicitando reporte PDF ---");
        IFinancialReport pdfReport = ReportFactory.createReport("PDF");
        pdfReport.generate();

        System.out.println("\n--- Solicitando reporte CSV ---");
        IFinancialReport csvReport = ReportFactory.createReport("CSV");
        csvReport.generate();

        System.out.println("\n--- Solicitando reporte no existente ---");
        try {
            IFinancialReport xmlReport = ReportFactory.createReport("XML");
            xmlReport.generate();
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }

        System.out.println("\n--- Usando la versión con Optional ---");
        ReportFactory.createReportOptional("JSON")
                .ifPresentOrElse(
                        IFinancialReport::generate,
                        () -> System.out.println("Formato JSON no disponible.")
                );
    }
}
