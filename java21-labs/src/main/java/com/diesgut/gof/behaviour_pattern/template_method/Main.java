package com.diesgut.gof.behaviour_pattern.template_method;

import com.diesgut.gof.behaviour_pattern.template_method.template.CsvFileProcessor;
import com.diesgut.gof.behaviour_pattern.template_method.template.JsonFileProcessor;
import com.diesgut.gof.behaviour_pattern.template_method.template.TransactionFileProcessorTemplate;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Iniciando procesamiento de archivo CSV ===");
        TransactionFileProcessorTemplate csvProcessor = new CsvFileProcessor();
        csvProcessor.processFile("transactions.csv");

        System.out.println("\n=== Iniciando procesamiento de archivo JSON ===");
        TransactionFileProcessorTemplate jsonProcessor = new JsonFileProcessor();
        jsonProcessor.processFile("transactions.json");
    }
}
