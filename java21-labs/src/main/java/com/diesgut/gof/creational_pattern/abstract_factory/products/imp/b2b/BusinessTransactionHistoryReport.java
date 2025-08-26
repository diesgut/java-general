package com.diesgut.gof.creational_pattern.abstract_factory.products.imp.b2b;

import com.diesgut.gof.creational_pattern.abstract_factory.products.ITransactionHistoryReport;

public class BusinessTransactionHistoryReport implements ITransactionHistoryReport {
    @Override
    public String generate() {
        return "Generando historial de transacciones de la cuenta maestra empresarial.";
    }
}