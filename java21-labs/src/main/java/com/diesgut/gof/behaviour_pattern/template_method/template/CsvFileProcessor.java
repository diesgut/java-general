package com.diesgut.gof.behaviour_pattern.template_method.template;

// Implementación concreta para archivos CSV
public class CsvFileProcessor extends TransactionFileProcessorTemplate {
    @Override
    protected void openFile(String filePath) {
        System.out.println("Abriendo el archivo CSV en la ruta: " + filePath);
    }

    @Override
    protected void parseData() {
        System.out.println("Parseando datos, separando columnas por comas...");
    }
}
