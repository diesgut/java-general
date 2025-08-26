package com.diesgut.gof.behaviour_pattern.template_method.template;

// La clase abstracta que define el "template"
public abstract class TransactionFileProcessorTemplate {

    // Este es el Template Method. Es 'final' para que las subclases no puedan alterarlo.
    public final void processFile(String filePath) {
        // 1. Pasos variables implementados por las subclases
        openFile(filePath);
        parseData();

        // 2. Pasos comunes implementados en la clase base
        validateTransactions();
        applyTransactions();
        generateReport();
    }

    // --- Pasos Abstractos (a ser implementados por las subclases) ---
    protected abstract void openFile(String filePath);
    protected abstract void parseData();

    // --- Pasos Comunes (implementados aquí para ser reutilizados) ---
    private void validateTransactions() {
        System.out.println("Validando transacciones contra reglas de negocio comunes...");
    }

    private void applyTransactions() {
        System.out.println("Aplicando transacciones a las cuentas de los clientes...");
    }

    private void generateReport() {
        System.out.println("Generando reporte final del procesamiento.");
    }
}
