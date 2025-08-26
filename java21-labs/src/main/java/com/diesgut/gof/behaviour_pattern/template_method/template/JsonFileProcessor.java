package com.diesgut.gof.behaviour_pattern.template_method.template;

// Implementación concreta para archivos JSON
public class JsonFileProcessor extends TransactionFileProcessorTemplate {
    @Override
    protected void openFile(String filePath) {
        System.out.println("Abriendo el archivo JSON en la ruta: " + filePath);
    }

    @Override
    protected void parseData() {
        System.out.println("Parseando estructura de objetos y arrays JSON...");
    }
}
