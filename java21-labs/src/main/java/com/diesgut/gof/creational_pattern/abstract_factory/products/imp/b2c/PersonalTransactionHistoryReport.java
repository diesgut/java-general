package com.diesgut.gof.creational_pattern.abstract_factory.products.imp.b2c;


import com.diesgut.gof.creational_pattern.abstract_factory.products.ITransactionHistoryReport;

public class PersonalTransactionHistoryReport implements ITransactionHistoryReport {
    @Override
    public String generate() {
        return "Generando historial de transacciones de cuenta de ahorros/corriente personal.";
    }
}
