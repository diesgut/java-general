package com.diesgut.gof.creational_pattern.factory_method_v2.factory;

import com.diesgut.gof.creational_pattern.factory_method_v2.products.CsvReport;
import com.diesgut.gof.creational_pattern.factory_method_v2.products.IFinancialReport;
import com.diesgut.gof.creational_pattern.factory_method_v2.products.JsonReport;
import com.diesgut.gof.creational_pattern.factory_method_v2.products.PdfReport;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

// La Fábrica como un registro central
public class ReportFactory {

    // El mapa que asocia un identificador con un "método de fábrica" (una lambda)
    private static final Map<String, Supplier<IFinancialReport>> registry = new HashMap<>();

    // Bloque estático para registrar nuestros productos.
    // Aquí es donde "enchufamos" las nuevas implementaciones.
    static {
        registry.put("PDF", () -> new PdfReport()); // () -> new PdfReport() es nuestro Factory Method
        registry.put("CSV", CsvReport::new);       // Usando una referencia a método, aún más conciso
        registry.put("JSON", JsonReport::new);
    }

    // El método principal de la fábrica que el cliente usará.
    public static IFinancialReport createReport(String format) {
        // Buscamos el Supplier en el mapa
        Supplier<IFinancialReport> reportSupplier = registry.get(format.toUpperCase());

        // Si no lo encontramos, lanzamos una excepción
        if (reportSupplier == null) {
            throw new IllegalArgumentException("Formato de reporte desconocido: " + format);
        }

        // Si lo encontramos, lo usamos para crear una nueva instancia
        return reportSupplier.get();
    }

    // Una alternativa usando Optional para un manejo más funcional
    public static Optional<IFinancialReport> createReportOptional(String format) {
        return Optional.ofNullable(registry.get(format.toUpperCase())).map(Supplier::get);
    }
}
